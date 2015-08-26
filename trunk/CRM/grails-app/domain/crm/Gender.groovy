package crm

class Gender {
	String name;
	static hasMany = [partners:Partner, clients:Client];
	
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
	}
}
