package crm

class Concession extends CrmDomain{
	Boolean isNegotiable;
	Date startDate;
	Date endDate;
	Double totalPrice;
	Double totalCommission;
	String description;
	PropertyDemand propertyDemand;
	Contract contract;
	Boolean publishInMLS;
	Boolean publishInPortals;
	String barter;
	String financing;
	Client client;
	CrmUser crmUser;
	Boolean isActive;
	Boolean isForRent;
	static belongsTo = ManagedProperty;
	static hasMany = [managedProperties:ManagedProperty, incomes:Income, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription, actions:Action, contacts:Contact, commissionsByConcession:CommissionByConcession/*,TagSelectedValue,CustomFieldSelectedValue*/];
	
	static constraints = {
		isNegotiable(nullable:false);
		startDate(blank:false, nullable:false);
		endDate(blank:false, nullable:false);
		totalPrice(blank:false, nullable:false, min:0D);
		totalCommission(blank:false, nullable:false, min:0D);
		description(blank:true, nullable:true, widget:'textArea', size:0..1500);
		propertyDemand(nullable:true);
		contract(nullable:true);
		publishInMLS(nullable:false);
		publishInPortals(nullable:false);
		barter(blank:true, nullable:true, size:0..50);
		financing(blank:true, nullable:true, size:0..50);
		client(nullable:false);
		crmUser(nullable:true);
		isActive(nullable:false);
		isForRent(nullable:false);
	}
	
	public Concession(){}
	
	public Concession(def params){
		this.properties = params;
	}
	
	@Override
	public static SearchAttribute[] searchByAttributes() {
		return [new SearchAttribute("id", false),new SearchAttribute("description")];
	}
	@Override
	public static String getDefaultPropertyName(){
		return "id";
	}
	@Override
	public static String getPluralName(){
		return "concessions";
	}
}