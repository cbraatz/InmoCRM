package crm

class BroadcastMedia extends CrmDomain{
	String name;
	String urlToSite;
	Integer adSummaryMaxLength;
	Integer adTextMaxLength;
	Country country;
	static hasMany = [advertisements:Advertisement, propertyDemands:PropertyDemand];
	static constraints = {
		name(blank: false, nullable:false, unique: true, size:1..50);
		urlToSite(blank: true, nullable:true, size:0..255);
		adSummaryMaxLength(blank: true, nullable:true);
		adTextMaxLength(blank: true, nullable:true);
		country(nullable:true);
	}
	@Override
	public static String getPluralName(){
		return "broadcastMedias";
	}
}
