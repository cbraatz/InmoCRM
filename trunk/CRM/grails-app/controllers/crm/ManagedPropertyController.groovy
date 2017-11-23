package crm

import static org.springframework.http.HttpStatus.*
import crm.commands.FeatureByPropertyCommand;
import crm.exception.CRMException
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ManagedPropertyController {

    static allowedMethods = [save: "POST", update: "PUT"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ManagedProperty.list(params), model:[managedPropertyCount: ManagedProperty.count()]
    }

    def show(ManagedProperty managedProperty) {
		Concession conc=managedProperty.concession;
		if(null==conc) {
			managedProperty.errors.rejectValue('',message(code:'concession.is.inactive.error').toString());
		}
		if (conc.crmUser.partner.isAgent.booleanValue()==false) {
			managedProperty.errors.rejectValue('',message(code:'concession.crmUser.agent.required.error').toString());
		}
		if (!managedProperty.hasImagesForWeb()) {
			managedProperty.errors.rejectValue('',message(code:'managedProperty.images.required.error').toString());
		}
		
        respond managedProperty
    }

    def create() {
        respond new ManagedProperty(params)
    }
	def addEditFiles(ManagedProperty managedProperty){
		redirect(controller:'upload', action:'edit', params: [obj:'property', oid: managedProperty.id])
	}
    @Transactional
    def save(ManagedProperty managedProperty) {
        if (managedProperty == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (managedProperty.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond managedProperty.errors, view:'create'
            return
        }

        boolean saved=(managedProperty.save(flush:true));
		String msg=updateCommissionsByPropertyIfNecessary(managedProperty);
		if (!saved){
			managedProperty.errors.rejectValue('',message(code: 'managedProperty.save.error'));
			respond managedProperty.errors, view:'create'
			return
		}
		if (null!=msg && !msg.isEmpty()){
			if (!msg.isEmpty()){
				managedProperty.errors.rejectValue('',msg);
				transactionStatus.setRollbackOnly()
				respond managedProperty.errors, view:'create'
				return
			}
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'managedProperty.label', default: 'ManagedProperty'), managedProperty.id])
                redirect managedProperty
            }
            '*' { respond managedProperty, [status: CREATED] }
        }
    }
	
	
	
	/*def commissions(ManagedProperty managedProperty){
		redirect(controller:'commissionByProperty', action:'create', params: [id: managedProperty?.id])
	}*/
	
	@Transactional
	def sold(Concession concession) {
		if (concession == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		concession.contract.validate();
		if (concession.hasErrors() || concession.contract.hasErrors()) {
			transactionStatus.setRollbackOnly();
			respond concession.errors, view:'edit';
			return;
		}
	}
	
    def edit(ManagedProperty managedProperty) {
        respond managedProperty, model:[featureByPropertyCommand: new FeatureByPropertyCommand(FeatureByProperty.getStoredFeatureByPropertyListForEachPropertyFeature(managedProperty))]
    }

    @Transactional
    def update(ManagedProperty managedProperty, FeatureByPropertyCommand featureByPropertyCommand) {
        if (managedProperty == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        if (managedProperty.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond managedProperty.errors, view:'edit'
            return
        }

        /*String msg=updateCommissionsByPropertyIfNecessary(managedProperty);
		
		if (null!=msg && !msg.isEmpty()){
			if (!msg.isEmpty()){
				managedProperty.errors.rejectValue('',msg);
				transactionStatus.setRollbackOnly()
				respond managedProperty.errors, view:'edit'
				return
			}
		}*/
		
		if(managedProperty.save(flush:true)){
			FeatureByProperty fbp=null;
			featureByPropertyCommand.pfitems.each{
				if(it.value > 0){
					it.managedProperty=managedProperty;
					fbp=FeatureByProperty.findByManagedPropertyAndPropertyFeature(managedProperty,it.propertyFeature);
					if(null!=fbp){
						if(fbp.value != it.value || !fbp.description.equals(it.description)){
							fbp.value=it.value;
							fbp.description=it.description;
							if(!fbp.save(flush:true)){
								GUtils.printErrors(fbp,"featureByProperty save. PropertyFeature = "+fbp.propertyFeature?.name);
								transactionStatus.setRollbackOnly();
								throw new CRMException("featureByProperty save. PropertyFeature = "+fbp.propertyFeature?.name);
							}
						}
					}else{
						if(!it.save(flush:true)){
							GUtils.printErrors(it,"featureByProperty save. PropertyFeature = "+it.propertyFeature?.name);
							transactionStatus.setRollbackOnly();
							throw new CRMException("featureByProperty save. PropertyFeature = "+it.propertyFeature?.name);
						}
					}
				}
			}
		}else{
			throw new CRMException("Error saving managedProperty.");
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'managedProperty.label', default: 'ManagedProperty'), managedProperty.id])
                redirect managedProperty
            }
            '*'{ respond managedProperty, [status: OK] }
        }
    }
	
    /*@Transactional
    def delete(ManagedProperty managedProperty) { //must add cascade delete for building and so on..

        if (managedProperty == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        managedProperty.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'managedProperty.label', default: 'ManagedProperty'), managedProperty.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }*/

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'managedProperty.label', default: 'ManagedProperty'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
