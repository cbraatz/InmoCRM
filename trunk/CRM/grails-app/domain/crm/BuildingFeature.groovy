package crm

import java.util.List;

class BuildingFeature extends CrmDomain{
	String name;
	String plural;
	String description;
	String defaultWebIcon;
	Boolean hasValue;
	static belongsTo = BuildingType;
	static hasMany = [featuresByBuilding:FeatureByBuilding, buildingTypes:BuildingType, buildingFeaturesByPropertyDemand:BuildingFeatureByPropertyDemand, buildingFeaturesByLanguage: BuildingFeatureByLanguage];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		plural(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:true, nullable:true, widget:'textArea', size:0..100);
		defaultWebIcon(blank:true, nullable:true, widget:'textArea', size:0..255);
		hasValue(nullable:false);
    }
	
	public static List<FeatureByBuilding> getEmptyFeatureByBuildingListForEachBuildingFeature(){
		List<FeatureByBuilding> list = new ArrayList<FeatureByBuilding>();
		BuildingFeature.getAll().each{
			list.add(new FeatureByBuilding(buildingFeature: it, value: new Float(0)));
		}
		return list;
	}
	@Override
	public static String getPluralName(){
		return "buildingFeatures";
	}
}
