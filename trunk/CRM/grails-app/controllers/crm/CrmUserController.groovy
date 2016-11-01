package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CrmUserController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CrmUser.list(params), model:[crmUserCount: CrmUser.count()]
    }

    def show(CrmUser crmUser) {
        respond crmUser
    }

    def create() {
		CrmUser usr=new CrmUser();
		//def uu=session.user;
		usr.crmUser=session.user;
        respond usr
    }

    @Transactional
    def save(CrmUser crmUser) {
        if (crmUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (crmUser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond crmUser.errors, view:'create'
            return
        }
		if(params.pass2!=crmUser.password){
			crmUser.errors.rejectValue('',message(code:'crmUser.password.doesnotmatch').toString());
			transactionStatus.setRollbackOnly();
            respond crmUser.errors, view:'create';
            return;
		}
		crmUser.password=crmUser.getEncodedPassword();
        crmUser.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'crmUser.label', default: 'CrmUser'), crmUser.id])
                redirect crmUser
            }
            '*' { respond crmUser, [status: CREATED] }
        }
    }

    def edit(CrmUser crmUser) {
        respond crmUser
    }

    @Transactional
    def update(CrmUser crmUser) {
        if (crmUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		if(crmUser.password == null){
			String tempName=crmUser.name;
			String tempEmailAddress=crmUser.emailAddress;
			Boolean tempIsAdmin=crmUser.isAdmin;
			Boolean tempIsActive=crmUser.isActive;
			Partner tempPartner=crmUser.partner;
			crmUser.refresh();//get crmUser from DB
			crmUser.name= tempName;
			crmUser.emailAddress= tempEmailAddress;
			crmUser.isAdmin= tempIsAdmin;
			crmUser.isActive= tempIsActive;
			crmUser.partner= tempPartner;
			crmUser.validate();
		}else{
			if(params.pass2!=crmUser.password){
				crmUser.errors.rejectValue('',message(code:'crmUser.password.doesnotmatch').toString());
				transactionStatus.setRollbackOnly();
	            respond crmUser.errors, view:'create';
	            return;
			}
			crmUser.password=crmUser.getEncodedPassword();
		}
        if (crmUser.hasErrors()) {
	        transactionStatus.setRollbackOnly()
	        respond crmUser.errors, view:'edit'
	        return
        }
		
        crmUser.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'crmUser.label', default: 'CrmUser'), crmUser.id])
                redirect crmUser
            }
            '*'{ respond crmUser, [status: OK] }
        }
    }

    @Transactional
    def delete(CrmUser crmUser) {

        if (crmUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        crmUser.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'crmUser.label', default: 'CrmUser'), crmUser.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'crmUser.label', default: 'CrmUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
