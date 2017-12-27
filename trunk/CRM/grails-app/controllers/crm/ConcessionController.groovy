package crm

import static org.springframework.http.HttpStatus.*

import java.util.Date;

import org.hibernate.collection.internal.PersistentSet

import crm.commands.FeatureByBuildingCommand
import crm.commands.FeatureByPropertyCommand
import crm.exception.CRMException
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ConcessionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Concession.list(params), model:[concessionCount: Concession.count()]
    }

    def show(Concession concession) {
		Contract c=concession.getCurrentContract();
		if(null==c){
			concession.errors.rejectValue('',message(code:'concession.current.contract.required.error').toString());
		}else{
			if(null==c.uploadedDocument){
				concession.errors.rejectValue('',message(code:'concession.current.contract.document.required.error').toString());
			}
		}
        respond concession
    }

    def create() {
		PropertyDemand pd;
		ManagedProperty mp;
		Concession concession;
		FeatureByBuildingCommand featureByBuildingCommand= new FeatureByBuildingCommand(BuildingFeature.getEmptyFeatureByBuildingListForEachBuildingFeature());//hago aca en vez de abajo donde esta comentado xq como aun buildingFeatureByPropertyDemand no esta implementado no puedo inportar de un pd
		FeatureByPropertyCommand featureByPropertyCommand;//se inicializa mas abajo ya que toma los de pd
		if(null != params.pdid) {
			pd=PropertyDemand.get(params.pdid);
			if(null!=pd) {
				if(pd.isSellDemand()) {
					featureByPropertyCommand= new FeatureByPropertyCommand(PropertyFeature.extractFeatureByPropertyListFromExistingPropertyDemand(pd));
					//featureByBuildingCommand= new FeatureByBuildingCommand(BuildingFeature.getEmptyFeatureByBuildingListForEachBuildingFeature());aun no implementado building features en PropertyDemand
					mp=new ManagedProperty(address:new Address(zone:pd.zone, city:pd.city, neighborhood:pd.neighborhood), placedBillboards:0, valueDegree:0, propertyType:pd.propertyType, title:pd.name, clientInitialPrice:pd.price, currency:pd.currency); 
					concession= new Concession(isActive:true, isForRent:false, totalPrice:new Double(0), totalCommission:new Double(0), propertyDemand:pd);
				}
			}
		}
		if((null!=pd && !pd.isSellDemand()) || null == params.pdid || null==pd) {
			featureByPropertyCommand= new FeatureByPropertyCommand(PropertyFeature.getEmptyFeatureByPropertyListForEachPropertyFeature());
			//featureByBuildingCommand= new FeatureByBuildingCommand(BuildingFeature.getEmptyFeatureByBuildingListForEachBuildingFeature()); //ya lo hice mas arriba
			mp=new ManagedProperty(address:new Address(), placedBillboards:0, valueDegree:0);
			concession= new Concession(isActive:true, isForRent:false, totalPrice:new Double(0), totalCommission:new Double(0));
		}
		respond concession, model:[contract:new Contract(internalID:Utils.getUUID(), isCurrentContract:true), managedProperty: mp, building:new Building(), featureByBuildingCommand: featureByBuildingCommand, featureByPropertyCommand: featureByPropertyCommand, displayBuilding: false];
    }
	def addEditFiles(Concession concession){
		redirect(controller:'uploadedDocument', action:'edit', params: [obj:'concession', oid: concession.id])
	}
    @Transactional
    def save(Concession concession, Contract contract, ManagedProperty managedProperty, Building building, FeatureByBuildingCommand featureByBuildingCommand, FeatureByPropertyCommand featureByPropertyCommand, boolean hasBuilding, boolean addImages) {
        if (concession == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

		contract.startDate=concession.startDate;
		contract.endDate=concession.endDate;
		contract.concession=concession;
		managedProperty.addedDate=concession.startDate;
		managedProperty.concession=concession;
		building.managedProperty=managedProperty;
		concession.validate();
		managedProperty.validate();
		managedProperty.address.validate();
		building.validate();
		contract.validate();

		if (managedProperty.commissionAmount <= 0) {
			concession.errors.rejectValue('',message(code:'concession.managedProperty.commission.required.error').toString());
		}
		if (managedProperty.area <= 0) {
			managedProperty.errors.rejectValue('area',message(code:'managedProperty.area.required.error').toString());
		}
		if (managedProperty.price <= 0) {
			managedProperty.errors.rejectValue('price',message(code:'managedProperty.price.required.error').toString());
		}
		if (null==managedProperty.clientInitialPrice || managedProperty.clientInitialPrice.isEmpty()) {
			managedProperty.errors.rejectValue('clientInitialPrice',message(code:'managedProperty.client.initial.price.required.error').toString());
		}
		if (managedProperty.value <= 0) {
			managedProperty.errors.rejectValue('value',message(code:'managedProperty.value.required.error').toString());
		}
		if (concession.crmUser.partner.isAgent.booleanValue()==false) {
			concession.errors.rejectValue('crmUser',message(code:'concession.crmUser.agent.required.error').toString());
		}
        if (concession.hasErrors() || contract.hasErrors() || managedProperty.address.hasErrors() || managedProperty.hasErrors() || building.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond concession, view:'create', model:[contract:contract, managedProperty: managedProperty, building: building, featureByBuildingCommand: featureByBuildingCommand, featureByPropertyCommand: featureByPropertyCommand, displayBuilding: hasBuilding, addImages:addImages];
            return
        }
		
		concession.totalPrice=managedProperty.price;//asignando el mismo precio y comision a la concesión que el del ManagedProperty (se puede mantener así mientras cada concesión tenga solo un ManagedProperty)
		concession.totalCommission=managedProperty.commissionAmount;
		boolean saved=true;
		
		if(!managedProperty.address.save(flush:true)){
			GUtils.printErrors(managedProperty.address,"managedProperty.address save");
			transactionStatus.setRollbackOnly();
			saved=false;
			throw new CRMException("managedProperty.address save");
		}else{
			if(!concession.save(flush:true)){
				GUtils.printErrors(concession,"concession save");
				transactionStatus.setRollbackOnly();
				saved=false;
				throw new CRMException("concession save");
			}else{
				if(!managedProperty.save(flush:true)){
					GUtils.printErrors(managedProperty,"managedProperty save");
					transactionStatus.setRollbackOnly();
					saved=false;
					throw new CRMException("managedProperty save");
				}else{
					if(!contract.save(flush:true)){
						GUtils.printErrors(contract,"contract save");
						transactionStatus.setRollbackOnly();
						saved=false;
						throw new CRMException("contract save Error");
					}else{
						featureByPropertyCommand.pfitems.each{
							if(it.value > 0){
								it.managedProperty=managedProperty;
								if(!it.save(flush:true)){
									GUtils.printErrors(it,"featureByProperty save. PropertyFeature = "+it.propertyFeature?.name);
									transactionStatus.setRollbackOnly();
									saved=false;
									throw new CRMException("featureByProperty save. PropertyFeature = "+it.propertyFeature?.name);
								}
							}
						}
						if (saved) {
							if(hasBuilding){
								building.managedProperty=managedProperty;
								if(!building.save(flush:true)){
									GUtils.printErrors(building,"building save");
									transactionStatus.setRollbackOnly();
									saved=false;
									throw new CRMException("building save");
								}else{
									featureByBuildingCommand.bfitems.each{
										if(it.value > 0){
											it.building=building;
											if(!it.save(flush:true)){
												GUtils.printErrors(it,"featureByBuilding save. BuildingFeature = "+it.buildingFeature?.name);
												transactionStatus.setRollbackOnly();
												saved=false;
												throw new CRMException("featureByBuilding save. BuildingFeature = "+it.buildingFeature?.name);
											}
										}
									}
								}
							}
							if(saved && concession.propertyDemand!=null) {
								concession.propertyDemand.demandStatus=DemandStatus.getTakenDemandStatus();
								if(!concession.propertyDemand.save(flush:true)){
									GUtils.printErrors(concession.propertyDemand,"Concession.propertyDemand = "+concession?.propertyDemand?.id);
									transactionStatus.setRollbackOnly();
									saved=false;
									throw new CRMException("Concession.propertyDemand.demandStatus update failed. Concession.propertyDemand = "+concession?.propertyDemand?.id);
								}
							}
						}
					}
				}
			}
		}
		
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'concession.label', default: 'Concession'), concession.id])
				if(addImages/* && saved*/){
					redirect(controller:'upload', action:'edit', params: [obj:'property', oid: managedProperty.id])
				}else{
					redirect concession//, view:'create', model:[managedProperty: managedProperty, building: building, featureByBuildingCommand: featureByBuildingCommand, featureByPropertyCommand: featureByPropertyCommand, displayBuilding: hasBuilding, addImages:addImages];
				}
            }
            '*' { respond concession, [status: CREATED] }
        }
    }
	
	
    def edit(Concession concession) {
        //respond concession, model:[managedProperty: manProp, building: bui, featureByBuildingCommand: new FeatureByBuildingCommand(bui?.featuresByBuilding), featureByPropertyCommand: new FeatureByPropertyCommand(manProp?.featuresByProperty), displayBuilding: (bui ? true : false), addImages:true];
		respond concession
	}
	
    @Transactional
    def update(Concession concession) {
        if (concession == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		//concession.contract.validate();
        if (concession.hasErrors()/* || concession.contract.hasErrors()*/) {
            transactionStatus.setRollbackOnly();
            respond concession.errors, view:'edit';
            return;
        }
		//concession.contract.save flush:true;
        concession.save flush:true;

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'concession.label', default: 'Concession'), concession.id])
                redirect concession
            }
            '*'{ respond concession, [status: OK] }
        }
    }

    @Transactional
    def delete(Concession concession) {

        if (concession == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        concession.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'concession.label', default: 'Concession'), concession.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'concession.label', default: 'Concession'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
