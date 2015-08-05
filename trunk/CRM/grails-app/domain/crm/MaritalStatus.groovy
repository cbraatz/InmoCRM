package crm

class MaritalStatus {
	String name;
	static hasMany = [clients:Client, partners:Partner];
	
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..40);
	}
}
