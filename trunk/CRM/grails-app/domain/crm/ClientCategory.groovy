package crm

class ClientCategory extends CrmDomain{
	String name;
	String description;
	static hasMany = [clients: Client];
	
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:false, nullable:false, size:10..200);
	}
}
