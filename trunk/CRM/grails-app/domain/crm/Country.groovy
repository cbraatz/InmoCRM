package crm

class Country extends CrmDomain{
	String name;
	static hasMany = [departments: Department, clients:Client, currencies:Currency, broadcastMedias:BroadcastMedia, locales:Locale, partners:Partner];
	
    static constraints = {
		name(blank: false, nullable:false, unique: true, size:1..50);
    }
	@Override
	public static String getPluralName(){
		return "countries";
	}
}