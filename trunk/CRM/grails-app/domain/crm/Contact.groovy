package crm

import java.util.Date;

class Contact {
	Date date;
	String description;
	Concession concession;
	Client client;
	Partner partner;
	ContactType contactType;
	
	static constraints = {
		date(blank:false, nullable:false);
		description(blank:false, nullable:false, widget:'textArea', size:1..300);
		concession(blank:false, nullable:false);
		client(blank:false, nullable:false);
		partner(blank:false, nullable:false);
		contactType(blank:false, nullable:false);
	}
}
