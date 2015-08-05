package crm

class Zone {
    String name;
	String description;
	static hasMany = [addresses: Address, propertyDemands:PropertyDemand];
	
    static constraints = {
		name(blank: false, nullable:false, unique: true, size:1..40);
		description(blank: false, nullable: false, widget:'textArea', size: 1..200);
    }
}
