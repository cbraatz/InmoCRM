package crm

import static org.springframework.http.HttpStatus.*
import java.util.Date;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ExpenseController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	private List<ExpensePayment> payments=new ArrayList<ExpensePayment>();
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Expense.list(params), model:[expenseCount: Expense.count()]
    }

    def show(Expense expense) {
        respond expense
    }

    def create() {
        respond new Expense(params);
    }

    @Transactional
    def save(Expense expense) {
        if (expense == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		expense.isPaid=false;
		expense.validate();
        if (expense.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond expense.errors, view:'create'
            return
        }
		
		if(!Utils.validateDecimals(expense.amount, expense.currency.hasDecimals)){
			expense.errors.rejectValue('amount',message(code:'default.decimal.value.error').toString());
			transactionStatus.setRollbackOnly();
			respond expense, view:'create';
			return;
		}
		
		if(expense.amount.doubleValue() <= 0){
			expense.errors.rejectValue('amount',message(code:'default.invalid.value.error').toString());
			transactionStatus.setRollbackOnly();
			respond expense, view:'create';
			return;
		}
		
		/*if(expense.expenseType.isConcessionRelated){
			if(expense.concession){
				expense.concession = Concession.get(expense.concession.id);//find concession by id
				if(!expense.concession){
					expense.errors.rejectValue('concession',message(code:'expense.concession.not.found.error.label').toString());
					transactionStatus.setRollbackOnly();
					respond expense.errors, view:'create';
					return;
				}
			}else{
				expense.errors.rejectValue('concession',message(code:'expense.concession.required.error.label').toString());
				transactionStatus.setRollbackOnly();
				respond expense.errors, view:'create';
				return;
			}
		}else{
			if(expense.concession.id){
				expense.errors.rejectValue('concession',message(code:'expense.concession.not.required.error.label').toString());
				transactionStatus.setRollbackOnly();
				respond expense.errors, view:'create';
				return;
			}
		}*/
		
        expense.save flush:true;
		
		this.createPayments(expense);//create and save payments
		
		
	    request.withFormat {
	         form multipartForm {
	             flash.message = message(code: 'default.created.message', args: [message(code: 'expense.label', default: 'Expense'), expense.id])
	             redirect expense
	         }
	         '*' { respond expense, [status: CREATED] }
	    }
		
    }
	
	def createPayments(Expense expense){
		if(expense.isCredit){
			int n=expense.paymentPlan.numberOfParts.intValue();
			double am=expense.amount.doubleValue();
			double regp=0;
			double aux;
			Date d=new Date();
			for(int i=0;i<n;i++){
				ExpensePayment ep=new ExpensePayment();
				ep.internalID=Utils.getShortUUIDWithNumbers(expense.id.toString());
				if(i==0){
					if(expense.paymentPlan.initialFreeTimeInDays.intValue() > 0){
						ep.dueDate=Utils.addToDate(d, expense.paymentPlan.initialFreeTimeInDays.intValue(), 0);
					}else{
						ep.dueDate=new Date();
					}
					if(expense.paymentPlan.initialPaymentPercentage.intValue() > 0){
						aux=am*expense.paymentPlan.initialPaymentPercentage.doubleValue()/100;
						ep.amount=Utils.round(aux, expense.currency.hasDecimals);
						am=am-ep.amount.doubleValue();
						if(n>1){
							aux=am/(n-1);
							regp=Utils.round(aux, expense.currency.hasDecimals);
						}
					}else{
						aux=am/n;
						regp=Utils.round(aux, expense.currency.hasDecimals);
						ep.amount=regp;
					}
					
				}else{
					ep.dueDate=Utils.addToDate(d, expense.paymentPlan.regularPaymentsInDays.intValue(), expense.paymentPlan.regularPaymentsInMonths.intValue());
					ep.amount=regp;
				}
				ep.currency=expense.currency;
				ep.expense=expense;
				ep.isCanceled=false;
				ep.isPaid=false;
				this.validateAndAddPaymentToPayments(ep, expense);
				d=new Date(ep.dueDate.getTime());
			}
			
			//verificar que el total de pagos con redondeos y todo sea igual al monto
			double am2=0;
			for(int i=0;i<payments.size();i++){
				am2=am2+payments.get(i).amount.doubleValue();
			}
			if(am2 > 0){
				am2=am2-expense.amount.doubleValue();//payments amount sum - expense amount
				if(am2 != 0){
					ExpensePayment ep=payments.get(0);
					ep.amount=ep.amount.doubleValue() - am2;
				}
			}else{
				expense.errors.rejectValue('',message(code:'expense.zero.value.error.label').toString());
				//this.delete(expense);
				transactionStatus.setRollbackOnly();
				respond expense.errors, view:'create';
				return;
			}
		}else{
			ExpensePayment ep=new ExpensePayment();
			ep.internalID=Utils.getShortUUIDWithNumbers(expense.id.toString());
			ep.dueDate=new Date();
			ep.amount=Utils.round(expense.amount.doubleValue(), expense.currency.hasDecimals);
			ep.currency=expense.currency;
			ep.expense=expense;
			ep.isCanceled=false;
			ep.isPaid=false;
			this.validateAndAddPaymentToPayments(ep, expense);
		}
		
		//save payments
		try{
			for(int i=0;i<payments.size();i++){
				ExpensePayment ep=payments.get(i);
				ep.save flush:true;
				println "Saved"+ep.internalID;
			}
		}finally{
			payments=new ArrayList<ExpensePayment>();
		}
	}
	
	private void validateAndAddPaymentToPayments(ExpensePayment expensePayment, Expense expense){
		expensePayment.validate();
		if (expensePayment.hasErrors()) {
			println "Expense Payment has errors";
			expensePayment.errors.each {
				println it
			}
			expense.errors=expensePayment.errors;
			//this.delete(expense);
			transactionStatus.setRollbackOnly();
			respond expense.errors, view:'create';
			return;
		}else{
			payments.add(expensePayment);
		}
	}
	
	
    def edit(Expense expense) {
        respond expense
    }

    @Transactional
    def update(Expense expense) {
        if (expense == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		expense.isPaid=false;
		expense.validate();
        if (expense.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond expense.errors, view:'edit'
            return
        }
		
		if(expense.hasPayedPayments() || expense.isPaid){
			expense.errors.rejectValue('',message(code:'expense.has.payed.payments.error.label').toString());
			transactionStatus.setRollbackOnly();
			respond expense.errors, view:'edit';
			return;
		}
		
		/*if(expense.expenseType.isConcessionRelated){
			if(expense.concession){
				//expense.concession = Concession.get(expense.concession.id);
				if(!expense.concession){
					expense.errors.rejectValue('concession',message(code:'expense.concession.not.found.error.label').toString());
					transactionStatus.setRollbackOnly();
					respond expense.errors, view:'edit';
					return;
				}
			}else{
				expense.errors.rejectValue('concession',message(code:'expense.concession.required.error.label').toString());
				transactionStatus.setRollbackOnly();
				respond expense.errors, view:'edit';
				return;
			}
		}else{
			if(expense.concession.id){
				expense.errors.rejectValue('concession',message(code:'expense.concession.not.required.error.label').toString());
				transactionStatus.setRollbackOnly();
				respond expense.errors, view:'edit';
				return;
			}
		}*/
		
		if(expense.removeAllPayments()){
			expense.save flush:true
			this.createPayments(expense);//create again and save payments
		}else{
			expense.errors.rejectValue('',message(code:'expense.internal.error.label').toString());
			transactionStatus.setRollbackOnly();
			respond expense.errors, view:'edit';
			return;
		}
		
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'expense.label', default: 'Expense'), expense.id])
                redirect expense
            }
            '*'{ respond expense, [status: OK] }
        }
    }

    @Transactional
    def delete(Expense expense) {

        if (expense == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		if(expense.hasPayedPayments()){
			expense.errors.rejectValue('',message(code:'expense.has.payed.payments.error.label').toString());
			transactionStatus.setRollbackOnly();
			respond expense.errors, view:'create';
			return;
		}
		
		if(expense.removeAllPayments()){
			expense.delete flush:true;
		}else{
			expense.errors.rejectValue('',message(code:'expense.internal.error.label').toString());
			transactionStatus.setRollbackOnly();
			respond expense.errors, view:'create';
			return;
		}

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'expense.label', default: 'Expense'), expense.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'expense.label', default: 'Expense'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}