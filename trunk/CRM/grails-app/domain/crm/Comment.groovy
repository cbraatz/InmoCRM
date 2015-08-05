package crm

class Comment {
	String comment;
	Date date;
	CrmUser owner;
	ManagedProperty managedProperty;
	Client client;
	PropertyDemand propertyDemand;
	Concession concession;
	//Task task;
	//InsuranceDemand insuranceDemand;
    static constraints = {
		comment(blank:false, nullable:false, widget:'textArea', size:1..400);
		date(blank:false, nullable:false);
		owner(nullable:false);
		managedProperty(nullable:true);
		client(nullable:true);
		propertyDemand(nullable:true);
		concession(nullable:true);
    }
}
