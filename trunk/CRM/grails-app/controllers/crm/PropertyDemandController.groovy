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
        respond new PropertyDemand(params)
    }

    @Transactional
    def save(PropertyDemand propertyDemand) {
        if (propertyDemand == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
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
