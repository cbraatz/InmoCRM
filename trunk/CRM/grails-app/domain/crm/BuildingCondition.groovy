package crm

class BuildingCondition extends CrmDomain{
	String name;
	
	static hasMany = [propertyDemands:PropertyDemand, buildings:Building];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
	}
	@Override
	public static String getPluralName(){
		return "buildingConditions";
	}
}
