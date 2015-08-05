package crm

class ClientCategory {
	String name;
	String description;
	static hasMany = [clients: Client];
	
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..40);
		description(blank:false, nullable:false, size:10..200);
	}
}
