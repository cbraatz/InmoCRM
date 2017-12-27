package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import crm.commands.FeatureByPropertyCommand
import crm.commands.PropertyFeatureByPropertyDemandCommand;
import crm.exception.CRMException

@Transactional(readOnly = true)
class PropertyDemandController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PropertyDemand.list(params), model:[propertyDemandCount: PropertyDemand.count()]
    }

    def show(PropertyDemand propertyDemand) {
        respond propertyDemand
    }

    def create() {
        PropertyDemand pd=new PropertyDemand();
		pd.isUsageRequired=false;
		//pd.addedDate=new Date(); lo hago en el save()
		respond pd, model:[propertyFeatureByPropertyDemandCommand: new PropertyFeatureByPropertyDemandCommand(PropertyFeatureByPropertyDemand.getEmptyPropertyDemandList())];
    }

    @Transactional
    def save(PropertyDemand propertyDemand, PropertyFeatureByPropertyDemandCommand propertyFeatureByPropertyDemandCommand, boolean isSellDemand) {
		propertyDemand.addedDate=new Date();
        if (propertyDemand == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		if(isSellDemand) {
			propertyDemand.propertyDemandType=PropertyDemandType.getSellDemand();
		}else {
			propertyDemand.propertyDemandType=PropertyDemandType.getBuyDemand();
		}
		propertyDemand.demandStatus=DemandStatus.getNewDemandStatus();
		propertyDemand.offersOnly=false;
		propertyDemand.isPriceRequired=false;
		propertyDemand.validate();
		if(propertyDemand.propertyDemandType.isBuyDemand()){//if it is a buy demand
			if(propertyDemand.isDepartmentRequired){
				if(!propertyDemand.department){
					propertyDemand.errors.rejectValue('department',message(code:'default.null.with.required.message', args: [message(code:'propertyDemand.department.label')]).toString());
				}
			}
			if(propertyDemand.isCityRequired){
				if(!propertyDemand.city){
					propertyDemand.errors.rejectValue('city',message(code:'default.null.with.required.message', args: [message(code:'propertyDemand.city.label')]).toString());
				}
			}
			if(propertyDemand.isNeighborhoodRequired){
				if(!propertyDemand.neighborhood){
					propertyDemand.errors.rejectValue('neighborhood',message(code:'default.null.with.required.message', args: [message(code:'propertyDemand.neighborhood.label')]).toString());
				}
			}
			if(propertyDemand.isAreaRequired){
				if(!propertyDemand.propertyMinArea){
					propertyDemand.errors.rejectValue('propertyMinArea',message(code:'default.null.with.required.message', args: [message(code:'propertyDemand.propertyMinArea.label')]).toString());
				}
				if(!propertyDemand.propertyMaxArea){
					propertyDemand.errors.rejectValue('propertyMaxArea',message(code:'default.null.with.required.message', args: [message(code:'propertyDemand.propertyMaxArea.label')]).toString());
				}
			}
			if(propertyDemand.isZoneRequired){
				if(!propertyDemand.zone){
					propertyDemand.errors.rejectValue('zone',message(code:'default.null.with.required.message', args: [message(code:'propertyDemand.zone.label')]).toString());
				}
			}
			if(propertyDemand.isPropertyTypeRequired){
				if(!propertyDemand.propertyType){
					propertyDemand.errors.rejectValue('propertyType',message(code:'default.null.with.required.message', args: [message(code:'propertyDemand.propertyType.label')]).toString());
				}
			}
			if(propertyDemand.isBuildingTypeRequired){
				if(!propertyDemand.buildingType){
					propertyDemand.errors.rejectValue('buildingType',message(code:'default.null.with.required.message', args: [message(code:'propertyDemand.buildingType.label')]).toString());
				}
			}
			if(propertyDemand.isPriceRequired){
				if(!propertyDemand.pricerice){
					propertyDemand.errors.rejectValue('price',message(code:'default.null.with.required.message', args: [message(code:'propertyDemand.maxPrice.label')]).toString());
				}
			}
			if(propertyDemand.isBuildingConditionRequired){
				if(!propertyDemand.buildingCondition){
					propertyDemand.errors.rejectValue('buildingCondition',message(code:'default.null.with.required.message', args: [message(code:'propertyDemand.buildingCondition.label')]).toString());
				}
			}
			if(propertyDemand.propertyMinArea > propertyDemand.propertyMaxArea){
				propertyDemand.errors.rejectValue('propertyMinArea',message(code:'default.invalid.compared.value.message', args: [message(code:'propertyDemand.propertyMinArea.label'), propertyDemand.propertyMinArea, message(code:'propertyDemand.propertyMaxArea.label'), propertyDemand.propertyMaxArea]).toString());
			}
		}
        if (propertyDemand.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond propertyDemand.errors, view:'create', model:[isSellDemand:isSellDemand,propertyFeatureByPropertyDemandCommand: propertyFeatureByPropertyDemandCommand]
            return
        }

        if(propertyDemand.save(flush:true)) {
			PropertyFeatureByPropertyDemand fbp=null;
			propertyFeatureByPropertyDemandCommand.pfitems.each{
				if((propertyDemand.propertyDemandType.isSellDemand()==true && it.value > 0) || (propertyDemand.propertyDemandType.isBuyDemand()==true && (it.maxValue > 0 || it.minValue > 0))){
					it.propertyDemand=propertyDemand;
					fbp=PropertyFeatureByPropertyDemand.findByPropertyDemandAndPropertyFeature(propertyDemand,it.propertyFeature);
					if(null!=fbp){
						if(fbp.value != it.value || fbp.minValue != it.minValue ||fbp.maxValue != it.maxValue){
							fbp.value=it.value;
							fbp.minValue=it.minValue;
							fbp.maxValue=it.maxValue;
							
							if(!fbp.save(flush:true)){
								GUtils.printErrors(fbp,"PropertyFeatureByPropertyDemand save. PropertyFeature = "+fbp.propertyFeature?.name);
								transactionStatus.setRollbackOnly();
								throw new CRMException("PropertyFeatureByPropertyDemand save. PropertyFeature = "+fbp.propertyFeature?.name);
							}
						}
					}else{
						if(!it.save(flush:true)){
							GUtils.printErrors(it,"PropertyFeatureByPropertyDemand save. PropertyFeature = "+it.propertyFeature?.name);
							transactionStatus.setRollbackOnly();
							throw new CRMException("PropertyFeatureByPropertyDemand save. PropertyFeature = "+it.propertyFeature?.name);
						}
					}
				}
			}
		}else{
			throw new CRMException("Error saving propertyDemand.");
		}
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'propertyDemand.label', default: 'PropertyDemand'), propertyDemand.id])
                redirect propertyDemand
            }
            '*' { respond propertyDemand, [status: CREATED] }
        }
    }

    def edit(PropertyDemand propertyDemand) {
        respond propertyDemand, model:[isSellDemand:(propertyDemand.propertyDemandType.isSellDemand()),propertyFeatureByPropertyDemandCommand: new PropertyFeatureByPropertyDemandCommand(PropertyFeatureByPropertyDemand.getStoredPropertyFeatureByPropertyDemandList(propertyDemand))]
    }

    @Transactional
    def update(PropertyDemand propertyDemand, PropertyFeatureByPropertyDemandCommand propertyFeatureByPropertyDemandCommand, boolean isSellDemand) {
        if (propertyDemand == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		if(isSellDemand) {
			propertyDemand.propertyDemandType=PropertyDemandType.getSellDemand();
		}else {
			propertyDemand.propertyDemandType=PropertyDemandType.getBuyDemand();
		}
		propertyDemand.validate();
        if (propertyDemand.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond propertyDemand.errors, view:'edit', model:[isSellDemand:isSellDemand,propertyFeatureByPropertyDemandCommand: propertyFeatureByPropertyDemandCommand]
            return
        }

		if(propertyDemand.save(flush:true)){
			PropertyFeatureByPropertyDemand fbp=null;
			propertyFeatureByPropertyDemandCommand.pfitems.each{
				it.propertyDemand=propertyDemand;
				fbp=PropertyFeatureByPropertyDemand.findByPropertyDemandAndPropertyFeature(propertyDemand,it.propertyFeature);
				if(null!=fbp){
					if((propertyDemand.propertyDemandType.isSellDemand()==true && it.value == 0) || (propertyDemand.propertyDemandType.isBuyDemand()==true && (it.maxValue == 0 && it.minValue == 0))){
						fbp.delete flush:true
					}else {
						if(fbp.value != it.value || fbp.minValue != it.minValue ||fbp.maxValue != it.maxValue){
							if(it.value > 0 || it.minValue > 0 || it.maxValue > 0){
								fbp.value=it.value;
								fbp.minValue=it.minValue;
								fbp.maxValue=it.maxValue;
								
								if(!fbp.save(flush:true)){
									GUtils.printErrors(fbp,"PropertyFeatureByPropertyDemand save. PropertyFeature = "+fbp.propertyFeature?.name);
									transactionStatus.setRollbackOnly();
									throw new CRMException("PropertyFeatureByPropertyDemand save. PropertyFeature = "+fbp.propertyFeature?.name);
								}
							}else {
								fbp.delete flush:true
							}
						}
					}
				}else{
					if((propertyDemand.propertyDemandType.isSellDemand()==true && it.value > 0) || (propertyDemand.propertyDemandType.isBuyDemand()==true && (it.maxValue > 0 || it.minValue > 0))){
						if(!it.save(flush:true)){
							GUtils.printErrors(it,"PropertyFeatureByPropertyDemand save. PropertyFeature = "+it.propertyFeature?.name);
							transactionStatus.setRollbackOnly();
							throw new CRMException("PropertyFeatureByPropertyDemand save. PropertyFeature = "+it.propertyFeature?.name);
						}
					}
				}
			}
		}else{
			throw new CRMException("Error saving propertyDemand.");
		}

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'propertyDemand.label', default: 'PropertyDemand'), propertyDemand.id])
                redirect propertyDemand
            }
            '*'{ respond propertyDemand, [status: OK] }
        }
    }

    @Transactional
    def delete(PropertyDemand propertyDemand) {

        if (propertyDemand == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        propertyDemand.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'propertyDemand.label', default: 'PropertyDemand'), propertyDemand.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'propertyDemand.label', default: 'PropertyDemand'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
