package crm

class Concession {
	String adSummary;
	String adText;
	boolean isNegotiable;
	Date startDate;
	Date endDate;
	int valueDegree;
	Float commissionAmount;
	Float commissionPercentage;
	String description;
	PropertyDemand propertyDemand;
	Contract contract;
	boolean publishInMLS;
	boolean publishInPortals;
	String keys;
	String barter;
	String financing;
	Client client;
	String propertyOwner;
	CrmUser owner;
	boolean isActive;
	
	static belongsTo = ManagedProperty;
	static hasMany = [managedProperties:ManagedProperty, incomes:Income, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription/*,TagSelectedValue,CustomFieldSelectedValue*/];
	
	static constraints = {
		adSummary(blank:true, nullable:true, size:0..80);
		adText(blank:true, nullable:true, widget:'textArea', size:0..500);
		isNegotiable(nullable:false);
		startDate(blank:false, nullable:false);
		endDate(blank:false, nullable:false);
		valueDegree(blank:false, nullable:false);
		commissionAmount(blank:true, nullable:true);
		commissionPercentage(blank:true, nullable:true);
		description(blank:true, nullable:true, widget:'textArea');
		propertyDemand(nullable:true);
		contract(nullable:true);
		publishInMLS(nullable:false);
		publishInPortals(nullable:false);
		keys(blank:true, nullable:true, size:0..50);
		barter(blank:true, nullable:true, size:0..50);
		financing(blank:true, nullable:true, size:0..50);
		client(nullable:false);
		propertyOwner(blank:false, nullable:false, size:1..80);
		owner(nullable:false);
		isActive(nullable:false);
	}
}
