package crm

class BuildingFeatureByLanguage extends CrmDomain{
	String name;
	String plural;
	Language language;
	BuildingFeature buildingFeature;
	
	static belongsTo = BuildingFeature;
    static constraints = {
		name(blank:false, nullable:false, size:1..50);
		plural(blank: false, nullable:false, size:1..50);
		language(blank: false, nullable:false);
		buildingFeature(nullable:false);
    }
	
	public static BuildingFeatureByLanguage getBuildingFeatureByLanguageByBuildingFeatureAndLanguage(BuildingFeature buildingFeature, Language language){
		List<BuildingFeatureByLanguage> list=BuildingFeatureByLanguage.executeQuery("from BuildingFeatureByLanguage where buildingFeature = :pf and language = :l",[pf:buildingFeature, l:language]);
		if(list.size()==1){
			return list.get(0);
		}else{
			System.err.println("BuildingFeatureByLanguage list size = "+list.size());
			return null;
		}
	}
	@Override
	public static String getPluralName(){
		return "buildingFeaturesByLanguage";
	}
}
