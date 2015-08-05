package crm

import java.util.Date;

class IssuedInvoice {
	String number;
	Date date;
	Float amount;
	Float amountInDefaultCurrency;
	Float deductibleTax;
	Float totalTax;
	boolean isAccounting;
	boolean isCanceled;
	Currency currency;
	Currency defaultCurrency;
	IncomePayment incomePayment;
	Client client;
	//ThirdPartyPayment thirdPartyPayment; //nullable:true
    static constraints = {
		number(blank: false, nullable:false, unique:'client', size:1..40);
		date(blank: false, nullable:false);
		amount(blank: false, nullable:false);
		amountInDefaultCurrency(blank: false, nullable:false);
		deductibleTax(blank: false, nullable:false);
		totalTax(blank: false, nullable:false);
		isAccounting(nullable:false);
		isCanceled(nullable:false);
		currency(nullable:false);
		defaultCurrency(nullable:false);
		incomePayment(nullable:true);
		client(nullable:false);
    }
}
