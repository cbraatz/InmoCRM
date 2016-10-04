package crm

class Usage extends CrmDomain{
	String name;
	String description;
	static hasMany = [propertyDemands:PropertyDemand, propertyUsages:PropertyUsage];
	
	static constraints = {
		name(blank: false, nullable:false, unique:true, size:1..50);
		description(blank: false, nullable:false, size:1..100);
	}
	@Override
	public static String getPluralName(){
		return "usages";
	}
}
