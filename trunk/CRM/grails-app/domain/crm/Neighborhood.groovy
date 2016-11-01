package crm

class Neighborhood extends CrmDomain{
	String name;
	String description;
	City city;
	static hasMany = [addresses: Address, propertyDemands:PropertyDemand/*Office*/];
	
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:true, nullable:true, widget:'textArea', size:0..100);
		city(nullable: false);
    }
	@Override
	public static String getPluralName(){
		return "neighborhoods";
	}
}
