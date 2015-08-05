package crm

class Building {
	Float builtSize;
	int builtYear;
	ManagedProperty managedProperty;
	BuildingType buildingType;
	BuildingCondition buildingCondition;
	String description;
	static hasMany = [featuresByBuilding:FeatureByBuilding/*InsuredGood*/];
    static constraints = {
		builtSize(blank:true, nullable:true);
		builtYear(blank:true, nullable:true);
		managedProperty(nullable:false);
		buildingType(nullable:false);
		buildingCondition(nullable:false);
		description(blank:false, nullable:false, widget:'textArea', size:1..200);
    }
}
