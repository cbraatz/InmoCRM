package crm

class PropertyTypeByLanguage extends CrmDomain{
	String name;
	String plural;
	Language language;
	PropertyType propertyType;
	
	static belongsTo = PropertyType;
	static constraints = {
		name(blank:false, nullable:false, size:1..50);
		plural(blank: false, nullable:false, size:1..50);
		language(blank: false, nullable:false);
		propertyType(nullable:false);
	}
	
	public static PropertyTypeByLanguage getPropertyTypeByLanguage(PropertyType propertyType, Language language){
		List<PropertyTypeByLanguage> list=PropertyTypeByLanguage.executeQuery("from PropertyTypeByLanguage where propertyType = :t and language = :l",[t:propertyType, l:language]);
		if(list.size()==1){
			return list.get(0);
		}else{
			System.err.println("PropertyTypeByLanguage list size = "+list.size());
			return null;
		}
	}
	@Override
	public static String getPluralName(){
		return "propertyTypesByLanguage";
	}
}