package crm

import static org.springframework.http.HttpStatus.*
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
        respond building
    }

    @Transactional
    def update(Building building) {
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

        building.save flush:true

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
