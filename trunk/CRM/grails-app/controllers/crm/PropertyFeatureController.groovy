package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PropertyFeatureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PropertyFeature.list(params), model:[propertyFeatureCount: PropertyFeature.count()]
    }

    def show(PropertyFeature propertyFeature) {
        respond propertyFeature
    }

    def create() {
        respond new PropertyFeature(params)
    }

    @Transactional
    def save(PropertyFeature propertyFeature) {
        if (propertyFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (propertyFeature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond propertyFeature.errors, view:'create'
            return
        }

        propertyFeature.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'propertyFeature.label', default: 'PropertyFeature'), propertyFeature.id])
                redirect propertyFeature
            }
            '*' { respond propertyFeature, [status: CREATED] }
        }
    }

    def edit(PropertyFeature propertyFeature) {
        respond propertyFeature
    }

    @Transactional
    def update(PropertyFeature propertyFeature) {
        if (propertyFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (propertyFeature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond propertyFeature.errors, view:'edit'
            return
        }

        propertyFeature.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'propertyFeature.label', default: 'PropertyFeature'), propertyFeature.id])
                redirect propertyFeature
            }
            '*'{ respond propertyFeature, [status: OK] }
        }
    }

    @Transactional
    def delete(PropertyFeature propertyFeature) {

        if (propertyFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        propertyFeature.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'propertyFeature.label', default: 'PropertyFeature'), propertyFeature.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'propertyFeature.label', default: 'PropertyFeature'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
