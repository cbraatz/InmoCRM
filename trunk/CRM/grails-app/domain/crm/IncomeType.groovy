package crm

class IncomeType {
	String name;
	String description;
	TaxRate taxRate;
	String billingDefaultDescription;
	String internalID;
	static hasMany = [income:Income];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..40);
		description(blank:false, nullable:false, widget:'textArea', size:1..200);
		taxRate(nullable:false);
		billingDefaultDescription(blank:false, nullable:false, size:1..100);
		internalID(blank:false, nullable:false, size:1..40);
    }
}
