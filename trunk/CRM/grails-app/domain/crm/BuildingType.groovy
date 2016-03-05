package crm

class BuildingType {
	String name;
	DimensionMeasuringUnit dimensionMeasuringUnit;
	String description;
	static hasMany = [buildingFeatures:BuildingFeature, propertyDemands:PropertyDemand, buildings:Building];
	static constraints = {
		name(blank: false, nullable:false, unique:true, size:1..50);
		dimensionMeasuringUnit(nullable:false);
		description(blank:false, nullable:false, widget:'textArea', size:1..100);
	}
}
