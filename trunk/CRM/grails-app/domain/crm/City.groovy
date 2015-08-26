package crm

class City {
	String name;
	Department department;
	static hasMany = [addresses:Address, propertyDemands:PropertyDemand/*,Office*/];

    static constraints = {
		name(blank: false, nullable: false, unique: 'department', size:1..50);
		department(nullable: false);
    }
}
