package crm

import java.util.List;

class PropertyFeature extends CrmDomain{
	String name;
	String plural;
	String description;
	String defaultWebIcon;
	Boolean hasValue;
	static belongsTo = PropertyType;
	static hasMany = [featuresByProperty:FeatureByProperty, propertyTypes:PropertyType, propertyFeaturesByPropertyDemand:PropertyFeatureByPropertyDemand, propertyFeaturesByLanguage: PropertyFeatureByLanguage];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		plural(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:true, nullable:true, widget:'textArea', size:0..100);
		defaultWebIcon(blank:true, nullable:true, widget:'textArea', size:0..255);
		hasValue(nullable:false);
	}
	@Override
	public static String getPluralName(){
		return "propertyFeatures";
	}
	public static List<FeatureByProperty> getEmptyFeatureByPropertyListForEachPropertyFeature(){
		List<FeatureByProperty> list = new ArrayList<FeatureByProperty>();
		PropertyFeature.getAll().each{
			list.add(new FeatureByProperty(propertyFeature: it, value: new Float(0)));
		}
		return list;
	}
	public static List<FeatureByProperty> extractFeatureByPropertyListFromExistingPropertyDemand(PropertyDemand propertyDemand){
		def listPD=propertyDemand?.propertyFeaturesByPropertyDemand;
		List<FeatureByProperty> list = new ArrayList<FeatureByProperty>();
		List<PropertyFeature> listAll=PropertyFeature.getAll();
		boolean exists=false;
		for(PropertyFeatureByPropertyDemand f:listPD){
			list.add(new FeatureByProperty(propertyFeature: f.propertyFeature, value: (propertyDemand.isSellDemand()?f.value:null)));
		}
		for(PropertyFeature f:listAll){
			exists=false;
			listPD.each{
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
}
