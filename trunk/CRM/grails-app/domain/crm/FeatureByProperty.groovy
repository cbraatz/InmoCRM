package crm

import java.util.List;

class FeatureByProperty extends CrmDomain{
	Float value;
	String description;
	ManagedProperty managedProperty;
	PropertyFeature propertyFeature;
    static constraints = {
		value(blank:false, nullable:true);
		description(blank:true, nullable:true, widget:'textArea', size:0..50);
		managedProperty(blank:false, nullable:false);
		propertyFeature(blank:false, nullable:false);
    }
	
	public static List<FeatureByProperty> getStoredFeatureByPropertyListForEachPropertyFeature(ManagedProperty managedProperty){
		List<PropertyFeature> listAll=PropertyFeature.getAll();
		List<FeatureByProperty> list = new ArrayList<FeatureByProperty>();
		def featureByPropertyList=FeatureByProperty.findAllByManagedProperty(managedProperty);
		boolean exists=false;
		featureByPropertyList.each{
			list.add(it);
		}
		for(PropertyFeature f:listAll){
			exists=false;
			featureByPropertyList.each{
				if(it.propertyFeature.id.equals(f.id)){
					 exists=true;
				}
			}
			if(exists == false){
				list.add(new FeatureByProperty(propertyFeature: f, value: new Float(0)));
			}
		}
		return list;
	}
	@Override
	public static String getPluralName(){
		return "featuresByProperty";
	}
}
