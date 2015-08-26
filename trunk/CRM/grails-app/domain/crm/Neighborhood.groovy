package crm

class Neighborhood {
	String name;
	String description;
	static hasMany = [addresses: Address, propertyDemands:PropertyDemand/*Office*/];
	
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:true, nullable:true, widget:'textArea', size:0..100);
    }
}
