package crm

class IncomingInvoice extends CrmDomain{
	String number;
	Date date;
	Double amount;
	Double amountInDefaultCurrency;
	Double amountInExpenseCurrency;
	Double deductibleAmount;
	Double totalTax;
	Boolean isAccounting;
	Boolean isSelfInvoice;
	Boolean isAccounted;
	Currency currency;
	ExpensePayment expensePayment;
	Vendor vendor;
    static constraints = {
		number(blank: false, nullable:false, size:1..40);
		date(blank: false, nullable:false);
		amount(blank: false, nullable:false);
		amountInDefaultCurrency(blank:true, nullable:true);
		amountInExpenseCurrency(blank:true, nullable:true);
		deductibleAmount(blank:false, nullable:false);
		totalTax(blank:true, nullable:true);
		isAccounting(nullable:false);
		isSelfInvoice(nullable:false);
		isAccounted(nullable:false);
		currency(nullable:false);
		expensePayment(nullable:true);
		vendor(nullable:false);
    }
	
	public Double getIncomingInvoiceAmountInExpenseCurrency(){
		List<IncomingInvoice> list=IncomingInvoice.executeQuery("from IncomingInvoice where expensePayment = :e",[e: this.expensePayment]);
		double amount=0;
		if(list.size()>0){
			list.each{
				amount=amount+it.amountInExpenseCurrency.doubleValue();
			}
		}
		return amount;
	}
}
