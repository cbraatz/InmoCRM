package crm

import static org.springframework.http.HttpStatus.*
import crm.commands.FeatureByBuildingCommand
import crm.commands.FeatureByPropertyCommand;
import crm.exception.CRMException
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Building.list(params), model:[buildingCount: Building.count()]
    }

    def show(Building building) {
        respond building
    }

    def create() {
        respond new Building(params)
    }

    @Transactional
    def save(Building building) {
        if (building == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (building.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond building.errors, view:'create'
            return
        }

        building.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'building.label', default: 'Building'), building.id])
                redirect building
            }
            '*' { respond building, [status: CREATED] }
        }
    }

    def edit(Building building) {
        respond building, model:[featureByBuildingCommand: new FeatureByBuildingCommand(FeatureByBuilding.getStoredFeatureByBuildingListForEachBuildingFeature(building))]
    }

    @Transactional
    def update(Building building, FeatureByBuildingCommand featureByBuildingCommand) {
        if (building == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (building.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond building.errors, view:'edit'
            return
        }

        if(building.save(flush:true)){
			FeatureByBuilding fbp=null;
			featureByBuildingCommand.bfitems.each{
				if(it.value > 0){
					it.building=building;
					fbp=FeatureByBuilding.findByBuildingAndBuildingFeature(building,it.buildingFeature);
					if(null!=fbp){
						if(fbp.value != it.value || !fbp.description.equals(it.description)){
							fbp.value=it.value;
							fbp.description=it.description;
							if(!fbp.save(flush:true)){
								GUtils.printErrors(fbp,"featureByBuilding save. BuildingFeature = "+fbp.buildingFeature?.name);
								transactionStatus.setRollbackOnly();
								throw new CRMException("featureByBuilding save. BuildingFeature = "+fbp.buildingFeature?.name);
							}
						}
					}else{
						if(!it.save(flush:true)){
							GUtils.printErrors(it,"featureByBuilding save. BuildingFeature = "+it.buildingFeature?.name);
							transactionStatus.setRollbackOnly();
							throw new CRMException("featureByBuilding save. BuildingFeature = "+it.buildingFeature?.name);
						}
					}
				}
			}
		}else{
			throw new CRMException("Error saving building.");
		}

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'building.label', default: 'Building'), building.id])
                redirect building
            }
            '*'{ respond building, [status: OK] }
        }
    }

    /*@Transactional needed to implement cascade delete
    def delete(Building building) {

        if (building == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        building.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'building.label', default: 'Building'), building.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }*/

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'building.label', default: 'Building'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
