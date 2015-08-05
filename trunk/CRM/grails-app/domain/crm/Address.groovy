package crm

class Address {
	String streetOne;
	String streetTwo;
	String address;
	String reference;
	String description;
	String zipCode;
	String gpsX;
	String gpsY;
	String homePhone;
	City city;
	Neighborhood neighborhood;
	Zone zone;
	static hasMany = [clients: Client, managedProperties:ManagedProperty];
	
	static constraints = {
		streetOne (blank:true, nullable:true, size:0..45);
		streetTwo (blank:true, nullable:true, size:0..45);
		address (blank:false, nullable:false, widget:'textArea', size:10..500);
		reference (blank:false, nullable:false, size:10..100);
		description (blank:true, nullable:true, widget:'textArea', size:0..255);
		zipCode (blank:true, nullable:true, size:0..10);
		gpsX (blank:true, nullable:true, size:0..30);
		gpsY (blank:true, nullable:true, size:0..30);
		homePhone (blank:true, nullable:true, size:0..30);
		city (nullable:false);
		neighborhood (nullable:true);
		zone (nullable:true);
	}
	@Override
	public String toString(){
		return this.address;
	}
}
