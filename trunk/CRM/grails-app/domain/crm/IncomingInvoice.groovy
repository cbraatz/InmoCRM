package crm

class IncomingInvoice {
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
		amountInDefaultCurrency(blank:false, nullable:false);
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
}
