package crm

class Language {
	String name;
	Boolean isDefault;
	static hasMany = [advertisements:Advertisement];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		isDefault(nullable:false);
	}
}
