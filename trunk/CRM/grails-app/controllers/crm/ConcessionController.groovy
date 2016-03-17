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
        respond concession
    }

    def create() {
		Concession concession=new Concession(soldByCompany:false, isActive:true, contract:new Contract());
		
		respond concession, model:[managedProperty: new ManagedProperty(address:new Address(), placedBillboards:0, valueDegree:0), building:new Building(), featureByBuildingCommand: new FeatureByBuildingCommand(BuildingFeature.getEmptyFeatureByBuildingListForEachBuildingFeature()), featureByPropertyCommand: new FeatureByPropertyCommand(PropertyFeature.getEmptyFeatureByPropertyListForEachPropertyFeature()), displayBuilding: false];
    }
	
    @Transactional
    def save(Concession concession, ManagedProperty managedProperty, Building building, FeatureByBuildingCommand featureByBuildingCommand, FeatureByPropertyCommand featureByPropertyCommand, boolean hasBuilding, boolean addImages) {
        if (concession == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		concession.contract.date=concession.startDate;
		managedProperty.addedDate=concession.startDate;
		building.managedProperty=managedProperty;
		concession.validate();
		managedProperty.validate();
		managedProperty.address.validate();
		building.validate();
		concession.contract.validate();

		if (concession.commissionAmount > 0 && concession.commissionPercentage > 0) {
			concession.errors.rejectValue('',message(code:'concession.duplicate.commission.error').toString());
		}
		if (concession.commissionAmount == 0 && concession.commissionPercentage == 0) {
			concession.errors.rejectValue('',message(code:'concession.commission.required.error').toString());
		}
		/*if (concession.adText) {
			if (concession.adText.charAt(concession.adText.length()-1)!='.') {
				concession.errors.rejectValue('adText',message(code:'concession.adText.point.finish.required.error').toString());
			}
		}
		if (concession.adTitle) {
			if (concession.adTitle.charAt(concession.adTitle.length()-1)!='.') {
				concession.errors.rejectValue('adTitle',message(code:'concession.adTitle.point.finish.required.error').toString());
			}
		}
		if (concession.adSummary) {
			if (concession.adSummary.charAt(concession.adSummary.length()-1)!='.') {
				concession.errors.rejectValue('adSummary',message(code:'concession.adSummary.point.finish.required.error').toString());
			}
		}
		if (concession.keys) {
			if (!Utils.validatePageKeys(concession.keys)) {
				concession.errors.rejectValue('keys',message(code:'concession.keys.validation.error').toString());
			}
		}*/
		if (managedProperty.area <= 0) {
			managedProperty.errors.rejectValue('area',message(code:'managedProperty.area.required.error').toString());
		}
		if (managedProperty.price <= 0) {
			managedProperty.errors.rejectValue('price',message(code:'managedProperty.price.required.error').toString());
		}
		if (managedProperty.clientInitialPrice <= 0) {
			managedProperty.errors.rejectValue('clientInitialPrice',message(code:'managedProperty.client.initial.price.required.error').toString());
		}
		if (managedProperty.value <= 0) {
			managedProperty.errors.rejectValue('value',message(code:'managedProperty.value.required.error').toString());
		}
        if (concession.hasErrors() || concession.contract.hasErrors() || managedProperty.address.hasErrors() || managedProperty.hasErrors() || building.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond concession, view:'create', model:[managedProperty: managedProperty, building: building, featureByBuildingCommand: featureByBuildingCommand, featureByPropertyCommand: featureByPropertyCommand, displayBuilding: hasBuilding, addImages:addImages];
            return
        }
		boolean saved=true;
		if(!concession.contract.save(flush:true)){
			GUtils.printErrors(concession.contract,"contract save");
			transactionStatus.setRollbackOnly();
			saved=false;
			throw new CRMException("contract save Error");
		}else{
			if(!managedProperty.address.save(flush:true)){
				GUtils.printErrors(managedProperty.address,"managedProperty.address save");
				transactionStatus.setRollbackOnly();
				saved=false;
				throw new CRMException("managedProperty.address save");
			}else{
				if(!managedProperty.save(flush:true)){
					GUtils.printErrors(managedProperty,"managedProperty save");
					transactionStatus.setRollbackOnly();
					saved=false;
					throw new CRMException("managedProperty save");
				}else{
					if(!managedProperty.save(flush:true)){
						GUtils.printErrors(managedProperty,"managedProperty save 1");
						transactionStatus.setRollbackOnly();
						saved=false;
						throw new CRMException("managedProperty save 1");
					}else{
						concession.addToManagedProperties(managedProperty);
						if(!concession.save(flush:true)){
							GUtils.printErrors(concession,"concession save");
							transactionStatus.setRollbackOnly();
							saved=false;
							throw new CRMException("concession save");
						}else{
							managedProperty.addToConcessions(concession);
							if(!managedProperty.save(flush:true)){
								GUtils.printErrors(managedProperty,"managedProperty save 2");
								transactionStatus.setRollbackOnly();
								saved=false;
								throw new CRMException("managedProperty save 2");
							}else{
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
			}
		}
		
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'concession.label', default: 'Concession'), concession.id])
				if(addImages/* && saved*/){
					redirect(controller:'upload', action:'images', params: [obj:'property', oid: managedProperty.id])
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
		concession.contract.validate();
        if (concession.hasErrors() || concession.contract.hasErrors()) {
            transactionStatus.setRollbackOnly();
            respond concession.errors, view:'edit';
            return;
        }
		concession.contract.save flush:true;
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
