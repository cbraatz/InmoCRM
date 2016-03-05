package crm

class Country {
	String name;
	static hasMany = [departments: Department, clients:Client, currencies:Currency, broadcastMedias:BroadcastMedia, locales:Locale];
	
    static constraints = {
		name(blank: false, nullable:false, unique: true, size:1..50);
    }
}