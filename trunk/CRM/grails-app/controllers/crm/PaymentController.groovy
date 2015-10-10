package crm

import static org.springframework.http.HttpStatus.*

import java.util.Date;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class PaymentController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	//double paymentTotalAmount=0;
	double payedTotalAmount=0;
	boolean paid=false;
	Payment myPayment=new Payment();
	Currency parentCurrency;
	private double getPayedAmount(Object paymentParent){
		double payedAmount=0;
		paymentParent.payments.each {
			payedAmount=payedAmount+it.amount;
		}
		System.out.println("Payed Amount="+payedAmount);
		return payedAmount;
	}
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Payment.list(params), model:[paymentCount: Payment.count()]
    }

    def show(Payment payment) {
        respond payment
    }

    def create() {
		Payment payment=new Payment();
		if(params.obj=='income'){
			payment.incomePayment=IncomePayment.get(params.pid);
			payment.amount=payment.incomePayment.amount;
			this.myPayment.amount=payment.incomePayment.amount;
			this.payedTotalAmount=this.getPayedAmount(payment.incomePayment);
			this.parentCurrency=payment.incomePayment.currency;
			this.myPayment.incomePayment=payment.incomePayment;
			this.paid=payment.incomePayment.isPaid;
		}else{
			if(params.obj=='expense'){
				payment.expensePayment=ExpensePayment.get(params.pid);
				payment.amount=payment.expensePayment.amount;
				this.myPayment.amount=payment.expensePayment.amount;
				this.payedTotalAmount=this.getPayedAmount(payment.expensePayment);
				this.parentCurrency=payment.expensePayment.currency;
				this.myPayment.expensePayment=payment.expensePayment;
				this.paid=payment.expensePayment.isPaid;
			}
		}
		
		//def object = (params.obj=='income'? IncomePayment.get(params.pid) : (params.obj=='expense'? ExpensePayment.get(params.pid): 'other object'));
		respond payment;
    }

    @Transactional
    def save(Payment payment) {
		//payment.incomePayment=this.incomePayment;//payment returns with incomePayment and expensePayment = null
		//payment.expensePayment=this.expensePayment;
		payment.validate();
		if (payment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        if (payment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond payment.errors, view:'create';
            return
        }
		
		if (this.paid) {
			payment.errors.rejectValue('',message(code:'payment.already.paid.error').toString());
			transactionStatus.setRollbackOnly();
			respond payment, view:'create';
			return;
		}
		
		if(!Utils.validateDecimals(payment.inAmount, payment.inCurrency.hasDecimals)){
			payment.errors.rejectValue('inAmount',message(code:'default.decimal.value.error').toString());
			transactionStatus.setRollbackOnly();
			respond payment, view:'create';
			return;
		}
		
		if(payment.inAmount.doubleValue() <= 0){
			payment.errors.rejectValue('inAmount',message(code:'default.invalid.value.error').toString());
			transactionStatus.setRollbackOnly();
			respond payment, view:'create';
			return;
		}
		
		if (!payment.incomePayment && !payment.expensePayment) {
			payment.errors.rejectValue('',message(code:'payment.parent.required.error').toString());
			transactionStatus.setRollbackOnly();
			respond payment, view:'create';
			return;
		}
		
		if (payment.incomePayment && payment.expensePayment) {
			payment.errors.rejectValue('',message(code:'payment.parent.multi.error').toString());
			transactionStatus.setRollbackOnly();
			respond payment, view:'create';
			return;
		}
		
		//paymentDocument validation
		if (payment.inPaymentMethod.isCash == false) {
			boolean hasErrors=false;
			if(!payment.inPaymentDocument?.internalId) {
				hasErrors=true;
				payment.errors.rejectValue('',message(code:'payment.document.number.required').toString());
			}
			if (!payment.inPaymentDocument?.bank) {
				if (payment.inPaymentMethod.hasBank) {
					hasErrors=true;
					payment.errors.rejectValue('',message(code:'payment.document.bank.required').toString());
				}
			}
			if (!payment.inPaymentDocument?.endDate) {
				if (payment.inPaymentMethod.hasEndDate) {
					hasErrors=true;
					payment.errors.rejectValue('',message(code:'payment.document.end.date.required').toString());
				}
			}
			if (!payment.inPaymentDocument?.startDate) {
				if (payment.inPaymentMethod.hasStartDate) {
					hasErrors=true;
					payment.errors.rejectValue('',message(code:'payment.document.start.date.required').toString());
				}
			}
			if (!payment.inPaymentDocument?.bank) {
				if (payment.inPaymentMethod.hasBank) {
					hasErrors=true;
					payment.errors.rejectValue('',message(code:'payment.document.bank.required').toString());
				}
			}
			if (hasErrors) {
				transactionStatus.setRollbackOnly();
				respond payment, view:'create';
				return;
			}
		}
		
		
		
		Currency defaultCurrency=Currency.getDefaultCurrency();
		if(!defaultCurrency){
			payment.errors.rejectValue('',message(code:'payment.currency.error').toString());
			transactionStatus.setRollbackOnly();
			respond payment, view:'create';
			return;
		}
		payment.outCurrency=defaultCurrency;
		payment.outPaymentMethod=PaymentMethod.getDefaultCashPaymentMethod();
		if(payment.inCurrency.id == this.parentCurrency.id){
			if(payment.inAmount >= payment.amount){
				if(payment.inCurrency.id == defaultCurrency.id){
					payment.outAmount=payment.inAmount - payment.amount;
				}else{
					payment.outAmount=(payment.inAmount - payment.amount)*CurrencyExchange.getCurrencyExchangeRate(new Date(), defaultCurrency, payment.inCurrency).buy;//converting change to default currency
				}
				payment.outAmount=Utils.round(payment.outAmount, defaultCurrency);
			}else{
				payment.errors.rejectValue('',message(code:'payment.amount.not.enough').toString());
				transactionStatus.setRollbackOnly();
				respond payment, view:'create';
				return;
			}
		}else{
			double amountValue=payment.amount;//monto a pagar en moneda por defecto = GS
			CurrencyExchange ce1, ce2;
			if(this.parentCurrency.id != defaultCurrency.id){//si la cuota/pago NO es en Gs
				ce1=CurrencyExchange.getCurrencyExchangeRate(new Date(), defaultCurrency, this.parentCurrency);
				amountValue=ce1.buy*payment.amount;//obtiene el valor en Gs del monto de la cuota/pago y es buy xq este calculo solo se hace al cobrar y no al pagar
			}
			double payedAmountValue=payment.inAmount; //monto pagado en moneda por defecto = GS
			if(payment.inCurrency.id != defaultCurrency.id){//si el monto pagado NO es en Gs
				ce2=CurrencyExchange.getCurrencyExchangeRate(new Date(), defaultCurrency, payment.inCurrency);
				payedAmountValue=ce2.buy*payment.inAmount;//obtiene el valor en Gs del monto pagado y es buy xq este calculo solo se hace al cobrar y no al pagar
			}
			if(payedAmountValue >= amountValue){
				payment.outAmount=Utils.round(payedAmountValue - amountValue, defaultCurrency);
			}else{
				def msg=ce1?(Utils.getDateInStr(ce1.date)+' '+ce1.targetCurrency.name+'=('+ce1.buy+' '+ce1.sourceCurrency.symbol+' , '+ce1.sell+' '+ce1.sourceCurrency.symbol+')'):'' + ce2?(Utils.getDateInStr(ce2.date)+' '+ce2.targetCurrency.name+'=('+ce2.buy+' '+ce2.sourceCurrency.symbol+' , '+ce2.sell+' '+ce2.sourceCurrency.symbol+')'):'';
				payment.errors.rejectValue('',message(code:'payment.amount.not.enough.other.currency', args:[msg]).toString());
				transactionStatus.setRollbackOnly();
				respond payment, view:'create';
				return;
			}
		}
		this.myPayment=payment;//salvando payment xq si tiene cambio, un nuevo formulario ejecuta confirmPayment y se pierde su valor
		this.myPayment.date=payment.date;
		this.myPayment.amount=payment.amount;
		this.myPayment.inAmount=payment.inAmount;
		this.myPayment.outAmount=payment.outAmount;
		this.myPayment.inCurrency=payment.inCurrency;
		this.myPayment.outCurrency=payment.outCurrency;
		this.myPayment.inPaymentMethod=payment.inPaymentMethod;
		this.myPayment.outPaymentMethod=payment.outPaymentMethod;
		this.myPayment.inPaymentDocument=payment.inPaymentDocument;
		this.myPayment.outPaymentMethod=payment.outPaymentMethod;
		//this.myPayment.outPaymentDocument=payment.outPaymentDocument;
		if(payment.outAmount>0){
			respond payment, view:'create';
			return;
		}else{
			confirmPayment();
		}
        
    }
	@Transactional
	def confirmPayment() {
		if (this.paid) {
			payment.errors.rejectValue('',message(code:'payment.already.paid.error').toString());
			transactionStatus.setRollbackOnly();
			respond payment, view:'create';
			return;
		}

		String internalId
		TransactionType transactionType;
		if(this.myPayment.incomePayment){
			internalId="IP-"+this.myPayment.incomePayment.id;
			transactionType=TransactionType.findByInternalID("INCOME_PAYMENT");
		}
		if(this.myPayment.expensePayment){
			internalId="EP-"+this.myPayment.expensePayment.id;
			transactionType=TransactionType.findByInternalID("EXPENSE_PAYMENT");
		}		
		
		//myPayment.paymentDocument.bank=...
		boolean saved=true;
		if(this.myPayment.inPaymentMethod.isCash){
			this.myPayment.inPaymentDocument=null;
		}else{
			this.myPayment.inPaymentDocument.paymentMethod=this.myPayment.inPaymentMethod;
			saved=this.myPayment.inPaymentDocument.save(flush:true);
		}
		if(saved){
			if(!this.myPayment.save(flush:true)){
				saved=false;
				GUtils.printErrors(this.myPayment, "myPayment save");
			}
		}
		if(saved){
			MoneyTransaction paymentMoneyTransaction=new MoneyTransaction(new Date(), this.myPayment.inAmount, internalId , this.myPayment, this.myPayment.inCurrency, this.myPayment.inPaymentMethod, transactionType, null, null);
			if(!paymentMoneyTransaction.save(flush:true)){
				saved=false;
				GUtils.printErrors(paymentMoneyTransaction, "paymentMoneyTransaction save");
			}
		}
		if(saved){
			if(this.myPayment.outAmount > 0){
				MoneyTransaction changeMoneyTransaction=new MoneyTransaction(new Date(), new Double(this.myPayment.outAmount * -1), internalId , this.myPayment, this.myPayment.outCurrency, this.myPayment.outPaymentMethod, TransactionType.findByInternalID("PAYMENT_CHANGE"), null, null);
				if(!changeMoneyTransaction.save(flush:true)){
					saved=false;
					GUtils.printErrors(changeMoneyTransaction, "changeMoneyTransaction save");
				}
			}
		}
		if(saved){
			if(this.myPayment.incomePayment){
				double payed=this.myPayment.incomePayment.getPayedTotalAmount();
				if(payed==myPayment.amount){
					this.myPayment.incomePayment.isPaid=true;
					if(this.myPayment.incomePayment.save(flush:true)){
						if(this.myPayment.incomePayment.income.areAllIncomePaymentsPaid()){
							this.myPayment.incomePayment.income.isPaid=true;
							if(!this.myPayment.incomePayment.income.save(flush:true)){
								saved=false;
								GUtils.printErrors(this.myPayment.incomePayment.income, "myPayment.incomePayment.income save");
							}
						}//else do nothing
					}else{
						saved=false;
						GUtils.printErrors(this.myPayment.incomePayment, "myPayment.incomePayment save");
					}
				}else{
					saved=false;
					GUtils.printErrors(null,"Payed Amount = "+payed+" and should be "+myPayment.amount);
				}
			}else{
				 if(this.myPayment.expensePayment){
					double payed=this.myPayment.expensePayment.getPayedTotalAmount();
					if(payed==myPayment.amount){
						this.myPayment.expensePayment.isPaid=true;
						if(this.myPayment.expensePayment.save(flush:true)){
							if(this.myPayment.expensePayment.expense.areAllExpensePaymentsPaid()){
								this.myPayment.expensePayment.expense.isPaid=true;
								if(!this.myPayment.expensePayment.expense.save(flush:true)){
									saved=false;
									GUtils.printErrors(this.myPayment.expensePayment.expense, "myPayment.expensePayment.expense save");
								}else{
									this.paid=true;
								}
							}//else do nothing
						}else{
							saved=false;
							GUtils.printErrors(this.myPayment.expensePayment, "myPayment.expensePayment save");
						}
					}else{
						saved=false;
						GUtils.printErrors(null,"Payed Amount = "+payed+" and should be "+myPayment.amount);
					}
				}//else ThirdPartyPayment
			}
		}
		if(saved){
			if(this.myPayment.incomePayment){
				request.withFormat {
					form multipartForm {
						flash.message = message(code: 'default.payed.message', args: [message(code: 'incomePayment.label', default: 'IncomePayment'), this.myPayment.incomePayment.id])
						redirect this.myPayment.incomePayment
					}
					'*' { respond this.myPayment.incomePayment, [status: CREATED] }
				}
			}
			if(this.myPayment.expensePayment){
				request.withFormat {
					form multipartForm {
						flash.message = message(code: 'default.payed.message', args: [message(code: 'expensePayment.label', default: 'ExpensePayment'), this.myPayment.expensePayment.id])
						redirect this.myPayment.expensePayment
					}
					'*' { respond this.myPayment.expensePayment, [status: CREATED] }
				}
			}
		/*add ThirdPartyPayment here...*/
		}else{
			this.myPayment.errors.rejectValue('',message(code:'payment.save.error').toString());
			transactionStatus.setRollbackOnly();
			this.myPayment.outAmount=0;
			//repond payment en vez de myPayment
			Payment payment=this.myPayment;
			if(!payment.inPaymentDocument){
				payment.inPaymentDocument=new PaymentDocument();
			}
			respond payment, view:'create';
			return;
		}
		
	}
    def edit(Payment payment) {
        respond payment
    }

    @Transactional
    def update(Payment payment) {
        if (payment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (payment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond payment.errors, view:'edit'
            return
        }

        payment.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'payment.label', default: 'Payment'), payment.id])
                redirect payment
            }
            '*'{ respond payment, [status: OK] }
        }
    }

    @Transactional
    def delete(Payment payment) {

        if (payment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        payment.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'payment.label', default: 'Payment'), payment.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'payment.label', default: 'Payment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
