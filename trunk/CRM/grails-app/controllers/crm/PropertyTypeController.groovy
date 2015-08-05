package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PropertyTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PropertyType.list(params), model:[propertyTypeCount: PropertyType.count()]
    }

    def show(PropertyType propertyType) {
        respond propertyType
    }

    def create() {
        respond new PropertyType(params)
    }

    @Transactional
    def save(PropertyType propertyType) {
        if (propertyType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (propertyType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond propertyType.errors, view:'create'
            return
        }

        propertyType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'propertyType.label', default: 'PropertyType'), propertyType.id])
                redirect propertyType
            }
            '*' { respond propertyType, [status: CREATED] }
        }
    }

    def edit(PropertyType propertyType) {
        respond propertyType
    }

    @Transactional
    def update(PropertyType propertyType) {
        if (propertyType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (propertyType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond propertyType.errors, view:'edit'
            return
        }

        propertyType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'propertyType.label', default: 'PropertyType'), propertyType.id])
                redirect propertyType
            }
            '*'{ respond propertyType, [status: OK] }
        }
    }

    @Transactional
    def delete(PropertyType propertyType) {

        if (propertyType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        propertyType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'propertyType.label', default: 'PropertyType'), propertyType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'propertyType.label', default: 'PropertyType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
