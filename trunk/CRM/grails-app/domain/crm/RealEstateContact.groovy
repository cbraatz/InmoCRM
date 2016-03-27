package crm

import java.util.Date;

class RealEstateContact {
	Date date;
	String description;
	Concession concession;
	Client client;
	Partner partner;
	RealEstateContactType realEstateContactType;
	
	static constraints = {
		date(blank:false, nullable:false);
		description(blank:false, nullable:false, widget:'textArea', size:1..300);
		concession(blank:false, nullable:false);
		client(blank:false, nullable:false);
		partner(blank:false, nullable:false);
		realEstateContactType(blank:false, nullable:false);
	}
}