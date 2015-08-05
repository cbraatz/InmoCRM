package crm

class PartnerRole {
	String name;
	boolean isEmployee;
	String description;
	static hasMany = [partners:Partner, commissionRates:CommissionRate/*,ContextPermissionByPartnerRole*/];
    static constraints = {
		name(blank: false, nullable:false, unique:true, size:1..40);
		isEmployee(nullable:false);
		description(blank: false, nullable:false, widget:'textArea', size:1..200);
    }
}
