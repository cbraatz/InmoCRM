package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommissionByConcessionController {

    static allowedMethods = [save:"POST"]
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CommissionByConcession.list(params), model:[commissionByConcessionCount: CommissionByConcession.count()]
    }

    
	def addCommission(){
		//this.commissionId=params.cid;
		//def commissionInstanceList;
		Concession concession;
		if(params.cid != null){
			concession=Concession.get(params.cid);
		}else{
			render(view:'/error', model:[message: message(code: 'default.invalid.paramethers.error', args: ["cid = "+params.cid])]);
		}
		/*if(null==commissionInstanceList){
			commissionInstanceList=CommissionByConcession.list(concession:concession);
		}*/
		
		respond concession, model:[commissionByConcession:new CommissionByConcession()]
	}
	
	@Transactional
	def save(CommissionByConcession commissionByConcession){
		if (commissionByConcession == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		Concession concession=commissionByConcession.concession;
		if(commissionByConcession.partner!=null){
			for(CommissionByConcession c:concession.commissionsByConcession){
				if(c.partner.id.equals(commissionByConcession.partner.id)){
					commissionByConcession.errors.rejectValue('partner',message(code:'commissionByConcession.partner.repeated.error').toString());
					break;
				}
			}
		}
		if (commissionByConcession.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond concession, model:[commissionByConcession:commissionByConcession]
			respond commissionByConcession, view:'addCommission', model:[concession:concession]
			return
		}
		
		if(!commissionByConcession.save(flush:true)){
			commissionByConcession.errors.rejectValue('',message(code:'default.save.error').toString());
			transactionStatus.setRollbackOnly()
			respond commissionByConcession, view:'addCommission', model:[concession:concession]
			return
		}else{
			redirect(controller:'commissionByConcession', action:'addCommission', params: [cid:concession.id]);
		}
	}
	def edit(CommissionByConcession commissionByConcession) {
		respond commissionByConcession
	}

	@Transactional
	def update(CommissionByConcession commissionByConcession) {
		if(commissionByConcession == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		def concessionId=commissionByConcession.concession.id;

		if(commissionByConcession.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond commissionByConcession, view:'edit'
			return
		}

		if(!commissionByConcession.save(flush:true)){
			commissionByConcession.errors.rejectValue('',message(code:'default.save.error').toString());
			transactionStatus.setRollbackOnly()
			respond commissionByConcession, view:'edit'
			return
		}else{
			redirect(controller:'commissionByConcession', action:'addCommission', params: [cid:concessionId]);
		}
	}
    @Transactional
    def delete(CommissionByConcession commissionByConcession) {

        if (commissionByConcession == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		def concessionId=commissionByConcession.concession.id;
		
		commissionByConcession.delete(flush:true)
		
		redirect(controller:'commissionByConcession', action:'addCommission', params: [cid:concessionId]);
        /*request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'commissionByConcession.label', default: 'CommissionByConcession'), commissionByConcession.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }*/
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'commissionByConcession.label', default: 'CommissionByConcession'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
