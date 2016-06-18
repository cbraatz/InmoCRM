package crm

class Address extends CrmDomain{
	String streetOne;
	String streetTwo;
	Integer number;
	String addressLine;
	String reference;
	String description;
	String code;
	String latitude;
	String longitude;
	String homePhone;
	City city;
	Neighborhood neighborhood;
	Zone zone;
	static hasMany = [clients: Client, managedProperties:ManagedProperty, partners:Partner];
	
	static constraints = {
		streetOne (blank:true, nullable:true, size:0..45);
		streetTwo (blank:true, nullable:true, size:0..45);
		number(blank:true, nullable:true);
		addressLine (blank:false, nullable:false, widget:'textArea', size:10..500);
		reference (blank:false, nullable:false, size:10..100);
		description (blank:true, nullable:true, widget:'textArea', size:0..300);
		code (blank:true, nullable:true);
		latitude (blank:true, nullable:true, size:0..50);
		longitude (blank:true, nullable:true, size:0..50);
		homePhone (blank:true, nullable:true, size:0..40);
		city (nullable:false);
		neighborhood (nullable:true);
		zone (nullable:true);
	}
	@Override
	public String toString(){
		return this.addressLine;
	}
	
	@Override
	public static SearchAttribute[] searchByAttributes() {
		return [new SearchAttribute("addressLine"),new SearchAttribute("reference"),new SearchAttribute("description")];
	}
	@Override
	public static getDefaultPropertyName(){
		return "addressLine";
	}
}
