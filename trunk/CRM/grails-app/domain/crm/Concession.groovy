package crm

class Concession {
	String adTitle;//page meta title and ad title
	String adSummary;//page meta description and ad first description
	String adText;//ad secondary description
	Boolean isNegotiable;
	Date startDate;
	Date endDate;
	Double commissionAmount;
	Float commissionPercentage;
	String description;
	PropertyDemand propertyDemand;
	Contract contract;
	Boolean publishInMLS;
	Boolean publishInPortals;
	String keys;
	String barter;
	String financing;
	Client client;
	CrmUser agent;
	Boolean isActive;
	Boolean isForRent;
	Boolean soldByCompany;
	
	static belongsTo = ManagedProperty;
	static hasMany = [managedProperties:ManagedProperty, incomes:Income, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription, advertisements:Advertisement/*,TagSelectedValue,CustomFieldSelectedValue*/];
	
	static constraints = {
		adTitle(blank:true, nullable:true, size:0..60);
		adSummary(blank:true, nullable:true, size:0..150);
		adText(blank:true, nullable:true, widget:'textArea', size:0..1000);
		isNegotiable(nullable:false);
		startDate(blank:false, nullable:false);
		endDate(blank:false, nullable:false);
		commissionAmount(blank:true, nullable:true, min:0D);
		commissionPercentage(blank:true, nullable:true, min:0F, max:100F, scale:2);
		description(blank:true, nullable:true, widget:'textArea', size:0..1500);
		propertyDemand(nullable:true);
		contract(nullable:true);
		publishInMLS(nullable:false);
		publishInPortals(nullable:false);
		keys(blank:true, nullable:true, size:0..50);
		barter(blank:true, nullable:true, size:0..50);
		financing(blank:true, nullable:true, size:0..50);
		client(nullable:false);
		agent(nullable:true);//probar con nullable true
		isActive(nullable:false);
		isForRent(nullable:false);
		soldByCompany(nullable:false);
	}
	
	public Concession(){}
	
	public Concession(def params){
		this.properties = params;
	}
	
}