package crm

import static org.springframework.http.HttpStatus.*

import java.util.Date;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class IncomeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	List<IncomePayment> payments=new ArrayList<IncomePayment>();
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Income.list(params), model:[incomeCount: Income.count()]
    }

    def show(Income income) {
        respond income
    }

    def create() {
        respond new Income(params)
    }

    @Transactional
    def save(Income income) {
        if (income == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (income.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond income.errors, view:'create'
            return
        }

        income.save flush:true
		
		
		
		if(income.isCredit){	
			int n=income.paymentPlan.numberOfParts.intValue();
			float am=income.amount.floatValue();
			float regp=0;
			float aux;
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
						aux=am*income.paymentPlan.initialPaymentPercentage.floatValue()/100;
						ip.amount=Utils.round(aux, income.currency.decimals.intValue());
						am=am-ip.amount.value;
						if(n>1){
							aux=am/(n-1);
							regp=Utils.round(aux, income.currency.decimals.intValue());
						}
					}else{
						aux=am/n;
						regp=Utils.round(aux, income.currency.decimals.intValue());
						ip.amount=regp;
					}
					
				}else{
					ip.dueDate=Utils.addToDate(d, income.paymentPlan.regularPaymentsInDays.intValue(), income.paymentPlan.regularPaymentsInMonths.intValue());
					ip.amount=regp;
				}
				ip.payedAmount=0;
				ip.paymentDate=null;
				ip.paymentMethod=null;
				ip.currency=income.currency;
				ip.income=income;
				ip.isCanceled=false;
				//ip.validate();
				if (ip.hasErrors()) {
					println "Income Payment has errors";
					ip.errors.each {
						println it
					}
					transactionStatus.setRollbackOnly();
					//respond income.errors, view:'create', controller:'income'
					//return
				}else{
					if(ip.save()){
						println "Income Payment SAVED";
					}else{
						transactionStatus.setRollbackOnly();
						println "Income Payment DONT SAVED";
						ip.errors.each {
							println it
						}
					}
				}
				payments.add(ip);
				d=new Date(ip.dueDate.getTime());
			}
			float totalAmount=0;
			for(int i=0;i<payments.lenght;i++){
				fsdfg...
			}
		}else{
			IncomePayment ip=new IncomePayment();
			ip.internalId=Utils.getShortUUIDWithNumbers(income.id.toString());
			ip.dueDate=new Date();
			ip.amount=Utils.round(income.amount.floatValue(), income.currency.decimals.intValue());
			ip.payedAmount=0;
			ip.paymentDate=null;
			ip.paymentMethod=null;
			ip.currency=income.currency;
			ip.income=income;
			ip.isCanceled=false;
		}
		
		
		
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'income.label', default: 'Income'), income.id])
                redirect income
            }
            '*' { respond income, [status: CREATED] }
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

        if (income.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond income.errors, view:'edit'
            return
        }

        income.save flush:true

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

        income.delete flush:true

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
