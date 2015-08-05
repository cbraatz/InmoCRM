package crm

class FeatureByBuilding {
	Float value;
	String description;
	Building building;
	BuildingFeature buildingFeature;
	static constraints = {
		value(blank:false, nullable:true);
		description (blank:true, nullable:true, widget:'textArea', size:0..50);
		building(blank:false, nullable:false);
		buildingFeature(blank:false, nullable:false, unique: 'building');
	}
}
