package crm

class PriorityLevel {
	String name;
	String color;
	int level;
	static hasMany = [propertyDemands:PropertyDemand/*InsuranceDemand*/];
    static constraints = {
		name(blank: false, nullable:false, unique:true, size:1..40);
		color(blank: false, nullable:false, unique:true, size:1..15);
		level(blank: false, nullable:false, unique:true);
    }
}
