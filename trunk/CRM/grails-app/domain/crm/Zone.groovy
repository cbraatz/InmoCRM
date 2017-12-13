package crm

class Zone extends CrmDomain{
    String name;
	String description;
	Boolean isCenter;
	City city;
	static hasMany = [addresses: Address, propertyDemands:PropertyDemand, neighborhoods:Neighborhood];
	
    static constraints = {
		name(blank: false, nullable:false, unique: true, size:1..50);
		description(blank: false, nullable: false, widget:'textArea', size: 1..200);
		isCenter(blank: false, nullable: false);
		city (blank: false, nullable: false);
    }
	@Override
	public static String getPluralName(){
		return "zones";
	}
	
	public static String getCenterZoneName(){
		return "Centro";
	}
}
