package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.context.MessageSource;

@Transactional(readOnly = true)
class PartnerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Partner.list(params), model:[partnerCount: Partner.count()]
    }

    def show(Partner partner) {
        respond partner
    }

    def create() {
        respond new Partner(params)
    }

    @Transactional
    def save(Partner partner) {
        if (partner == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (partner.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond partner.errors, view:'create'
            return
        }
		
		//////
		partner.address = new Address();
		partner.address.properties=params.address;
		partner.address.validate();
		if (partner.address.hasErrors()) {
			transactionStatus.setRollbackOnly();
			respond partner.errors, view:'create', controller:'partner'
			return
		}
		
		if(partner.address.save(flush: true)){
			println "Address SAVED";
			if(partner.save(flush: true)){
				println "Partner SAVED";
			}else{
				println "Partner DONT SAVED";
				partner.errors.each {
					println it
				}
				partner.delete flush:true
			}
		}else{
			println "Address DONT SAVED";
			partner.address.errors.each {
				println it
			}
		}
		
		///////
		
	    

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'partner.label', default: 'Partner'), partner.id])
                redirect partner
            }
            '*' { respond partner, [status: CREATED] }
        }
    }

    def edit(Partner partner) {
        respond partner
    }

    @Transactional
    def update(Partner partner) {
        if (partner == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (partner.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond partner.errors, view:'edit'
            return
        }

        partner.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'partner.label', default: 'Partner'), partner.id])
                redirect partner
            }
            '*'{ respond partner, [status: OK] }
        }
    }

    @Transactional
    def delete(Partner partner) {

        if (partner == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        partner.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'partner.label', default: 'Partner'), partner.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'partner.label', default: 'Partner'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
