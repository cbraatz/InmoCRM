package crm

class BuildingFeature {
	String name;
	String description;
	boolean hasValue;
	static belongsTo = BuildingType;
	static hasMany = [featuresByBuilding:FeatureByBuilding, buildingTypes:BuildingType, buildingFeaturesByPropertyDemand:BuildingFeatureByPropertyDemand];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..40);
		description (blank:true, nullable:true, widget:'textArea', size:0..100);
		hasValue(nullable:false);
    }
}
