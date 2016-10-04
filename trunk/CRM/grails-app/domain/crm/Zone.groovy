package crm

class Zone extends CrmDomain{
    String name;
	String description;
	static hasMany = [addresses: Address, propertyDemands:PropertyDemand];
	
    static constraints = {
		name(blank: false, nullable:false, unique: true, size:1..50);
		description(blank: false, nullable: false, widget:'textArea', size: 1..200);
    }
	@Override
	public static String getPluralName(){
		return "zones";
	}
}
