package crm

class PropertyFeature {
	String name;
	String description;
	boolean hasValue;
	static belongsTo = PropertyType;
	static hasMany = [featuresByProperty:FeatureByProperty, propertyTypes:PropertyType, propertyFeaturesByPropertyDemand:PropertyFeatureByPropertyDemand];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:true, nullable:true, widget:'textArea', size:0..100);
		hasValue(nullable:false);
	}
}
