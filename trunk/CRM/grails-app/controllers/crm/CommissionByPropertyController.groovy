package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommissionByPropertyController {

    static allowedMethods = [save:"POST"]
	/*def index(Integer max) {
		ManagedProperty managedProperty;
		if(params.pid != null){
			managedProperty=ManagedProperty.get(params.pid);
			params.max = Math.min(max ?: 10, 100)
			respond managedProperty.commissionsByProperty, model:[commissionByPropertyCount: managedProperty.commissionsByProperty.count()]
		}else{
			render(view:'/error', model:[message: message(code: 'default.invalid.paramethers.error', args: ["pid = null"])]);
		}
	}*/
	def create(){
		//this.commissionId=params.cid;
		//def commissionInstanceList;
		ManagedProperty managedProperty;
		if(params.pid != null){
			managedProperty=ManagedProperty.get(params.pid);
		}else{
			render(view:'/error', model:[message: message(code: 'default.invalid.paramethers.error', args: ["pid = null"])]);
		}
				
		respond new CommissionByProperty(managedProperty:managedProperty, currency:managedProperty.currency)
	}
	
	@Transactional
	def save(CommissionByProperty commissionByProperty){
		if (commissionByProperty == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		ManagedProperty managedProperty=commissionByProperty.managedProperty;
		if(commissionByProperty.partner!=null){
			for(CommissionByProperty c:managedProperty.commissionsByProperty){
				if(c.partner.id.equals(commissionByProperty.partner.id)){
					commissionByProperty.errors.rejectValue('partner',message(code:'commissionByProperty.partner.repeated.error').toString());
					break;
				}
			}
		}
		commissionByProperty.crmUser=session?.user;
		commissionByProperty.validate();
		if (commissionByProperty.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond managedProperty, model:[commissionByProperty:commissionByProperty]
			respond commissionByProperty, view:'create', model:[managedProperty:managedProperty]
			return
		}
		
		if(!commissionByProperty.save(flush:true)){
			commissionByProperty.errors.rejectValue('',message(code:'default.save.error').toString());
			transactionStatus.setRollbackOnly()
			respond commissionByProperty, view:'create', model:[managedProperty:managedProperty]
			return
		}
		redirect(controller:'commissionByProperty', action:'create', params: [pid:managedProperty.id]);
	}
	
	def edit(CommissionByProperty commissionByProperty) {
		respond commissionByProperty
	}

	@Transactional
	def update(CommissionByProperty commissionByProperty) {
		if(commissionByProperty == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		commissionByProperty.crmUser=session?.user;
		commissionByProperty.validate();
		if(commissionByProperty.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond commissionByProperty, view:'edit'
			return
		}

		if(!commissionByProperty.save(flush:true)){
			commissionByProperty.errors.rejectValue('',message(code:'default.save.error').toString());
			transactionStatus.setRollbackOnly()
			respond commissionByProperty, view:'edit'
			return
		}
		redirect(controller:'commissionByProperty', action:'create', params: [pid:commissionByProperty?.managedProperty?.id]);
	}
    @Transactional
    def delete(CommissionByProperty commissionByProperty) {

        if (commissionByProperty == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		commissionByProperty.delete(flush:true)
		
		redirect(controller:'commissionByProperty', action:'create', params: [pid:commissionByProperty.managedProperty.id]);
        /*request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'commissionByProperty.label', default: 'CommissionByProperty'), commissionByProperty.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }*/
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'commissionByProperty.label', default: 'CommissionByProperty'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
