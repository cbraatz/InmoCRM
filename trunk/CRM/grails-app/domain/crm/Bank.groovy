package crm

class Bank extends CrmDomain{
	String name;
	
	static hasMany = [bankAccounts:BankAccount, paymentDocuments:PaymentDocument];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
	}
}
