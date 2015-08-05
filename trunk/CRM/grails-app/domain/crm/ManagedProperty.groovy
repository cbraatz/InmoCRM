package crm

class ManagedProperty {
	String title;
	String description;
	String measures;
	String publicAddress;
	String propertyID1;
	String propertyID2;
	String propertyID3;
	Date addedDate;
	int placedBillboards;
	Float area;
	Float excess;
	Client owner;
	Address address;
	PropertyType propertyType;
	static hasMany = [propertyUsages:PropertyUsage, buildings:Building, advertisements:Advertisement, concessions:Concession, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription, featuresByProperty:FeatureByProperty, propertyImages:PropertyImage/*,realEstateAction,propertyDocument,insuredGood,tagSelectedValue,customFieldSelectedValue*/];
    static constraints = {
		title(blank:false, nullable:false, size:10..80);
		description(blank:false, nullable:false, widget:'textArea', size:20..500);
		measures(blank:false, nullable:false, size:1..40);
		publicAddress(blank:false, nullable:false, size:1..100);
		propertyID1(blank:false, nullable:false, size:1..20);
		propertyID2(blank:true, nullable:true, size:0..20);
		propertyID3(blank:true, nullable:true, size:0..20);
		addedDate(nullable:false);
		placedBillboards(nullable:false);
		area(nullable:false);
		excess(nullable:false);
		owner(nullable:false);
		address(nullable:false);
		propertyType(nullable:false);
    }
}
