package crm
import static org.springframework.http.HttpStatus.*

import java.math.RoundingMode
import java.util.Date;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class IncomingInvoiceController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond IncomingInvoice.list(params), model:[incomingInvoiceCount: IncomingInvoice.count()]
	}

	def show(IncomingInvoice incomingInvoice) {
		respond incomingInvoice
	}

	def create(IncomingInvoice updatedIncomingInvoice) {
		if(updatedIncomingInvoice){
			respond updatedIncomingInvoice;
		}else{
			IncomingInvoice incomingInvoice=new IncomingInvoice();//era con (params)
			incomingInvoice.expensePayment=ExpensePayment.get(params.pid);
			if(incomingInvoice.expensePayment){
				incomingInvoice.vendor=(incomingInvoice?.expensePayment?.expense?.vendor);
				incomingInvoice.amountInExpenseCurrency=incomingInvoice.expensePayment.getPayedTotalAmount().doubleValue()-incomingInvoice.getIncomingInvoiceAmountInExpenseCurrency().doubleValue();
				incomingInvoice.amount=incomingInvoice.amountInExpenseCurrency.doubleValue();
				incomingInvoice.currency=incomingInvoice.expensePayment.expense.currency;
				incomingInvoice.date=Utils.removeTimeFromDate(new Date());
				incomingInvoice.amountInDefaultCurrency=null;//is completed automatically once accounting resume is done
				incomingInvoice.number=new String("001-001-");
				float tr=incomingInvoice.expensePayment.expense.expenseType.taxRate.percentage.floatValue();
				incomingInvoice.totalTax=incomingInvoice.amount.doubleValue()*tr/(100+tr);
				incomingInvoice.isAccounting=true;
				incomingInvoice.isAccounted=false;
				incomingInvoice.isSelfInvoice=false;
				respond incomingInvoice;
			}else{
				render(view: "/error", model:[message:message(code: 'incomingInvoice.expense.id.not.found.label', default: 'Expense ID not found or not correct')]);
			}
		}
	}
	def refresh(IncomingInvoice incomingInvoice, boolean returnToSave){
		if(incomingInvoice.expensePayment.expense.currency.id == incomingInvoice.currency.id){
			incomingInvoice.amount=incomingInvoice.amountInExpenseCurrency.doubleValue();
			if(incomingInvoice.currency.isDefault){
				incomingInvoice.amountInDefaultCurrency=incomingInvoice.amount;
			}
		}else{
			if(!incomingInvoice.expensePayment.expense.currency.isDefault && !incomingInvoice.currency.isDefault){//si ninguno es en GS
				Currency defaultCurrency=Currency.getDefaultCurrency();
				CurrencyExchange ce=CurrencyExchange.getCurrencyExchangeRate(incomingInvoice.date, defaultCurrency, incomingInvoice.expensePayment.expense.currency);
				CurrencyExchange ce2=CurrencyExchange.getCurrencyExchangeRate(incomingInvoice.date, defaultCurrency, incomingInvoice.currency);
				BigDecimal bd=new BigDecimal(incomingInvoice.amountInExpenseCurrency.toString()).multiply(new BigDecimal(ce.buy.toString()));
				incomingInvoice.amount=Utils.round(bd.divide(new BigDecimal(ce2.sell.toString()), RoundingMode.HALF_UP).doubleValue(), incomingInvoice.currency.hasDecimals);
			}else{
				if(incomingInvoice.expensePayment.expense.currency.isDefault){//si el ingreso está en GS y la factura en otra moneda
					CurrencyExchange ce=CurrencyExchange.getCurrencyExchangeRate(incomingInvoice.date, incomingInvoice.expensePayment.expense.currency, incomingInvoice.currency);
					incomingInvoice.amount=Utils.round(incomingInvoice.amountInExpenseCurrency.doubleValue()/ce.sell, incomingInvoice.currency.hasDecimals);
				}else{
					CurrencyExchange ce=CurrencyExchange.getCurrencyExchangeRate(incomingInvoice.date, incomingInvoice.currency, incomingInvoice.expensePayment.expense.currency);
					incomingInvoice.amount=Utils.round(incomingInvoice.amountInExpenseCurrency.doubleValue()*ce.buy, incomingInvoice.currency.hasDecimals);
				}
			}
		}
		float tr=incomingInvoice.expensePayment.expense.expenseType.taxRate.percentage.floatValue();
		incomingInvoice.totalTax=Utils.round(incomingInvoice.amount.doubleValue()*tr/(100+tr), incomingInvoice.currency.hasDecimals);
		if(returnToSave){
			return incomingInvoice;
		}else{
			respond incomingInvoice, view:'create';
		}
	}
	/*private boolean validateExpenseInvoiceNumber(String number){
		int idx=number.indexOf('_');
		String[] array=number.split("_");
		if(array.length!=2){
			return false;
		}
		try{
			Integer.parseInt(array[0]);
		}catch(NumberFormatException ex){
			return false;
		}
		String[] array2=array[1].split("-");
		if(array2.length!=3){
			return false;
		}
		for(int i=0;i<3;i++){
			try{
				Integer.parseInt(array2[i]);
			}catch(NumberFormatException ex){
				return false;
			}
		}
		return true;
	}*/
	@Transactional
	def save(IncomingInvoice incomingInvoice) {
		if (incomingInvoice == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		if(incomingInvoice.isSelfInvoice){
			this.refresh(incomingInvoice, true);
		}
		incomingInvoice.validate();
		/*if (!incomingInvoice.currency.isInvoicingCurrency) {
			incomingInvoice.errors.rejectValue('currency',message(code:'currency.not.invoicing.currency.error', args:[incomingInvoice.currency.name]).toString());
		}*/
		double invoicedAmount=incomingInvoice.expensePayment.getRegisteredInvoicesTotalAmount();
		double payedAmount=incomingInvoice.expensePayment.getPayedTotalAmount()
		if(incomingInvoice.amountInExpenseCurrency + invoicedAmount > payedAmount){
			incomingInvoice.errors.rejectValue('amountInExpenseCurrency', message(code:'incomingInvoice.too.big.amount.error', args:[incomingInvoice.expensePayment.expense.currency.plural, Utils.formatDecimals(payedAmount), Utils.formatDecimals(invoicedAmount), Utils.formatDecimals(incomingInvoice.amountInExpenseCurrency)]).toString());
		}
		/*if (!this.validateExpenseInvoiceNumber(incomingInvoice.number)) {
			incomingInvoice.errors.rejectValue('number',message(code:'incomingInvoice.number.not.valid.error').toString());
		}*/
		
		if (incomingInvoice.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond incomingInvoice.errors, view:'create'
			return
		}
		
		incomingInvoice.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'incomingInvoice.label', default: 'IncomingInvoice'), incomingInvoice.id])
				redirect incomingInvoice
			}
			'*' { respond incomingInvoice, [status: CREATED] }
		}
	}
	
	def edit(IncomingInvoice incomingInvoice) {
		respond incomingInvoice
	}

	@Transactional
	def update(IncomingInvoice incomingInvoice) {
		
		if (incomingInvoice == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		this.refresh(incomingInvoice, true);
		incomingInvoice.validate();
		if (!incomingInvoice.currency.isInvoicingCurrency) {
			incomingInvoice.errors.rejectValue('currency',message(code:'currency.not.invoicing.currency.error', args:[incomingInvoice.currency.name]).toString());
		}
		
		if(incomingInvoice.isAccounted){
			incomingInvoice.errors.rejectValue('', message(code:'incomingInvoice.already.accounted.error'));
		}
		
		if (incomingInvoice.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond incomingInvoice.errors, view:'edit'
			return
		}
		incomingInvoice.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'incomingInvoice.label', default: 'IncomingInvoice'), incomingInvoice.id])
				redirect incomingInvoice
			}
			'*'{ respond incomingInvoice, [status: OK] }
		}
	}

	@Transactional
	def delete(IncomingInvoice incomingInvoice) {

		if (incomingInvoice == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		if(incomingInvoice.isAccounted){
			incomingInvoice.errors.rejectValue('', message(code:'incomingInvoice.already.accounted.error'));
			respond incomingInvoice.errors
			return
		}
		incomingInvoice.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'incomingInvoice.label', default: 'IncomingInvoice'), incomingInvoice.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}
	
	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'incomingInvoice.label', default: 'IncomingInvoice'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}