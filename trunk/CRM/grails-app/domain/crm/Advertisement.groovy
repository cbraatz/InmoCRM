package crm

class Advertisement {
	String summary;
	String content;
	BroadcastMedia broadcastMedia;
	boolean isContactProvenance;
	boolean byOwner;
	boolean byCompany;
	Date postDate;
	String urlToPost;
	PropertyDemand propertyDemand;
	ManagedProperty managedProperty;
    static constraints = {
		summary(blank: true, nullable:true, size:0..500);
		content(blank: true, nullable:true, size:0..100);
		broadcastMedia(nullable:false);
		isContactProvenance(nullable:false);
		byOwner(nullable:false);
		byCompany(nullable:false);
		postDate(blank: true, nullable:true);
		urlToPost(blank: true, nullable:true, size:0..200);
		propertyDemand(nullable:true);
		managedProperty(nullable:true);
    }
}
