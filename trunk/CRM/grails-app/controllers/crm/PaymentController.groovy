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
	boolean registerInvoice=true;
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
		payment.outCurrency=Currency.getDefaultCurrency();
		payment.outPaymentMethod=PaymentMethod.getDefaultCashPaymentMethod();
		if(params.obj=='income'){
			payment.incomePayment=IncomePayment.get(params.pid);
			payment.amount=payment.incomePayment.amount;
			payment.amount=payment.incomePayment.amount;
			this.payedTotalAmount=this.getPayedAmount(payment.incomePayment);
			this.parentCurrency=payment.incomePayment.currency;
			this.paid=payment.incomePayment.isPaid;
		}else{
			if(params.obj=='expense'){
				payment.expensePayment=ExpensePayment.get(params.pid);
				payment.amount=payment.expensePayment.amount;
				this.payedTotalAmount=this.getPayedAmount(payment.expensePayment);
				this.parentCurrency=payment.expensePayment.currency;
				this.paid=payment.expensePayment.isPaid;
			}
		}
		respond payment;
    }
	
    @Transactional
    def save(Payment payment, boolean submitInvoice) {
		//payment.incomePayment=this.incomePayment;//payment returns with incomePayment and expensePayment = null
		//payment.expensePayment=this.expensePayment;
		this.registerInvoice=submitInvoice;
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
			payment.errors.rejectValue('',message(code:'payment.already.paid.message').toString());
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
			if(!payment.inPaymentDocument?.internalID) {
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

		if(payment.inCurrency.id == this.parentCurrency.id){
			if(payment.inAmount >= payment.amount){
				if(payment.inCurrency.id == defaultCurrency.id){
					payment.outAmount=payment.inAmount - payment.amount;
				}else{
					payment.outAmount=(payment.inAmount - payment.amount)*CurrencyExchange.getCurrencyExchangeRate(new Date(), defaultCurrency, payment.inCurrency).buy;//converting change to default currency
				}
				payment.outAmount=Utils.round(payment.outAmount, defaultCurrency.hasDecimals);
			}else{
				payment.errors.rejectValue('',message(code:'payment.amount.not.enough.error').toString());
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
				payment.outAmount=Utils.round(payedAmountValue - amountValue, defaultCurrency.hasDecimals);
			}else{
				def msg=ce1?(Utils.dateToStr(ce1.date)+' '+ce1.targetCurrency.name+'=('+ce1.buy+' '+ce1.sourceCurrency.symbol+' , '+ce1.sell+' '+ce1.sourceCurrency.symbol+')'):'' + ce2?(Utils.dateToStr(ce2.date)+' '+ce2.targetCurrency.name+'=('+ce2.buy+' '+ce2.sourceCurrency.symbol+' , '+ce2.sell+' '+ce2.sourceCurrency.symbol+')'):'';
				payment.errors.rejectValue('',message(code:'payment.amount.not.enough.other.currency.error', args:[msg]).toString());
				transactionStatus.setRollbackOnly();
				respond payment, view:'create';
				return;
			}
		}

		if(payment.outAmount>0){
			respond payment, view:'create';
			return;
		}else{
			confirm();
		}
        
    }
	@Transactional
	def confirm(Payment payment) {
		if (this.paid) {
			payment.errors.rejectValue('',message(code:'payment.already.paid.message').toString());
			transactionStatus.setRollbackOnly();
			respond payment, view:'create';
			return;
		}

		String internalID
		TransactionType transactionType;
		if(payment.incomePayment){
			internalID="IP-"+payment.incomePayment.id;
			transactionType=TransactionType.findByInternalID("INCOME_PAYMENT");
		}
		if(payment.expensePayment){
			internalID="EP-"+payment.expensePayment.id;
			transactionType=TransactionType.findByInternalID("EXPENSE_PAYMENT");
		}		
		
		boolean saved=true;
		if(payment.inPaymentMethod.isCash){
			payment.inPaymentDocument=null;
		}else{
			payment.inPaymentDocument.paymentMethod=payment.inPaymentMethod;
			saved=payment.inPaymentDocument.save(flush:true);
		}
		if(saved){
			if(!payment.save(flush:true)){
				saved=false;
				GUtils.printErrors(payment, "Payment save");
			}
		}
		if(saved){
			MoneyTransaction paymentMoneyTransaction=new MoneyTransaction(new Date(), payment.inAmount, internalID , payment, payment.inCurrency, payment.inPaymentMethod, transactionType, null, null);
			if(!paymentMoneyTransaction.save(flush:true)){
				saved=false;
				GUtils.printErrors(paymentMoneyTransaction, "paymentMoneyTransaction save");
			}
		}
		if(saved){
			if(payment.outAmount > 0){
				MoneyTransaction changeMoneyTransaction=new MoneyTransaction(new Date(), new Double(payment.outAmount * -1), internalID , payment, payment.outCurrency, payment.outPaymentMethod, TransactionType.findByInternalID("PAYMENT_CHANGE"), null, null);
				if(!changeMoneyTransaction.save(flush:true)){
					saved=false;
					GUtils.printErrors(changeMoneyTransaction, "changeMoneyTransaction save");
				}
			}
		}
		if(saved){
			if(payment.incomePayment){
				double payed=payment.incomePayment.getPayedTotalAmount();
				if(payed==payment.amount){
					payment.incomePayment.isPaid=true;
					if(payment.incomePayment.save(flush:true)){
						if(payment.incomePayment.income.areAllIncomePaymentsPaid()){
							payment.incomePayment.income.isPaid=true;
							if(!payment.incomePayment.income.save(flush:true)){
								saved=false;
								GUtils.printErrors(payment.incomePayment.income, "payment.incomePayment.income save");
							}
						}//else do nothing
					}else{
						saved=false;
						GUtils.printErrors(payment.incomePayment, "payment.incomePayment save");
					}
				}else{
					saved=false;
					GUtils.printErrors(null,"Payed Amount = "+payed+" and should be "+payment.amount);
				}
			}else{
				 if(payment.expensePayment){
					double payed=payment.expensePayment.getPayedTotalAmount();
					if(payed==payment.amount){
						payment.expensePayment.isPaid=true;
						if(payment.expensePayment.save(flush:true)){
							if(payment.expensePayment.expense.areAllExpensePaymentsPaid()){
								payment.expensePayment.expense.isPaid=true;
								if(!payment.expensePayment.expense.save(flush:true)){
									saved=false;
									GUtils.printErrors(payment.expensePayment.expense, "payment.expensePayment.expense save");
								}else{
									this.paid=true;
								}
							}//else do nothing
						}else{
							saved=false;
							GUtils.printErrors(payment.expensePayment, "payment.expensePayment save");
						}
					}else{
						saved=false;
						GUtils.printErrors(null,"Payed Amount = "+payed+" and should be "+payment.amount);
					}
				}//else ThirdPartyPayment
			}
		}
		if(saved){
			if(payment.incomePayment){
				if(this.registerInvoice){
					redirect(action: "create", controller:"issuedInvoice", params: [pid: payment.incomePayment.id]);
				}else{
					request.withFormat {
						form multipartForm {
							flash.message = message(code: 'default.payed.message', args: [message(code: 'incomePayment.label', default: 'IncomePayment'), payment.incomePayment.id])
							redirect payment.incomePayment
						}
						'*' { respond payment.incomePayment, [status: CREATED] }
					}
				}
			}
			if(payment.expensePayment){
				if(this.registerInvoice){
					redirect(action: "create", controller:"incomingInvoice", params: [pid: payment.expensePayment.id]);
				}else{
					request.withFormat {
						form multipartForm {
							flash.message = message(code: 'default.payed.message', args: [message(code: 'expensePayment.label', default: 'ExpensePayment'), payment.expensePayment.id])
							redirect payment.expensePayment
						}
						'*' { respond payment.expensePayment, [status: CREATED] }
					}
				}
			}
		/*add ThirdPartyPayment here...*/
		}else{
			payment.errors.rejectValue('',message(code:'default.save.error', args: ['Payment']).toString());
			transactionStatus.setRollbackOnly();
			payment.outAmount=0;
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
