package crm

class InterestType {
	String name;
	String description;
	String internalID;
	boolean isSimpleInterest;
	static hasMany = [paymentPlans:PaymentPlan];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:false, nullable:false, widget:'textArea', size:1..200);
		internalID(blank:false, nullable:false, size:1..40);
		isSimpleInterest(nullable:false);
	}
}
