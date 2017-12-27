package crm

class Language extends CrmDomain{
	String name;
	String symbol;
	String prepositionOfPlace;
	static hasMany = [locales:Locale, operationTypes:OperationType, propertyFeaturesByLanguage:PropertyFeatureByLanguage, buildingFeaturesByLanguage:BuildingFeatureByLanguage, propertyTypesByLanguage:PropertyTypeByLanguage, buildingTypesByLanguage:BuildingTypeByLanguage];
	
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		symbol(blank:false, nullable:false, unique:true, size:1..10);
		prepositionOfPlace(blank:false, nullable:false, size:1..10);
	}
	@Override
	public static String getPluralName(){
		return "languages";
	}
	public static Language getDefaultLanguage(){
		return Locale.getDefaultLocale().language;
	}
}
