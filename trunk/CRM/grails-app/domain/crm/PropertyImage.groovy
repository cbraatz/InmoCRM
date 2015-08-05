package crm

class PropertyImage {
	String name;
	String url;
	String description;
	Float sizeInKB;
	ManagedProperty managedProperty;
    static constraints = {
		name(blank:true, nullable:true, size:0..30);
		url(blank:false, nullable:false, size:1..200);
		description(blank:true, nullable:true, widget:'textArea', size:0..100);
		sizeInKB(nullable:false);
		managedProperty(nullable:false);
    }
}
