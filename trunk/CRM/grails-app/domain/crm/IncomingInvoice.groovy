package crm

class IncomingInvoice {
	String number;
	Date date;
	Float amount;
	Float amountInDefaultCurrency;
	Float deductibleTax;
	Float totalTax;
	boolean isAccounting;
	boolean isSelfInvoice;
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
		expensePayment(nullable:false);
		vendor(nullable:false);
    }
}
