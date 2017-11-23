package crm

import java.util.Date;

class Contact extends CrmDomain{
	Date date;
	String description;
	ManagedProperty managedProperty;
	Client client;
	CrmUser crmUser;
	ContactType contactType;
	
	static constraints = {
		date(blank:false, nullable:false);
		description(blank:false, nullable:false, widget:'textArea', size:1..300);
		managedProperty(blank:false, nullable:false);
		client(nullable:false);
		crmUser(nullable:false);
		contactType(nullable:false);
	}
	@Override
	public static String getPluralName(){
		return "contracts";
	}
}
