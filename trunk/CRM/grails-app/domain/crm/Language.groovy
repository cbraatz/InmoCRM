package crm

class Language {
	String name;
	String symbol;
	String prepositionOfPlace;
	static hasMany = [locales:Locale, operationTypes:OperationType, propertyFeaturesByLanguage:PropertyFeatureByLanguage, buildingFeaturesByLanguage:BuildingFeatureByLanguage];

	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		symbol(blank:false, nullable:false, unique:true, size:1..10);
		prepositionOfPlace(blank:false, nullable:false, size:1..10);
	}
	public static Language getDefaultLanguage(){
		return Locale.getDefaultLocale().language;
	}
}
