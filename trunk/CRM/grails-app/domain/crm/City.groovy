package crm

class City {
	String name;
	Department department;
	static hasMany = [addresses:Address, neighborhoods:Neighborhood, propertyDemands:PropertyDemand/*,Office*/];

    static constraints = {
		name(blank: false, nullable: false, unique: 'department', size:1..40);
		department(nullable: false);
    }
}
