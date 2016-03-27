package crm

class RealEstateContactType {
	String name;
	String description;
	Boolean email;
	Boolean phoneCall;
	Boolean chat;
	Boolean personally;
	Boolean showing;
	
	static hasMany=[realEstateContacts: RealEstateContact];
    static constraints = {
		name(blank:false, nullable:false, size:1..50);
		description(blank:true, nullable:true, size:0..100);
		email(blank:false, nullable:false);
		phoneCall(blank:false, nullable:false);
		chat(blank:false, nullable:false);
		personally(blank:false, nullable:false);
		showing(blank:false, nullable:false);
    }
}
