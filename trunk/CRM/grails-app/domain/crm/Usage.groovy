package crm

class Usage {
	String name;
	String description;
	static hasMany = [propertyDemands:PropertyDemand, propertyUsages:PropertyUsage];
	
	static constraints = {
		name(blank: false, nullable:false, unique:true, size:1..40);
		description(blank: false, nullable:false, size:1..100);
	}
}
