package crm

class Neighborhood {
	String name;
	String description;
	City city;
	static hasMany = [addresses: Address, propertyDemands:PropertyDemand,/*Office*/];
	
    static constraints = {
		name(blank:false, nullable:false, unique:'city', size:1..40);
		description(blank:true, nullable:true, widget:'textArea', size:0..100);
		city(nullable:false);
    }
}
