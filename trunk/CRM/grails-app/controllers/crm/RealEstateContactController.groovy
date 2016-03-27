package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RealEstateContactController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RealEstateContact.list(params), model:[realEstateContactCount: RealEstateContact.count()]
    }

    def show(RealEstateContact realEstateContact) {
        respond realEstateContact
    }

    def create() {
        RealEstateContact rec=new RealEstateContact(params);
		rec.concession=Concession.get(params.cid);
		if(null==rec.concession){
			rec.errors.rejectValue('concession',message(code:'realEstateContact.concession.required.error').toString());
		}
        respond rec
    }

    @Transactional
    def save(RealEstateContact realEstateContact) {
        if (realEstateContact == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (realEstateContact.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond realEstateContact.errors, view:'create'
            return
        }

        realEstateContact.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'realEstateContact.label', default: 'RealEstateContact'), realEstateContact.id])
                redirect realEstateContact
            }
            '*' { respond realEstateContact, [status: CREATED] }
        }
    }

    def edit(RealEstateContact realEstateContact) {
        respond realEstateContact
    }

    @Transactional
    def update(RealEstateContact realEstateContact) {
        if (realEstateContact == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (realEstateContact.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond realEstateContact.errors, view:'edit'
            return
        }

        realEstateContact.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'realEstateContact.label', default: 'RealEstateContact'), realEstateContact.id])
                redirect realEstateContact
            }
            '*'{ respond realEstateContact, [status: OK] }
        }
    }

    @Transactional
    def delete(RealEstateContact realEstateContact) {

        if (realEstateContact == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        realEstateContact.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'realEstateContact.label', default: 'RealEstateContact'), realEstateContact.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'realEstateContact.label', default: 'RealEstateContact'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
