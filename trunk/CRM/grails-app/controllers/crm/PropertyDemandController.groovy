package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

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
		respond pd;
    }

    @Transactional
    def save(PropertyDemand propertyDemand) {
		propertyDemand.addedDate=new Date();
        if (propertyDemand == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		propertyDemand.demandStatus=DemandStatus.findByIsNew(true);
		propertyDemand.offersOnly=false;
		propertyDemand.isPriceRequired=false;
		propertyDemand.validate();
		if(!propertyDemand.isSellDemand){//if it is a buy demand
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
            respond propertyDemand.errors, view:'create'
            return
        }

        propertyDemand.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'propertyDemand.label', default: 'PropertyDemand'), propertyDemand.id])
                redirect propertyDemand
            }
            '*' { respond propertyDemand, [status: CREATED] }
        }
    }

    def edit(PropertyDemand propertyDemand) {
        respond propertyDemand
    }

    @Transactional
    def update(PropertyDemand propertyDemand) {
        if (propertyDemand == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (propertyDemand.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond propertyDemand.errors, view:'edit'
            return
        }

        propertyDemand.save flush:true

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
