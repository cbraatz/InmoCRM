package crm

import static org.springframework.http.HttpStatus.*

import java.math.BigDecimal;
import java.math.RoundingMode
import java.util.Date;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class IssuedInvoiceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond IssuedInvoice.list(params), model:[issuedInvoiceCount: IssuedInvoice.count()]
    }

    def show(IssuedInvoice issuedInvoice) {
        respond issuedInvoice
    }
    def create(IssuedInvoice updatedIssuedInvoice) {
		IssuedInvoice issuedInvoice=new IssuedInvoice(params);
		issuedInvoice.incomePayment=IncomePayment.get(params.pid);
		if(issuedInvoice.incomePayment){
			issuedInvoice.client=(issuedInvoice?.incomePayment?.income?.client);
			issuedInvoice.amountInIncomeCurrency=issuedInvoice.incomePayment.getPayedTotalAmount().doubleValue()-issuedInvoice.getIssuedInvoiceAmountInIncomeCurrency().doubleValue();
			issuedInvoice.amount=issuedInvoice.amountInIncomeCurrency.doubleValue();
			issuedInvoice.currency=issuedInvoice.incomePayment.income.currency;
			issuedInvoice.date=Utils.removeTimeFromDate(new Date());
			issuedInvoice.amountInDefaultCurrency=null;//is completed automatically once accounting resume is done
			issuedInvoice.number="001-001-";
			float tr=issuedInvoice.incomePayment.income.incomeType.taxRate.percentage.floatValue();
			issuedInvoice.totalTax=issuedInvoice.amount.doubleValue()*tr/(100+tr);
			issuedInvoice.isAccounting=true;
			issuedInvoice.isAccounted=false;
			issuedInvoice.isCanceled=false;
			respond issuedInvoice;
		}else{
			render(view: "/error", model:[message:message(code: 'issuedInvoice.income.id.not.found.label', default: 'Income ID not found or not correct')]);
		}  
    }
	def refresh(IssuedInvoice issuedInvoice, boolean returnToSave){		
		if(issuedInvoice.incomePayment.income.currency.id == issuedInvoice.currency.id){
			issuedInvoice.amount=issuedInvoice.amountInIncomeCurrency.doubleValue();
			if(issuedInvoice.currency.isDefault){
				issuedInvoice.amountInDefaultCurrency=issuedInvoice.amount;
			}
		}else{
			if(!issuedInvoice.incomePayment.income.currency.isDefault && !issuedInvoice.currency.isDefault){//si ninguno es en GS
				Currency defaultCurrency=Currency.getDefaultCurrency();
				CurrencyExchange ce=CurrencyExchange.getCurrencyExchangeRate(issuedInvoice.date, defaultCurrency, issuedInvoice.incomePayment.income.currency);
				CurrencyExchange ce2=CurrencyExchange.getCurrencyExchangeRate(issuedInvoice.date, defaultCurrency, issuedInvoice.currency);
				BigDecimal bd=new BigDecimal(issuedInvoice.amountInIncomeCurrency.toString()).multiply(new BigDecimal(ce.buy.toString()));
				issuedInvoice.amount=Utils.round(bd.divide(new BigDecimal(ce2.sell.toString()), RoundingMode.HALF_UP).doubleValue(), issuedInvoice.currency.hasDecimals);
			}else{
				if(issuedInvoice.incomePayment.income.currency.isDefault){//si el ingreso está en GS y la factura en otra moneda
					CurrencyExchange ce=CurrencyExchange.getCurrencyExchangeRate(issuedInvoice.date, issuedInvoice.incomePayment.income.currency, issuedInvoice.currency);
					issuedInvoice.amount=Utils.round(issuedInvoice.amountInIncomeCurrency.doubleValue()/ce.sell, issuedInvoice.currency.hasDecimals);
				}else{
					CurrencyExchange ce=CurrencyExchange.getCurrencyExchangeRate(issuedInvoice.date, issuedInvoice.currency, issuedInvoice.incomePayment.income.currency);
					issuedInvoice.amount=Utils.round(issuedInvoice.amountInIncomeCurrency.doubleValue()*ce.buy, issuedInvoice.currency.hasDecimals);
				}
			}
		}
		float tr=issuedInvoice.incomePayment.income.incomeType.taxRate.percentage.floatValue();
		issuedInvoice.totalTax=Utils.round(issuedInvoice.amount.doubleValue()*tr/(100+tr), issuedInvoice.currency.hasDecimals);
		if(returnToSave){
			return issuedInvoice;
		}else{
			respond issuedInvoice, view:'create';
		}
	}
    @Transactional
    def save(IssuedInvoice issuedInvoice) {
		this.refresh(issuedInvoice, true);
        if (issuedInvoice == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }	
		issuedInvoice.validate();
		if (!issuedInvoice.currency.isInvoicingCurrency) {
			issuedInvoice.errors.rejectValue('currency',message(code:'currency.not.invoicing.currency.error', args:[issuedInvoice.currency.name]).toString());
			transactionStatus.setRollbackOnly();
			respond issuedInvoice, view:'create';
			return;
		}
		double invoicedAmount=issuedInvoice.incomePayment.getInvoicedTotalAmount();
		double payedAmount=issuedInvoice.incomePayment.getPayedTotalAmount()
		if(issuedInvoice.amountInIncomeCurrency + invoicedAmount > payedAmount){
			issuedInvoice.errors.rejectValue('amountInIncomeCurrency', message(code:'issuedInvoice.too.big.amount.error', args:[issuedInvoice.incomePayment.income.currency.plural, Utils.formatDecimals(payedAmount), Utils.formatDecimals(invoicedAmount), Utils.formatDecimals(issuedInvoice.amountInIncomeCurrency)]).toString());
			transactionStatus.setRollbackOnly();
			respond issuedInvoice.errors, view:'create';
			return;
		}
        if (issuedInvoice.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond issuedInvoice.errors, view:'create'
            return
        }
		
        issuedInvoice.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'issuedInvoice.label', default: 'IssuedInvoice'), issuedInvoice.id])
                redirect issuedInvoice
            }
            '*' { respond issuedInvoice, [status: CREATED] }
        }
    }

    def edit(IssuedInvoice issuedInvoice) {
        respond issuedInvoice
    }

    @Transactional
    def update(IssuedInvoice issuedInvoice) {
        if (issuedInvoice == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (issuedInvoice.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond issuedInvoice.errors, view:'edit'
            return
        }

        issuedInvoice.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'issuedInvoice.label', default: 'IssuedInvoice'), issuedInvoice.id])
                redirect issuedInvoice
            }
            '*'{ respond issuedInvoice, [status: OK] }
        }
    }

    @Transactional
    def delete(IssuedInvoice issuedInvoice) {

        if (issuedInvoice == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        issuedInvoice.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'issuedInvoice.label', default: 'IssuedInvoice'), issuedInvoice.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'issuedInvoice.label', default: 'IssuedInvoice'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
