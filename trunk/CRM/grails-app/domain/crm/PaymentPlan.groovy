package crm

class PaymentPlan {
	String name
	int initialFreeTimeInDays;
	int regularPaymentsInDays;
	int regularPaymentsInMonths;
	int initialPaymentPercentage;
	int numberOfParts;
	Float interestPercentage;
	InterestType interestType;
	static hasMany = [income:Income, expenses:Expense/*,ThirdPartyIncome,ThirdPartyExpense*/];
    static constraints = {
		name(blank: false, nullable:false, size:1..50);
		initialFreeTimeInDays(blank:false, nullable:false);
		regularPaymentsInDays(blank:false, nullable:false);
		regularPaymentsInMonths(blank:false, nullable:false);
		initialPaymentPercentage(blank:false, nullable:false);
		numberOfParts(blank:false, nullable:false);
		interestPercentage(blank:false, nullable:false);
		interestType(nullable:true);
    }
}
