package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AgentCommentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AgentComment.list(params), model:[agentCommentCount: AgentComment.count()]
    }

    def show(AgentComment agentComment) {
        respond agentComment
    }

    def create() {
		AgentComment ac=new AgentComment(params);
		ac.crmUser=CrmUser.get(params.cid);
		if(null==ac.crmUser){
			ac.errors.rejectValue('crmUser',message(code:'agentComment.crmUser.required.error').toString());
		}
		respond ac
    }

    @Transactional
    def save(AgentComment agentComment) {
        if (agentComment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (agentComment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond agentComment.errors, view:'create'
            return
        }

        agentComment.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'agentComment.label', default: 'AgentComment'), agentComment.id])
                redirect agentComment
            }
            '*' { respond agentComment, [status: CREATED] }
        }
    }

    def edit(AgentComment agentComment) {
        respond agentComment
    }

    @Transactional
    def update(AgentComment agentComment) {
        if (agentComment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (agentComment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond agentComment.errors, view:'edit'
            return
        }

        agentComment.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'agentComment.label', default: 'AgentComment'), agentComment.id])
                redirect agentComment
            }
            '*'{ respond agentComment, [status: OK] }
        }
    }

    @Transactional
    def delete(AgentComment agentComment) {

        if (agentComment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        agentComment.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'agentComment.label', default: 'AgentComment'), agentComment.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'agentComment.label', default: 'AgentComment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
