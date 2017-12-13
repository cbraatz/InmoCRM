package crm

class DemandStatus extends CrmDomain{
	String name;
	String internalID;
	static hasMany = [propertyDemands:PropertyDemand/*InsuranceDemand*/];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		internalID(blank:false, nullable:false, unique:true, size:1..10);
	}
	@Override
	public static String getPluralName(){
		return "demandStatuses";
	}
}
