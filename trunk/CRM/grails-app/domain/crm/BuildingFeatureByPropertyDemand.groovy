package crm

import java.util.List

class BuildingFeatureByPropertyDemand extends CrmDomain{//no implementado aun, sera necesario al agregar building feature a propertyDemand
	Float value;//for sale demands
	Float minValue;//for buy demands
	Float maxValue;//for buy demands
	Boolean isRequired;
	PropertyDemand propertyDemand;
	BuildingFeature buildingFeature;
	static constraints = {
		value(blank:true, nullable:true);
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
	/*public static List<BuildingFeatureByPropertyDemand> getStoredBuildingFeatureByPropertyDemandList(PropertyDemand propertyDemand){ 
		List<BuildingFeature> listAll=BuildingFeature.getAll();
		List<BuildingFeatureByPropertyDemand> list = new ArrayList<BuildingFeatureByPropertyDemand>();
		def featureByPropertyDemandList=BuildingFeatureByPropertyDemand.findAllByPropertyDemand(propertyDemand);
		boolean exists=false;
		featureByPropertyDemandList.each{
			list.add(it);
		}
		for(BuildingFeature f:listAll){
			exists=false;
			featureByPropertyDemandList.each{
				if(it.buildingFeature.id.equals(f.id)){
					 exists=true;
				}
			}
			if(exists == false){
				list.add(new BuildingFeatureByPropertyDemand(buildingFeature: f, value: new Float(0), minValue: new Float(0), maxValue: new Float(0)));
			}
		}
		return list;
	}
	public static List<BuildingFeatureByPropertyDemand> getEmptyPropertyDemandList(){
		List<BuildingFeature> listAll=BuildingFeature.getAll();
		List<BuildingFeatureByPropertyDemand> list = new ArrayList<BuildingFeatureByPropertyDemand>();
		for(BuildingFeature f:listAll){
			list.add(new BuildingFeatureByPropertyDemand(buildingFeature: f, value: new Float(0), minValue: new Float(0), maxValue: new Float(0)));
		}
		return list;
	}*/
}
