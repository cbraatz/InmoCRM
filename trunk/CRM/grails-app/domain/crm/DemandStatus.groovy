package crm

class DemandStatus extends CrmDomain{
	String name;
	Boolean isNew;
	Boolean isClosed;
	static hasMany = [propertyDemands:PropertyDemand/*InsuranceDemand*/];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		isNew(nullable:false);
		isClosed(nullable:false);
	}
	@Override
	public static String getPluralName(){
		return "demandStatuses";
	}
}
