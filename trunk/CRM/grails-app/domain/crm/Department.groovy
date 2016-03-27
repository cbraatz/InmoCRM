package crm

class Department {
	String name;
	Country country;
	static hasMany = [cities: City, propertyDemands:PropertyDemand];
	
	static constraints = {
		name(blank: false, nullable: false, size:1..50);
		country(nullable: false);
	}
}
