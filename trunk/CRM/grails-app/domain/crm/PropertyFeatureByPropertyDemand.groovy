package crm

class PropertyFeatureByPropertyDemand extends CrmDomain{
	Float minValue;
	Float maxValue;
	Boolean isRequired;
	PropertyDemand propertyDemand;
	PropertyFeature propertyFeature;
    static constraints = {
		minValue(blank:true, nullable:true);
		maxValue(blank:true, nullable:true);
		isRequired(nullable:false);
		propertyDemand(nullable:false);
		propertyFeature(nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "propertyFeaturesByPropertyDemand";
	}
}
