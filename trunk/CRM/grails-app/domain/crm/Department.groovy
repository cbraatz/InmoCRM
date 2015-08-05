package crm

class Department {
	String name;
	Country country;
	static hasMany = [cities: City, propertyDemands:PropertyDemand];
	
	static constraints = {
		name(blank: false, nullable: false, unique: 'country', size:1..40);
		country(nullable: false);
	}
}
