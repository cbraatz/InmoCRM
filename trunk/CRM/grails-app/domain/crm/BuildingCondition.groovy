package crm

class BuildingCondition {
	String name;
	
	static hasMany = [propertyDemands:PropertyDemand, buildings:Building];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
	}
}
