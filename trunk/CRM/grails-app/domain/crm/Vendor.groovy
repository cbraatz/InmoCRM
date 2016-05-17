package crm

class Vendor extends CrmDomain{
	String name;
	String TIN;
	String phone;
	String description;
	String emailAddress;
	static hasMany = [expenses:Expense, incomingInvoices:IncomingInvoice];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		TIN(blank:false, nullable:false, size:1..40);
		phone(blank:false, nullable:false, size:1..40);
		description(blank:false, nullable:false, widget:'textArea', size:1..200);
		emailAddress(blank:true, nullable:true, email: true);
    }
}
