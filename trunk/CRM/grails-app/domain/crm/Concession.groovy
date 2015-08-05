package crm

class Concession {
	String adSummary;
	String adText;
	String publicCashPrice;
	String publicCreditPrice;
	Float price;
	Currency currency;
	Float appraisedPrice;
	Float clientInitialPrice;
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
	CrmUser creator;
	boolean isConfirmed;
	boolean isSoldByCompany;
	boolean isCanceled;
	static belongsTo = ManagedProperty;
	static hasMany = [managedProperties:ManagedProperty, incomes:Income, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription/*,TagSelectedValue,CustomFieldSelectedValue*/];
	
	static constraints = {
		adSummary(blank:true, nullable:true, size:0..80);
		adText(blank:true, nullable:true, widget:'textArea', size:0..500);
		publicCashPrice(blank:false, nullable:false, size:1..25);
		publicCreditPrice(blank:true, nullable:true, size:0..50);
		price(blank:false, nullable:false);
		currency(blank:false, nullable:false);
		appraisedPrice(blank:false, nullable:false);
		clientInitialPrice(blank:false, nullable:false);
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
		creator(nullable:false);
		isConfirmed(nullable:false);
		isSoldByCompany(nullable:false);
		isCanceled(nullable:false);
	}
}
