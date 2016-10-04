package crm

class PartnerRole extends CrmDomain{
	String name;
	boolean isEmployee;
	String description;
	static hasMany = [partners:Partner, commissionRates:CommissionRate/*,ContextPermissionByPartnerRole*/];
    static constraints = {
		name(blank: false, nullable:false, unique:true, size:1..50);
		isEmployee(nullable:false);
		description(blank: false, nullable:false, widget:'textArea', size:1..200);
    }
	@Override
	public static String getPluralName(){
		return "partnerRoles";
	}
}
