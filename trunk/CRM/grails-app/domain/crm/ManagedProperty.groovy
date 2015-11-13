package crm

class ManagedProperty {
	String title;
	String propertyDescription;
	String measures;
	String publicAddress;
	String publicCashPrice;
	String publicCreditPrice;
	Double price;
	Double value;
	Double clientInitialPrice;
	Integer valueDegree;
	Currency currency;
	Date addedDate;
	Integer placedBillboards;
	Float area;
	Float excess;
	Client owner;
	Address address;
	PropertyType propertyType;
	Boolean inWeb;
	static hasMany = [propertyUsages:PropertyUsage, buildings:Building, advertisements:Advertisement, concessions:Concession, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription, featuresByProperty:FeatureByProperty, uploadedImages:UploadedImage, propertyUnits:PropertyUnit/*,realEstateAction,propertyDocument,insuredGood,tagSelectedValue,customFieldSelectedValue*/];
    static constraints = {
		title(blank:false, nullable:false, size:1..100, unique:true);
		propertyDescription(blank:false, nullable:false, widget:'textArea', size:20..500);
		measures(blank:false, nullable:false, size:1..40);
		publicAddress(blank:false, nullable:false, size:1..100);
		publicCashPrice(blank:false, nullable:false, size:1..25);
		publicCreditPrice(blank:true, nullable:true, size:0..50);
		price(blank:false, nullable:false, min:0D);
		value(blank:false, nullable:false, min:0D);
		clientInitialPrice(blank:false, nullable:false, min:0D);
		valueDegree(blank:false, nullable:false, min:0, max:5);
		currency(blank:false, nullable:false);
		addedDate(nullable:false);
		placedBillboards(nullable:false, min:0);
		area(nullable:false, min:0F);
		excess(nullable:false, min:0F);
		owner(nullable:false);
		address(nullable:false);
		propertyType(nullable:false);
		inWeb(nullable:false);
    }
}