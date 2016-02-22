package crm

class PropertyType {
	String name;
	DimensionMeasuringUnit dimensionMeasuringUnit;
	String description;
	String keywords;
	static hasMany = [propertyFeatures:PropertyFeature, propertyDemands:PropertyDemand, managedProperties:ManagedProperty];
	static constraints = {
		name(blank: false, nullable:false, unique:true, size:1..50);
		dimensionMeasuringUnit(nullable:false);
		description(blank:false, nullable:false, widget:'textArea', size:1..100);
		keywords(blank:false, nullable:false, widget:'textArea', size:1..500);
	}
	
}
