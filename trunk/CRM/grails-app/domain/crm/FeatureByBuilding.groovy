package crm

import java.util.List;

class FeatureByBuilding extends CrmDomain{
	Float value;
	String description;
	Building building;
	BuildingFeature buildingFeature;
	
	static constraints = {
		value(blank:false, nullable:true);
		description (blank:true, nullable:true, widget:'textArea', size:0..50);
		building(blank:false, nullable:false);
		buildingFeature(blank:false, nullable:false);
	}
	
	public static List<FeatureByBuilding> getStoredFeatureByBuildingListForEachBuildingFeature(Building building){
		List<BuildingFeature> listAll=BuildingFeature.getAll();
		List<FeatureByBuilding> list = new ArrayList<FeatureByBuilding>();
		def featureByBuildingList=FeatureByBuilding.findAllByBuilding(building);
		boolean exists=false;
		featureByBuildingList.each{
			list.add(it);
		}
		for(BuildingFeature f:listAll){
			exists=false;
			featureByBuildingList.each{
				if(it.buildingFeature.id.equals(f.id)){
					 exists=true;
				}
			}
			if(exists == false){
				list.add(new FeatureByBuilding(buildingFeature: f, value: new Float(0)));
			}
		}
		return list;
	}
	@Override
	public static String getPluralName(){
		return "featuresByBuilding";
	}
}
