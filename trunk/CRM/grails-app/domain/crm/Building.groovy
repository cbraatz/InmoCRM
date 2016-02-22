package crm

class Building {
	Float builtSize;
	Integer builtYear;
	ManagedProperty managedProperty;
	BuildingType buildingType;
	BuildingCondition buildingCondition;
	String buildingDescription;
	static hasMany = [featuresByBuilding:FeatureByBuilding/*InsuredGood*/];
    static constraints = {
		builtSize(blank:true, nullable:true);
		builtYear(blank:true, nullable:true);
		managedProperty(nullable:false);
		buildingType(nullable:false);
		buildingCondition(nullable:false);
		buildingDescription(blank:true, nullable:true, widget:'textArea', size:0..1000);
    }
}
