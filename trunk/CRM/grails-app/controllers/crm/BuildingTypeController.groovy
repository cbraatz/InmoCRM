package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BuildingType.list(params), model:[buildingTypeCount: BuildingType.count()]
    }

    def show(BuildingType buildingType) {
        respond buildingType
    }

    def create() {
        respond new BuildingType(params)
    }

    @Transactional
    def save(BuildingType buildingType) {
        if (buildingType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingType.errors, view:'create'
            return
        }

        buildingType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'buildingType.label', default: 'BuildingType'), buildingType.id])
                redirect buildingType
            }
            '*' { respond buildingType, [status: CREATED] }
        }
    }

    def edit(BuildingType buildingType) {
        respond buildingType
    }

    @Transactional
    def update(BuildingType buildingType) {
        if (buildingType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingType.errors, view:'edit'
            return
        }

        buildingType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'buildingType.label', default: 'BuildingType'), buildingType.id])
                redirect buildingType
            }
            '*'{ respond buildingType, [status: OK] }
        }
    }

    @Transactional
    def delete(BuildingType buildingType) {

        if (buildingType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        buildingType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'buildingType.label', default: 'BuildingType'), buildingType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'buildingType.label', default: 'BuildingType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
