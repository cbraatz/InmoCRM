package crm

class Client extends CrmDomain{
	String name;
	Integer IDNumber;
	Date birthDate;
	String description;
	String phone;
	String phone2;
	String notificationPhone;
	String emailAddress;
	Country nationality;
	Profession profession;
	CrmUser owner;
	MaritalStatus maritalStatus;
	Gender gender;
	Address address;
	ClientCategory category;
	String timeAvailability;
	Boolean isActive;
	Boolean readsEmail;
	Boolean readsSms;
	Boolean receiveNotifications;
	Boolean isProspectiveClient;
	
	
	static hasMany=[propertyDemands:PropertyDemand, concessions:Concession, incomes:Income, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription, issuedInvoices:IssuedInvoice,
					managedProperties:ManagedProperty, contacts:Contact/*InsuranceProposal,InsuredGood,ThirdPartyPayment,ThirdPartyIncome,TagSelectedValue,CustomFieldSelectedValue,InsuranceDemand,*/];

    static constraints = {
		name(blank:false, nullable:false, size:1..100);
		IDNumber(blank:true, nullable:true);
		birthDate(blank:true, nullable:true);
		description(blank:true, nullable:true, size:0..200);
		phone(blank:true, nullable:true, size:0..40);
		phone2(blank:true, nullable:true, size:0..40);
		notificationPhone(blank:true, nullable:true, size:0..40);
		emailAddress(blank:true, nullable:true, email: true);
		nationality(nullable:true);
		profession(nullable:true);
		owner(nullable:false);
		maritalStatus(nullable:true);
		gender(nullable:true);
		address(nullable:true);
		category(nullable:true);
		timeAvailability(blank:true, nullable:true, size:0..50);
		isActive(nullable:false);
		readsEmail(nullable:true);
		readsSms(nullable:true);
		receiveNotifications(nullable:true);
		isProspectiveClient(nullable:false);
	}
	
	@Override
	public static SearchAttribute[] searchByAttributes() {
		return [new SearchAttribute("name")];
	}
	public String getCity(){
		return address.city.name;
	}
	@Override
	public static String getPluralName(){
		return "clients";
	}
}
