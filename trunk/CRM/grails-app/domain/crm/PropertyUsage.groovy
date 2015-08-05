package crm

class PropertyUsage {
	Usage usage;
	ManagedProperty managedProperty;
	Float quantity;
	boolean isQuantityInPercentage;
	boolean isCurrentUsage;
	String description;
    static constraints = {
		usage(nullable: false);
		managedProperty(nullable: false);
		quantity(blank:true, nullable: true);
		isQuantityInPercentage(nullable: true);
		isCurrentUsage(nullable: false);
		description(blank: true, nullable: true, widget:'textArea', size:0..100);
    }
}
