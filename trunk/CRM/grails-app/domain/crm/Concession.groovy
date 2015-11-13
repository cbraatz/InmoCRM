package crm

class Concession {
	String adSummary;
	String adText;
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
	CrmUser addedBy;
	Boolean isActive;
	Boolean isSoldByCompany;
	static belongsTo = ManagedProperty;
	static hasMany = [managedProperties:ManagedProperty, incomes:Income, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription/*,TagSelectedValue,CustomFieldSelectedValue*/];
	
	static constraints = {
		adSummary(blank:true, nullable:true, size:0..80);
		adText(blank:true, nullable:true, widget:'textArea', size:0..500);
		isNegotiable(nullable:false);
		startDate(blank:false, nullable:false);
		endDate(blank:false, nullable:false);
		commissionAmount(blank:true, nullable:true, min:0D);
		commissionPercentage(blank:true, nullable:true, min:0F, max:100F, scale:2);
		description(blank:true, nullable:true, widget:'textArea', size:0..200);
		propertyDemand(nullable:true);
		contract(nullable:true);
		publishInMLS(nullable:false);
		publishInPortals(nullable:false);
		keys(blank:true, nullable:true, size:0..50);
		barter(blank:true, nullable:true, size:0..50);
		financing(blank:true, nullable:true, size:0..50);
		client(nullable:false);
		addedBy(nullable:true);//probar con nullable true
		isActive(nullable:false);
		isSoldByCompany(nullable:false);
	}
	
	public Concession(){}
	
	public Concession(def params){
		this.properties = params;
	}
	
}