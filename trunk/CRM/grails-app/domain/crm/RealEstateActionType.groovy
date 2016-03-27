package crm

class RealEstateActionType {
	String name;
	String description;
	Boolean hasCost;
	Boolean clientPays;
	
	static hasMany=[realEstateActions:RealEstateAction];
    static constraints = {
		name(blank: false, nullable:false, size:1..50);
		description(blank: true, nullable:true, size:0..100);
		hasCost(blank: false, nullable:false);
		clientPays(blank: false, nullable:false);
    }
}
