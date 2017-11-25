package crm

class Comment extends CrmDomain{
	String comment;
	Date date;
	CrmUser crmUser;
	ManagedProperty managedProperty;
	Client client;
	PropertyDemand propertyDemand;
	Concession concession;
	Task task;
	//InsuranceDemand insuranceDemand;
    static constraints = {
		comment(blank:false, nullable:false, widget:'textArea', size:1..400);
		date(blank:false, nullable:false);
		crmUser(nullable:false);
		managedProperty(nullable:true);
		client(nullable:true);
		propertyDemand(nullable:true);
		concession(nullable:true);
    }
	@Override
	public static String getPluralName(){
		return "comments";
	}
}
