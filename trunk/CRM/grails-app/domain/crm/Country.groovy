package crm

class Country {
	String name;
	static hasMany = [departments: Department, clients:Client, currencies:Currency];
	
    static constraints = {
		name(blank: false, nullable:false, unique: true, size:1..40);
    }
}