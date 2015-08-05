package crm

class Income {
	Date date;
	String description;
	Float amount;
	boolean isPaid;
	boolean isCredit;
	PaymentPlan paymentPlan;
	Currency currency;
	Client client;
	Concession concession;
	IncomeType incomeType;
	static hasMany = [incomePayments:IncomePayment/*,GoodsSaleDetail*/];
		
    static constraints = {
		date(blank:false, nullable:false);
		description(blank:true, nullable:true, widget:'textArea', size:0..200);
		amount(blank:false, nullable:false);
		isCredit(nullable:false);
		isPaid(nullable:false);
		paymentPlan(nullable:true);
		currency(nullable:false);
		client(nullable:true);
		concession(nullable:true);
		incomeType(nullable:true);
    }
}
