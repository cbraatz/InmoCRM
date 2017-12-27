package crm

class BuildingTypeByLanguage extends CrmDomain{
	String name;
	String plural;
	Language language;
	BuildingType buildingType;
	
	static belongsTo = BuildingType;
	static constraints = {
		name(blank:false, nullable:false, size:1..50);
		plural(blank: false, nullable:false, size:1..50);
		language(blank: false, nullable:false);
		buildingType(nullable:false);
	}
	
	public static BuildingTypeByLanguage getBuildingTypeByLanguage(BuildingType buildingType, Language language){
		List<BuildingTypeByLanguage> list=BuildingTypeByLanguage.executeQuery("from BuildingTypeByLanguage where buildingType = :t and language = :l",[t:buildingType, l:language]);
		if(list.size()==1){
			return list.get(0);
		}else{
			System.err.println("BuildingTypeByLanguage list size = "+list.size());
			return null;
		}
	}
	@Override
	public static String getPluralName(){
		return "buildingTypesByLanguage";
	}
}
