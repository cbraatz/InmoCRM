package crm

import java.util.List

class PropertyFeatureByPropertyDemand extends CrmDomain{
	Float value;//for sale demands
	Float minValue;//for buy demands
	Float maxValue;//for buy demands
	PropertyDemand propertyDemand;
	PropertyFeature propertyFeature;
    static constraints = {
		value(blank:true, nullable:true);
		minValue(blank:true, nullable:true);
		maxValue(blank:true, nullable:true);
		propertyDemand(nullable:false);
		propertyFeature(nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "propertyFeaturesByPropertyDemand";
	}
	public static List<PropertyFeatureByPropertyDemand> getStoredPropertyFeatureByPropertyDemandList(PropertyDemand propertyDemand){
		List<PropertyFeature> listAll=PropertyFeature.getAll();
		List<PropertyFeatureByPropertyDemand> list = new ArrayList<PropertyFeatureByPropertyDemand>();
		def featureByPropertyDemandList=PropertyFeatureByPropertyDemand.findAllByPropertyDemand(propertyDemand);
		boolean exists=false;
		featureByPropertyDemandList.each{
			list.add(it);
		}
		for(PropertyFeature f:listAll){
			exists=false;
			featureByPropertyDemandList.each{
				if(it.propertyFeature.id.equals(f.id)){
					 exists=true;
				}
			}
			if(exists == false){
				list.add(new PropertyFeatureByPropertyDemand(propertyFeature: f, value: new Float(0), minValue: new Float(0), maxValue: new Float(0)));
			}
		}
		return list;
	}
	public static List<PropertyFeatureByPropertyDemand> getEmptyPropertyDemandList(){
		List<PropertyFeature> listAll=PropertyFeature.getAll();
		List<PropertyFeatureByPropertyDemand> list = new ArrayList<PropertyFeatureByPropertyDemand>();
		for(PropertyFeature f:listAll){
			list.add(new PropertyFeatureByPropertyDemand(propertyFeature: f, value: new Float(0), minValue: new Float(0), maxValue: new Float(0)));
		}
		return list;
	}
}
