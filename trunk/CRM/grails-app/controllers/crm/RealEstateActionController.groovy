package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RealEstateActionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RealEstateAction.list(params), model:[realEstateActionCount: RealEstateAction.count()]
    }

    def show(RealEstateAction realEstateAction) {
        respond realEstateAction
    }

    def create() {
		RealEstateAction rea=new RealEstateAction(params);
		rea.concession=Concession.get(params.cid);
		if(null==rea.concession){
			rea.errors.rejectValue('concession',message(code:'realEstateContact.concession.required.error').toString());
		}
        respond rea
    }

    @Transactional
    def save(RealEstateAction realEstateAction) {
        if (realEstateAction == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (realEstateAction.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond realEstateAction.errors, view:'create'
            return
        }

        realEstateAction.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'realEstateAction.label', default: 'RealEstateAction'), realEstateAction.id])
                redirect realEstateAction
            }
            '*' { respond realEstateAction, [status: CREATED] }
        }
    }

    def edit(RealEstateAction realEstateAction) {
        respond realEstateAction
    }

    @Transactional
    def update(RealEstateAction realEstateAction) {
        if (realEstateAction == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (realEstateAction.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond realEstateAction.errors, view:'edit'
            return
        }

        realEstateAction.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'realEstateAction.label', default: 'RealEstateAction'), realEstateAction.id])
                redirect realEstateAction
            }
            '*'{ respond realEstateAction, [status: OK] }
        }
    }

    @Transactional
    def delete(RealEstateAction realEstateAction) {

        if (realEstateAction == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        realEstateAction.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'realEstateAction.label', default: 'RealEstateAction'), realEstateAction.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'realEstateAction.label', default: 'RealEstateAction'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
