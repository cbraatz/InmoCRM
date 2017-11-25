package crm

class Address extends CrmDomain{
	String streetOne;
	String streetTwo;
	String number;
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
	static hasMany = [clients: Client, managedProperties:ManagedProperty, partners:Partner, valuePoints:ValuePoint];
	
	static constraints = {
		streetOne (blank:true, nullable:true, size:0..45);
		streetTwo (blank:true, nullable:true, size:0..45);
		number(blank:true, nullable:true, size:0..10);
		addressLine (blank:false, nullable:false, widget:'textArea', size:10..500);
		reference (blank:true, nullable:true, size:10..100);
		description (blank:true, nullable:true, widget:'textArea', size:0..300);
		code (blank:true, nullable:true);
		latitude (blank:true, nullable:true, size:0..50);
		longitude (blank:true, nullable:true, size:0..50);
		homePhone (blank:true, nullable:true, size:0..40);
		city (nullable:false);
		neighborhood (nullable:true);
		zone (nullable:true);
	}
	/*static mapping = {
		id generator: 'hilo',
		params: [table: 'hi_value', column: 'next_value', max_lo: 100]
	}*/
	@Override
	public String toString(){
		return this.addressLine;
	}
	
	@Override
	public static SearchAttribute[] searchByAttributes() {
		return [new SearchAttribute("addressLine"),new SearchAttribute("reference"),new SearchAttribute("description")];
	}
	@Override
	public static String getDefaultPropertyName(){
		return "addressLine";
	}
	@Override
	public static String getPluralName(){
		return "addresses";
	}
}
