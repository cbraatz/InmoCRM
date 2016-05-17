package crm

class Locale extends CrmDomain{
	String name;
	String symbol;
	Language language;
	Country country
	Boolean isDefault;
	static hasMany = [domains:Domain, keyWords:KeyWord, advertisements:Advertisement, agentComments:AgentComment];

	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		symbol(blank:false, nullable:false, unique:true, size:1..10);
		language(blank:false, nullable:false);
		country(blank:false, nullable:false);
		isDefault(nullable:false);
	}
	public static Locale getDefaultLocale(){
		List<Locale> list=Locale.executeQuery("from Locale where isDefault = :d",[d:true]);
		if(list.size()==1){
			return list.get(0);
		}else{
			System.err.println("Locale list size = "+list.size());
			return null;
		}
	}
}
