package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ExpensePaymentController {
	String documentNumber;
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond ExpensePayment.list(params), model:[expensePaymentCount: ExpensePayment.count()]
	}

	def show(ExpensePayment expensePayment) {
		respond expensePayment
	}
	
	def edit(ExpensePayment expensePayment) {
		respond expensePayment
	}

	@Transactional
	def pay(ExpensePayment expensePayment) {

		if (expensePayment == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (expensePayment.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond expensePayment.errors, view:'edit'
			return
		}
		
		expensePayment.save flush:true
		
		MoneyTransaction moneyTransaction=new MoneyTransaction();
		moneyTransaction.setExpensePayment(expensePayment);
		moneyTransaction.setAmount(expensePayment.getPayedAmount());
		moneyTransaction.setCurrency(expensePayment.getCurrency());
		moneyTransaction.setDocumentId(documentNumber)
		
		moneyTransaction.setInternalID(documentNumber)
		moneyTransaction.setPaymentMethod(null)
		moneyTransaction.setTransactionType(null)
		
		

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'expensePayment.label', default: 'ExpensePayment'), expensePayment.id])
				redirect expensePayment
			}
			'*'{ respond expensePayment, [status: OK] }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'expensePayment.label', default: 'ExpensePayment'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}