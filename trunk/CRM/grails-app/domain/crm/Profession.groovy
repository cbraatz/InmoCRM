package crm

class Profession extends CrmDomain{
	String name;
	static hasMany = [clients: Client, partners:Partner];
	
	static constraints = {
		name(blank: false, nullable:false, unique:true, size:1..50);
	}
}
