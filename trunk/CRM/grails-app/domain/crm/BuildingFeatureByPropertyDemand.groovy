package crm

class BuildingFeatureByPropertyDemand extends CrmDomain{
	Float minValue;
	Float maxValue;
	boolean isRequired;
	PropertyDemand propertyDemand;
	BuildingFeature buildingFeature;
	static constraints = {
		minValue(blank:true, nullable:true);
		maxValue(blank:true, nullable:true);
		isRequired(nullable:false);
		propertyDemand(nullable:false);
		buildingFeature(nullable:false);
	}
	@Override
	public static String getPluralName(){
		return "buildingFeaturesByPropertyDemand";
	}
}
