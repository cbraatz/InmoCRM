package crm

class PaymentPlan {
	String name
	Integer initialFreeTimeInDays;
	Integer regularPaymentsInDays;
	Integer regularPaymentsInMonths;
	Integer initialPaymentPercentage;
	Integer numberOfParts;
	Float interestPercentage;
	InterestType interestType;
	static hasMany = [income:Income, expenses:Expense/*,ThirdPartyIncome,ThirdPartyExpense*/];
    static constraints = {
		name(blank: false, nullable:false, size:1..50);
		initialFreeTimeInDays(blank:false, nullable:false);
		regularPaymentsInDays(blank:false, nullable:false);
		regularPaymentsInMonths(blank:false, nullable:false);
		initialPaymentPercentage(blank:false, nullable:false, min:0, max:100, scale:2);
		numberOfParts(blank:false, nullable:false);
		interestPercentage(blank:false, nullable:false, min:0F, max:100F, scale:2);
		interestType(nullable:true);
    }
}
