package crm

class CommissionRate extends CrmDomain{
	String name;
	Float percentage;
	CommissionType commissionType;
	PartnerRole partnerRole;
	static belongsTo = PartnerRole;
	static hasMany = [commissionsByProperty:CommissionByProperty];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		percentage(blank:false, nullable:false, min:0F, max:100F, scale:2);
		commissionType(nullable:false);
		partnerRole(nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "commissionRates";
	}
}
