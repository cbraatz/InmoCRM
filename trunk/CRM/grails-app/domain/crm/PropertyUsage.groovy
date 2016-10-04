package crm

class PropertyUsage extends CrmDomain{
	Usage usage;
	Float quantity;
	boolean isQuantityInPercentage;
	boolean isCurrentUsage;
	String description;
    static constraints = {
		usage(nullable: false);
		quantity(blank:true, nullable: true);
		isQuantityInPercentage(nullable: true);
		isCurrentUsage(nullable: false);
		description(blank: true, nullable: true, widget:'textArea', size:0..100);
    }
	@Override
	public static String getPluralName(){
		return "propertyUsages";
	}
}
