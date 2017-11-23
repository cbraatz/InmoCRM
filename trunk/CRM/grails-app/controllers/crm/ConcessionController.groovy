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
		}
        respond concession
    }

    def create() {
		Concession concession=new Concession(isActive:true, isForRent:false, totalPrice:new Double(0), totalCommission:new Double(0));
		respond concession, model:[contract:new Contract(internalID:Utils.getUUID(), isCurrentContract:true), managedProperty: new ManagedProperty(address:new Address(), placedBillboards:0, valueDegree:0), building:new Building(), featureByBuildingCommand: new FeatureByBuildingCommand(BuildingFeature.getEmptyFeatureByBuildingListForEachBuildingFeature()), featureByPropertyCommand: new FeatureByPropertyCommand(PropertyFeature.getEmptyFeatureByPropertyListForEachPropertyFeature()), displayBuilding: false];
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
				/*if(!managedProperty.save(flush:true)){
					GUtils.printErrors(managedProperty,"managedProperty save 1");
					transactionStatus.setRollbackOnly();
					saved=false;
					throw new CRMException("managedProperty save 1");
				}else{*/
				//concession.addToManagedProperties(managedProperty);
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
						/*managedProperty.addToConcessions(concession);
						if(!managedProperty.save(flush:true)){
							GUtils.printErrors(managedProperty,"managedProperty save 2");
							transactionStatus.setRollbackOnly();
							saved=false;
							throw new CRMException("managedProperty save 2");
							}else{
						String errmsg=this.updateCommissionsByPropertyIfNecessary(concession);
						if(null != errmsg) {
							GUtils.printErrors(managedProperty,errmsg);
							transactionStatus.setRollbackOnly();
							saved=false;
							throw new CRMException(errmsg);
						}*/
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
								if(saved){
									featureByPropertyCommand.pfitems.each{
										if(it.value > 0){
											it.managedProperty=managedProperty;
											if(!it.save(flush:true)){
												GUtils.printErrors(it,"featureByProperty save. PropertyFeature = "+it.propertyFeature?.name);
												transactionStatus.setRollbackOnly();
												throw new CRMException("featureByProperty save. PropertyFeature = "+it.propertyFeature?.name);
											}
										}
									}
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
