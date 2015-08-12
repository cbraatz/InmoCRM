package crm

class ManagedProperty {
	String title;
	String description;
	String measures;
	String publicAddress;
	String publicCashPrice;
	String publicCreditPrice;
	Float price;
	Float value;
	Float clientInitialPrice;
	Currency currency;
	Date addedDate;
	int placedBillboards;
	Float area;
	Float excess;
	Client owner;
	Address address;
	PropertyType propertyType;
	boolean isSoldByCompany;
	boolean isCanceled;
	boolean inWeb;
	static hasMany = [propertyUsages:PropertyUsage, buildings:Building, advertisements:Advertisement, concessions:Concession, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription, featuresByProperty:FeatureByProperty, propertyImages:PropertyImage, propertyUnits:PropertyUnit/*,realEstateAction,propertyDocument,insuredGood,tagSelectedValue,customFieldSelectedValue*/];
    static constraints = {
		title(blank:false, nullable:false, size:1..100, unique:true);
		description(blank:false, nullable:false, widget:'textArea', size:20..500);
		measures(blank:false, nullable:false, size:1..40);
		publicAddress(blank:false, nullable:false, size:1..100);
		publicCashPrice(blank:false, nullable:false, size:1..25);
		publicCreditPrice(blank:true, nullable:true, size:0..50);
		price(blank:false, nullable:false);
		value(blank:false, nullable:false);
		clientInitialPrice(blank:false, nullable:false);
		currency(blank:false, nullable:false);
		addedDate(nullable:false);
		placedBillboards(nullable:false);
		area(nullable:false);
		excess(nullable:false);
		owner(nullable:false);
		address(nullable:false);
		propertyType(nullable:false);
		isSoldByCompany(nullable:false);
		inWeb(nullable:false);
    }
}
