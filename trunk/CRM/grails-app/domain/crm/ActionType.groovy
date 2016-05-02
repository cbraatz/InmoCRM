package crm

class ActionType {
	String name;
	String description;
	Boolean hasCost;
	Boolean clientPays;
	
	static hasMany=[actions:Action];
    static constraints = {
		name(blank: false, nullable:false, size:1..50);
		description(blank: true, nullable:true, size:0..100);
		hasCost(blank: false, nullable:false);
		clientPays(blank: false, nullable:false);
    }
}
