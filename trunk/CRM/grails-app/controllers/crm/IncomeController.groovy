package crm

import static org.springframework.http.HttpStatus.*

import java.util.Date;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class IncomeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	private List<IncomePayment> payments=new ArrayList<IncomePayment>();
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Income.list(params), model:[incomeCount: Income.count()]
    }

    def show(Income income) {
        respond income
    }

    def create() {
        respond new Income(params);
    }

    @Transactional
    def save(Income income) {
        if (income == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		income.isPaid=false;
		income.validate();
        if (income.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond income.errors, view:'create'
            return
        }
		
		if(!Utils.validateDecimals(income.amount, income.currency.hasDecimals)){
			income.errors.rejectValue('amount',message(code:'default.decimal.value.error').toString());
			transactionStatus.setRollbackOnly();
			respond income, view:'create';
			return;
		}
		
		if(income.amount.doubleValue() <= 0){
			income.errors.rejectValue('amount',message(code:'default.invalid.value.error').toString());
			transactionStatus.setRollbackOnly();
			respond income, view:'create';
			return;
		}
		
		if(income.incomeType.isConcessionRelated){
			if(income.concession){
				income.concession = Concession.get(income.concession.id);//find concession by id
				if(!income.concession){
					income.errors.rejectValue('concession',message(code:'income.concession.not.found.error.label').toString());
					transactionStatus.setRollbackOnly();
					respond income.errors, view:'create';
					return;
				}
			}else{
				income.errors.rejectValue('concession',message(code:'income.concession.required.error.label').toString());
				transactionStatus.setRollbackOnly();
				respond income.errors, view:'create';
				return;
			}
		}else{
			if(income.concession?.id){
				income.errors.rejectValue('concession',message(code:'income.concession.not.required.error.label').toString());
				transactionStatus.setRollbackOnly();
				respond income.errors, view:'create';
				return;
			}
		}
		
        income.save flush:true;
		
		this.createPayments(income);//create and save payments
		
		
	    request.withFormat {
	         form multipartForm {
	             flash.message = message(code: 'default.created.message', args: [message(code: 'income.label', default: 'Income'), income.id])
	             redirect income
	         }
	         '*' { respond income, [status: CREATED] }
	    }
		
    }
	
	def createPayments(Income income){
		if(income.isCredit){
			int n=income.paymentPlan.numberOfParts.intValue();
			double am=income.amount.doubleValue();
			double regp=0;
			double aux;
			Date d=new Date();
			for(int i=0;i<n;i++){
				IncomePayment ip=new IncomePayment();
				ip.internalId=Utils.getShortUUIDWithNumbers(income.id.toString());
				if(i==0){
					if(income.paymentPlan.initialFreeTimeInDays.intValue() > 0){
						ip.dueDate=Utils.addToDate(d, income.paymentPlan.initialFreeTimeInDays.intValue(), 0);
					}else{
						ip.dueDate=new Date();
					}
					if(income.paymentPlan.initialPaymentPercentage.intValue() > 0){
						aux=am*income.paymentPlan.initialPaymentPercentage.doubleValue()/100;
						ip.amount=Utils.round(aux, income.currency.hasDecimals);
						am=am-ip.amount.doubleValue();
						if(n>1){
							aux=am/(n-1);
							regp=Utils.round(aux, income.currency.hasDecimals);
						}
					}else{
						aux=am/n;
						regp=Utils.round(aux, income.currency.hasDecimals);
						ip.amount=regp;
					}
					
				}else{
					ip.dueDate=Utils.addToDate(d, income.paymentPlan.regularPaymentsInDays.intValue(), income.paymentPlan.regularPaymentsInMonths.intValue());
					ip.amount=regp;
				}
				ip.currency=income.currency;
				ip.income=income;
				ip.isCanceled=false;
				ip.isPaid=false;
				this.validateAndAddPaymentToPayments(ip, income);
				d=new Date(ip.dueDate.getTime());
			}
			
			//verificar que el total de pagos con redondeos y todo sea igual al monto
			double am2=0;
			for(int i=0;i<payments.size();i++){
				am2=am2+payments.get(i).amount.doubleValue();
			}
			if(am2 > 0){
				am2=am2-income.amount.doubleValue();//payments amount sum - income amount
				if(am2 != 0){
					IncomePayment ip=payments.get(0);
					ip.amount=ip.amount.doubleValue() - am2;
				}
			}else{
				income.errors.rejectValue('',message(code:'income.zero.value.error.label').toString());
				//this.delete(income);
				transactionStatus.setRollbackOnly();
				respond income.errors, view:'create';
				return;
			}
		}else{
			IncomePayment ip=new IncomePayment();
			ip.internalId=Utils.getShortUUIDWithNumbers(income.id.toString());
			ip.dueDate=new Date();
			ip.amount=Utils.round(income.amount.doubleValue(), income.currency.hasDecimals);
			ip.currency=income.currency;
			ip.income=income;
			ip.isCanceled=false;
			ip.isPaid=false;
			this.validateAndAddPaymentToPayments(ip, income);
		}
		
		//save payments
		try{
			for(int i=0;i<payments.size();i++){
				IncomePayment ip=payments.get(i);
				ip.save flush:true;
				println "Saved"+ip.internalId;
			}
		}finally{
			payments=new ArrayList<IncomePayment>();
		}
	}
	
	private void validateAndAddPaymentToPayments(IncomePayment incomePayment, Income income){
		incomePayment.validate();
		if (incomePayment.hasErrors()) {
			println "Income Payment has errors";
			incomePayment.errors.each {
				println it
			}
			income.errors=incomePayment.errors;
			//this.delete(income);
			transactionStatus.setRollbackOnly();
			respond income.errors, view:'create';
			return;
		}else{
			payments.add(incomePayment);
		}
	}
	
	
    def edit(Income income) {
        respond income
    }

    @Transactional
    def update(Income income) {
        if (income == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		income.isPaid=false;
		income.validate();
        if (income.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond income.errors, view:'edit'
            return
        }
		
		if(income.hasPayedPayments() || income.isPaid){
			income.errors.rejectValue('',message(code:'income.has.payed.payments.error.label').toString());
			transactionStatus.setRollbackOnly();
			respond income.errors, view:'edit';
			return;
		}
		
		if(income.incomeType.isConcessionRelated){
			if(income.concession){
				income.concession = Concession.get(income.concession.id);
				if(!income.concession){
					income.errors.rejectValue('concession',message(code:'income.concession.not.found.error.label').toString());
					transactionStatus.setRollbackOnly();
					respond income.errors, view:'edit';
					return;
				}
			}else{
				income.errors.rejectValue('concession',message(code:'income.concession.required.error.label').toString());
				transactionStatus.setRollbackOnly();
				respond income.errors, view:'edit';
				return;
			}
		}else{
			if(income.concession?.id){
				income.errors.rejectValue('concession',message(code:'income.concession.not.required.error.label').toString());
				transactionStatus.setRollbackOnly();
				respond income.errors, view:'edit';
				return;
			}
		}
		
		if(income.removeAllPayments()){
			income.save flush:true
			this.createPayments(income);//create again and save payments
		}else{
			income.errors.rejectValue('',message(code:'income.internal.error.label').toString());
			transactionStatus.setRollbackOnly();
			respond income.errors, view:'edit';
			return;
		}
		
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'income.label', default: 'Income'), income.id])
                redirect income
            }
            '*'{ respond income, [status: OK] }
        }
    }

    @Transactional
    def delete(Income income) {

        if (income == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		if(income.hasPayedPayments()){
			income.errors.rejectValue('',message(code:'income.has.payed.payments.error.label').toString());
			transactionStatus.setRollbackOnly();
			respond income.errors, view:'create';
			return;
		}
		
		if(income.removeAllPayments()){
			income.delete flush:true;
		}else{
			income.errors.rejectValue('',message(code:'income.internal.error.label').toString());
			transactionStatus.setRollbackOnly();
			respond income.errors, view:'create';
			return;
		}

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'income.label', default: 'Income'), income.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'income.label', default: 'Income'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
