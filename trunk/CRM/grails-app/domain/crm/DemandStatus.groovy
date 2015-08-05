package crm

class DemandStatus {
	String name;
	boolean isNew;
	boolean isClosed;
	static hasMany = [propertyDemands:PropertyDemand/*InsuranceDemand*/];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..40);
		isNew(nullable:false);
		isClosed(nullable:false);
	}
}
