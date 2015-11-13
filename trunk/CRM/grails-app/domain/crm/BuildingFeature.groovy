package crm

import java.util.List;

import org.apache.commons.collections.ListUtils

class BuildingFeature {
	String name;
	String description;
	static belongsTo = BuildingType;
	static hasMany = [featuresByBuilding:FeatureByBuilding, buildingTypes:BuildingType, buildingFeaturesByPropertyDemand:BuildingFeatureByPropertyDemand];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description (blank:true, nullable:true, widget:'textArea', size:0..100);
    }
	
	public static List<FeatureByBuilding> getEmptyFeatureByBuildingListForEachBuildingFeature(){
		List<FeatureByBuilding> list = new ArrayList<FeatureByBuilding>();
		BuildingFeature.getAll().each{
			list.add(new FeatureByBuilding(buildingFeature: it, value: new Float(0)));
		}
		return list;
	}
}
