package crm

class Bank {
	String name;
	
	static hasMany = [bankAccounts:BankAccount];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..40);
	}
}
