package crm

class CommissionType {
	String name;
	String description;
	String selfInvoiceDefaultDescription;
	String internalID;
	ExpenseType expenseType;
	//appliedWhenCondition
	static hasMany = [commissionRates:CommissionRate];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:false, nullable:false, widget:'textArea', size:1..200);
		selfInvoiceDefaultDescription(blank:false, nullable:false, size:1..100);
		internalID(blank:false, nullable:false, unique:true, size:1..40);
		expenseType(nullable:false);
    }
}
