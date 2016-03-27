package crm

class PropertyFeatureByPropertyDemand {
	Float minValue;
	Float maxValue;
	boolean isRequired;
	PropertyDemand propertyDemand;
	PropertyFeature propertyFeature;
    static constraints = {
		minValue(blank:true, nullable:true);
		maxValue(blank:true, nullable:true);
		isRequired(nullable:false);
		propertyDemand(nullable:false);
		propertyFeature(nullable:false);
    }
}
