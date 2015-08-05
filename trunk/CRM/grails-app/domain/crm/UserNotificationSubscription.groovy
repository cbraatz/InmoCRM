package crm

class UserNotificationSubscription {
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
}
