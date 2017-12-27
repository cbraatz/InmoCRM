package crm

class Office extends CrmDomain{
	String name;
	String description;
	String internalID;
	Address address;
	static hasMany = [partners:Partner/*Checkouts*/];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:false, nullable:false, widget:'textArea', size:10..200);
		internalID(blank:false, nullable:false, unique:true, size:1..10);
		address(nullable:false);
	}
	@Override
	public static String getPluralName(){
		return "offices";
	}
}
