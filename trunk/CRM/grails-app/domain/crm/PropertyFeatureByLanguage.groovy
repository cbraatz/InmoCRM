package crm

class PropertyFeatureByLanguage extends CrmDomain{
	String name;
	String plural;
	Language language;
	PropertyFeature propertyFeature;
	
	static belongsTo = PropertyFeature;
	static constraints = {
		name(blank:false, nullable:false, size:1..50);
		plural(blank: false, nullable:false, size:1..50);
		language(blank: false, nullable:false);
		propertyFeature(nullable:false);
	}
	
	public static PropertyFeatureByLanguage getPropertyFeatureByLanguageByPropertyFeatureAndLanguage(PropertyFeature propertyFeature, Language language){
		List<PropertyFeatureByLanguage> list=PropertyFeatureByLanguage.executeQuery("from PropertyFeatureByLanguage where propertyFeature = :pf and language = :l",[pf:propertyFeature, l:language]);
		if(list.size()==1){
			return list.get(0);
		}else{
			System.err.println("PropertyFeatureByLanguage list size = "+list.size());
			return null;
		}
	}
	@Override
	public static String getPluralName(){
		return "propertyFeaturesByLanguage";
	}
}
