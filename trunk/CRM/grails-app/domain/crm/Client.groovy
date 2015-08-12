package crm

class Client {
	String name;
	String lastName;
	String IDNumber;
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
	boolean isActive;
	boolean readsEmail;
	boolean readsSms;
	boolean receiveNotifications;
	boolean isProspectiveClient;
	
	
	static hasMany=[propertyDemands:PropertyDemand, concessions:Concession, incomes:Income, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription, issuedInvoices:IssuedInvoice,
					managedProperties:ManagedProperty/*InsuranceProposal,InsuredGood,ThirdPartyPayment,ThirdPartyIncome,TagSelectedValue,CustomFieldSelectedValue,InsuranceDemand,*/];

    static constraints = {
		name(blank:false, nullable:false, size:1..50);
		lastName(blank:false, nullable:false, size:1..50);
		IDNumber(blank:true, nullable:true, size:0..30);
		birthDate(blank:true, nullable:true);
		description(blank:true, nullable:true, size:0..200);
		phone(blank:false, nullable:false, size:5..30);
		phone2(blank:true, nullable:true, size:0..30);
		notificationPhone(blank:true, nullable:true, size:0..30);
		emailAddress(blank:true, nullable:true, email: true);
		nationality(nullable:true);
		profession(nullable:true);
		owner(nullable:false);
		maritalStatus(nullable:true);
		gender(nullable:true);
		address(nullable:true);
		category(nullable:true);
		isActive(nullable:false);
		readsEmail(nullable:true);
		readsSms(nullable:true);
		receiveNotifications(nullable:true);
		isProspectiveClient(nullable:false);
		
	}
}
