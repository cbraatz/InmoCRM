package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingFeatureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BuildingFeature.list(params), model:[buildingFeatureCount: BuildingFeature.count()]
    }

    def show(BuildingFeature buildingFeature) {
        respond buildingFeature
    }

    def create() {
        respond new BuildingFeature(params)
    }

    @Transactional
    def save(BuildingFeature buildingFeature) {
        if (buildingFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingFeature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingFeature.errors, view:'create'
            return
        }

        buildingFeature.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'buildingFeature.label', default: 'BuildingFeature'), buildingFeature.id])
                redirect buildingFeature
            }
            '*' { respond buildingFeature, [status: CREATED] }
        }
    }

    def edit(BuildingFeature buildingFeature) {
        respond buildingFeature
    }

    @Transactional
    def update(BuildingFeature buildingFeature) {
        if (buildingFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingFeature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingFeature.errors, view:'edit'
            return
        }

        buildingFeature.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'buildingFeature.label', default: 'BuildingFeature'), buildingFeature.id])
                redirect buildingFeature
            }
            '*'{ respond buildingFeature, [status: OK] }
        }
    }

    @Transactional
    def delete(BuildingFeature buildingFeature) {

        if (buildingFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        buildingFeature.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'buildingFeature.label', default: 'BuildingFeature'), buildingFeature.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'buildingFeature.label', default: 'BuildingFeature'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
