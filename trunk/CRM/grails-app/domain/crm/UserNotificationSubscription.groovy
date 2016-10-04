package crm

class UserNotificationSubscription extends CrmDomain{
	CrmUser crmUser;
	PropertyDemand propertyDemand;
	ManagedProperty managedProperty;
	Concession concession;
	Client client;
	//task
	//policy
	//insuranceDemand
    static constraints = {
		crmUser(nullable:false);
		propertyDemand(nullable:true);
		managedProperty(nullable:true);
		concession(nullable:true);
		client(nullable:true);
    }
	@Override
	public static String getPluralName(){
		return "userNotificationSubscriptions";
	}
}
