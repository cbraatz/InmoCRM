package crm

import static org.springframework.http.HttpStatus.*

import java.lang.ClassLoader.ParallelLoaders

import grails.transaction.Transactional
import crm.enums.Resolution;
@Transactional(readOnly = true)
class SoldPropertyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond SoldProperty.list(params), model:[soldPropertyCount: SoldProperty.count()]
	}
    def show(SoldProperty soldProperty) {
        respond soldProperty
    }

    def create() {
		SoldProperty soldProperty;
		ManagedProperty managedProperty=ManagedProperty.get(params.pid);
		if(null!=managedProperty){
			Contract c=managedProperty.concession.getCurrentContract();
			if(null!=c) {
				soldProperty=new SoldProperty(date:new Date(), commissionAmount:(managedProperty.price*c.contractType.commissionPercentage/100), isConfirmed:false, sellPrice:managedProperty.price, crmUser:session.user, currency:managedProperty.currency, managedProperty:managedProperty);
				if(c.endDate.getTime()<new Date().getTime()){
					soldProperty.errors.rejectValue('',message(code:'concession.current.contract.expired.error').toString());
				}
			}else {
				soldProperty=new SoldProperty(date:new Date(), isConfirmed:false, sellPrice:managedProperty.price, crmUser:session.user, currency:managedProperty.currency, managedProperty:managedProperty);
				soldProperty.errors.rejectValue('',message(code:'concession.current.contract.required.error').toString());
			}
		}else {
			soldProperty=new SoldProperty(date:new Date(), isConfirmed:false, crmUser:session.user);
			soldProperty.errors.rejectValue('',message(code:'soldProperty.managedProperty.required.error').toString());
		}
		respond soldProperty, view:'create';
    }

    @Transactional
    def save(SoldProperty soldProperty) {
        if (soldProperty == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		if (soldProperty.propertyDemand) {
			if(soldProperty.propertyDemand.isSellDemand.booleanValue()==true) {
				soldProperty.errors.rejectValue('propertyDemand',message(code:'soldProperty.propertyDemand.sell.type.error').toString());
			}
		}
		
		if(null==soldProperty.managedProperty) {
			soldProperty.errors.rejectValue('',message(code:'soldProperty.managedProperty.required.error').toString());
		}else {
			if(soldProperty.managedProperty.resolution.equals(crm.enums.Resolution.SOLD)) {
				soldProperty.errors.rejectValue('',message(code:'managedProperty.already.sold.error').toString());
			}
		}

        if (soldProperty.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond soldProperty.errors, view:'create';
            return
        }

		if(!soldProperty.save(flush:true)){
			soldProperty.errors.rejectValue('',message(code:'soldProperty.save.error').toString());
			transactionStatus.setRollbackOnly();
			respond soldProperty.errors, view:'create';
			return;
		}
		String msg=setCatchCommissionsByProperty(soldProperty.managedProperty);
		if(!msg.isEmpty()){
			soldProperty.errors.rejectValue('',msg);
			transactionStatus.setRollbackOnly();
			respond soldProperty.errors, view:'create';
			return;
		}
		
		msg=setSellCommissionsByProperty(soldProperty);
		if(!msg.isEmpty()){
			soldProperty.errors.rejectValue('',msg);
			transactionStatus.setRollbackOnly();
			respond soldProperty.errors, view:'create';
			return;
		}
		
		soldProperty.managedProperty.resolution=Resolution.SOLD;
		if(!soldProperty.managedProperty.save(flush:true)){
			soldProperty.errors.rejectValue('',message(code:'soldProperty.property.save.error').toString());
			transactionStatus.setRollbackOnly();
			respond soldProperty.errors, view:'create';
			return;
		}
		soldProperty.managedProperty.concession.isActive=false;//desactivar propiedad por defecto. Cuando una concesion pueda tener más de 1 concesión habría que preguntar si cerrar la concesión en lugar de hacerlo automaticamente
		if(!soldProperty.managedProperty.concession.save(flush:true)){
			soldProperty.errors.rejectValue('',message(code:'soldProperty.concession.save.error').toString());
			transactionStatus.setRollbackOnly();
			respond soldProperty.errors, view:'create';
			return;
		}

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'soldProperty.label', default: 'Sold Property'), soldProperty.id])
                redirect soldProperty
            }
            '*' { respond soldProperty, [status: CREATED] }
        }
    }

    def edit(SoldProperty soldProperty) {
        respond soldProperty
    }
	
	private String setSellCommissionsByProperty(SoldProperty soldProperty) {
		///genera las comisiones por venta.
		CommissionRate commirPD=null;
		if(soldProperty.propertyDemand) {//si se vendió la concesión a traves de una demanda
			CommissionType ct=CommissionType.getAtalayaSellCommissionType();
			if(!ct){
				return message(code:'commissionType.not.found.error', args:[CommissionType.getAtalayaSellCommissionTypeInternalID()]).toString();
			}
			commirPD=CommissionRate.findByPartnerRoleAndCommissionType(soldProperty.propertyDemand.owner.partner.partnerRole, ct);
			
			if(!commirPD){
				return message(code:'commissionRate.pd.not.found.error', args:[soldProperty.propertyDemand.owner.partner.partnerRole, ct]).toString();
			}
			CommissionByProperty c1=new CommissionByProperty(percentage: commirPD.percentage, description: "", amount: soldProperty.commissionAmount*commirPD.percentage/100, currency: soldProperty.currency, crmUser: session.user, partner: soldProperty.propertyDemand.owner.partner, commissionRate:commirPD, managedProperty:soldProperty.managedProperty);
			if(!c1.save(flush:true)){
				return message(code:'commissionByProperty.save.error').toString();
			}
		}
		
		CommissionType ctPar=null;
		String intId;
		if(soldProperty.propertyDemand) {
			intId=CommissionType.getPartnerWithAtalayaSellCommissionTypeInternalID();
			ctPar=CommissionType.findByInternalID(intId);//comision de venta de inmueble con atalaya
		}else {
			intId=CommissionType.getPartnerSellCommissionTypeInternalID();
			ctPar=CommissionType.findByInternalID(intId);//comision de venta de inmueble sin atalaya
		}
		if(!ctPar){
			return message(code:'commissionType.not.found.error', args:[intId]).toString();
		}
		CommissionRate commirPar=CommissionRate.findByPartnerRoleAndCommissionType(soldProperty.crmUser.partner.partnerRole, ctPar);
		if(commirPar){
			CommissionByProperty c2=new CommissionByProperty(percentage: commirPar.percentage, description: "", amount: soldProperty.commissionAmount*commirPar.percentage/100, currency: soldProperty.currency, crmUser: session.user, partner: soldProperty.crmUser.partner, commissionRate:commirPar, managedProperty:soldProperty.managedProperty);
			if(!c2.save(flush:true)){
				return message(code:'commissionByProperty.save.error').toString();
			}
		}else {
			return message(code:'commissionRate.pa.not.found.error', args:[soldProperty.crmUser.partner.partnerRole, ctPar]).toString();
		}
		return "";
	}
	
	private String setCatchCommissionsByProperty(ManagedProperty managedProperty) {
		Concession concession=managedProperty.concession;
		if(null!=managedProperty &&  null!=concession) {
			List<CommissionByProperty> cbcs = CommissionByProperty.findAllByManagedProperty(managedProperty);
			CommissionByProperty cbc=null;
			CommissionType ct=CommissionType.getAtalayaSellCommissionType();
			if(!ct){
				return message(code:'commissionType.not.found.error', args:[CommissionType.getAtalayaCatchCommissionTypeInternalID()]).toString();
			}
			cbcs.each{
				if(it.commissionRate.commissionType.internalID.equals(ct.internalID) {
					cbc=it;
				}
			}
			if(concession.propertyDemand) {
				if(!cbc) {//si no tiene comision de atalaya de venta y tiene propertyDemand hay que agregar comision de atalaya
					CommissionRate commirPD=CommissionRate.findByPartnerRoleAndCommissionType(concession.propertyDemand.owner.partner.partnerRole, ct);
					if(!commirPD){
						return message(code:'commissionRate.pd.not.found.error', args:[concession.propertyDemand.owner.partner.partnerRole, ct]).toString();
					}
					CommissionByProperty c1=new CommissionByProperty(percentage: commirPD.percentage, description: "", amount: managedProperty.commissionAmount*commirPD.percentage/100, currency: managedProperty.currency, crmUser: session.user, partner: concession.propertyDemand.owner.partner, commissionRate:commirPD, managedProperty:managedProperty);
					if(!c1.save(flush:true)){
						return message(code:'commissionByProperty.save.error').toString();
					}
				}
			}else {
				if(cbc) {//si tiene comision de atalaya de venta y no tiene propertyDemand hay que borrar comision de atalaya
					cbc.delete flush:true
				}
			}
			cbc=null;
			if(concession.propertyDemand) {//el agente pudo captar en base a una propertyDemand
				ct=CommissionType.getPartnerCatchCommissionType()
				cbcs.each{
					if(it.commissionRate.commissionType.internalID.equals(ct.internalID) {
						cbc=it;
					}
				}
				if(cbc) {//si tiene comision de partner sin atalaya de venta y fue captado a traves de un ayalaya hay que borrar comision de partner solo
					cbc.delete flush:true
					cbc=null;
				}
				
				ct=CommissionType.getPartnerWithAtalayaCatchCommissionType()
				cbcs.each{
					if(it.commissionRate.commissionType.internalID.equals(ct.internalID) {
						cbc=it;
					}
				}
				if(!cbc) {//si no tiene comision de partner con atalaya de venta y fue captado a traves de un atalaya hay que agregar comision para el partner
					CommissionRate commir=CommissionRate.findByPartnerRoleAndCommissionType(concession.propertyDemand.owner.partner.partnerRole, ct);
					if(!commir){
						return message(code:'commissionRate.pa.not.found.error', args:[concession.propertyDemand.owner.partner.partnerRole, ct]).toString();
					}
					CommissionByProperty c=new CommissionByProperty(percentage: commir.percentage, description: "", amount: managedProperty.commissionAmount*commir.percentage/100, currency: managedProperty.currency, crmUser: session.user, partner: concession.propertyDemand.owner.partner, commissionRate:commir, managedProperty:managedProperty);
					if(!c.save(flush:true)){
						return message(code:'commissionByProperty.save.error').toString();
					}
				}

			}else {
				cbc=null;
				ct=CommissionType.getPartnerWithAtalayaCatchCommissionType();
				cbcs.each{
					if(it.commissionRate.commissionType.internalID.equals(ct.internalID){
						cbc=it;
					}
				}
				if(cbc) {//si tiene comision de partner con atalaya y no fue captado a traves de un ayalaya hay que borrar comision de partner con atalaya
					cbc.delete flush:true
					cbc=null;
				}
				ct=CommissionType.getPartnerCatchCommissionType();
				cbcs.each{
					if(it.commissionRate.commissionType.internalID.equals(ct.internalID){
						cbc=it;
					}
				}
				if(!cbc) {//si no tiene comision de partner sin atalaya de venta y fue captado sin atalaya hay que agregar comision para el partner sin atalaya
					CommissionRate commir=CommissionRate.findByPartnerRoleAndCommissionType(concession.crmUser.partner.partnerRole, ct);
					if(!commir){
						return message(code:'commissionRate.pa.not.found.error', args:[concession.crmUser.partner.partnerRole, ct]).toString();
					}
					CommissionByProperty c=new CommissionByProperty(percentage: commir.percentage, description: "", amount: managedProperty.commissionAmount*commir.percentage/100, currency: managedProperty.currency, crmUser: session.user, partner: concession.crmUser.partner, commissionRate:commir, managedProperty:managedProperty);
					if(!c.save(flush:true)){
						return message(code:'commissionByProperty.save.error').toString();
					}
				}
			}
		}else {
			return null;
		}
		return "";
	}
    @Transactional
    def update(SoldProperty soldProperty) {
        if (soldProperty == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (soldProperty.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond soldProperty.errors, view:'edit'
            return
        }

        soldProperty.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'soldProperty.label', default: 'Sold Property'), soldProperty.id])
                redirect soldProperty
            }
            '*'{ respond soldProperty, [status: OK] }
        }
    }

    @Transactional
    def delete(SoldProperty soldProperty) {

        if (soldProperty == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        soldProperty.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'soldProperty.label', default: 'Sold Property'), soldProperty.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'soldProperty.label', default: 'Sold Property'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
