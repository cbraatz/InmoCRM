package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class IncomePaymentController {
	String documentNumber;
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond IncomePayment.list(params), model:[incomePaymentCount: IncomePayment.count()]
    }

    def show(IncomePayment incomePayment) {
        respond incomePayment
    }
	
	/*def edit(IncomePayment incomePayment) {
		respond incomePayment
	}no hay vista para este metodo*/

	/*@Transactional
	def pay(IncomePayment incomePayment) {

		if (incomePayment == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (incomePayment.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond incomePayment.errors, view:'edit'
			return
		}
		
		incomePayment.save flush:true
		
		MoneyTransaction moneyTransaction=new MoneyTransaction();
		moneyTransaction.setIncomePayment(incomePayment);
		moneyTransaction.setAmount(incomePayment.getPayedAmount());
		moneyTransaction.setCurrency(incomePayment.getCurrency());
		moneyTransaction.setDocumentId(documentNumber)
		
		moneyTransaction.setInternalID(documentNumber)
		moneyTransaction.setPaymentMethod(null)
		moneyTransaction.setTransactionType(null)
		
		

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'incomePayment.label', default: 'IncomePayment'), incomePayment.id])
				redirect incomePayment
			}
			'*'{ respond incomePayment, [status: OK] }
		}
	}*/

    /*@Transactional
    def cancel(IncomePayment incomePayment) {

        if (incomePayment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		if(!incomePayment.isIsCanceled()){//cancel if it is not already canceled
			incomePayment.isCanceled=true;
	        incomePayment.save flush:true
		}
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.canceled.message', args: [message(code: 'incomePayment.label', default: 'IncomePayment'), incomePayment.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }*/

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'incomePayment.label', default: 'IncomePayment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
