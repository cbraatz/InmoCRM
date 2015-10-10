package crm

class IncomingInvoice {
	String number;
	Date date;
	Double amount;
	Double amountInDefaultCurrency;
	Double deductibleTax;
	Double totalTax;
	Boolean isAccounting;
	Boolean isSelfInvoice;
	Currency currency;
	Currency defaultCurrency;
	ExpensePayment expensePayment;
	Vendor vendor;
    static constraints = {
		number(blank: false, nullable:false, unique:'vendor', size:1..40);
		date(blank: false, nullable:false);
		amount(blank: false, nullable:false);
		amountInDefaultCurrency(blank: false, nullable:false);
		deductibleTax(blank: false, nullable:false);
		totalTax(blank: false, nullable:false);
		isAccounting(nullable:false);
		isSelfInvoice(nullable:false);
		currency(nullable:false);
		defaultCurrency(nullable:false);
		expensePayment(nullable:true);
		vendor(nullable:false);
    }
}
