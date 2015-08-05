package crm

class FeatureByProperty {
	Float value;
	String description;
	ManagedProperty managedProperty;
	PropertyFeature propertyFeature;
    static constraints = {
		value(blank:false, nullable:true);
		description(blank:true, nullable:true, widget:'textArea', size:0..50);
		managedProperty(blank:false, nullable:false);
		propertyFeature(blank:false, nullable:false, unique: 'managedProperty');
    }
}
