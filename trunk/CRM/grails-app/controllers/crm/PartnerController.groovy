package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.context.MessageSource;
import org.springframework.web.multipart.MultipartFile

@Transactional(readOnly = true)
class PartnerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

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
		//////
		partner.address = new Address();
		partner.address.properties=params.address;
		partner.address.validate();
		
		def f = request.getFile('photo')
		if(f.empty) {
			if(partner.isAgent){
				partner.errors.rejectValue('',message(code:'partner.photo.required.error').toString());
			}
		}
		
		if (partner.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond partner.errors, view:'create'
			return
		}
		
		if (partner.address.hasErrors()) {
			transactionStatus.setRollbackOnly();
			respond partner.errors, view:'create', controller:'partner'
			return
		}
		
		if(partner.address.save(flush: true)){
			println "Address SAVED";
			if(partner.save(flush: true)){
				println "Partner SAVED";
				if(f){
					String imageContainer=grailsApplication.config.getProperty('web.realPath')+File.separatorChar+grailsApplication.config.getProperty('web.image.partner')+File.separatorChar+partner.id;
					String filePath=imageContainer + File.separatorChar + "profile.jpg";
					File dire=new File(imageContainer);
					dire.mkdirs()
					f.transferTo(new File(filePath));
				}
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
		
		String imageContainer=grailsApplication.config.getProperty('web.realPath')+File.separatorChar+grailsApplication.config.getProperty('web.image.partner')+File.separatorChar+partner.id;
		String filePath=imageContainer + File.separatorChar + "profile.jpg";
		def f = request.getFile('photo')
		if(!f.empty) {
			File dire=new File(imageContainer);
			dire.mkdirs()
			f.transferTo(new File(filePath));
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
