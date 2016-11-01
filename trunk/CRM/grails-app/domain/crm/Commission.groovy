package crm

class Commission extends CrmDomain{
	Float percentage;
	String description;
	Double fixedAmount;
	Double taxTotal;
	Currency currency;
	CrmUser crmUser;
	Partner partner;
	CommissionRate commissionRate;
	static hasMany = [expenses:Expense];
    static constraints = {
		percentage(blank: true, nullable:true, min:0F, max:100F, scale:2);
		description(blank: true, nullable:true, widget:'textArea', size:0..200);
		fixedAmount(blank: true, nullable:true);
		currency(nullable:false);
		crmUser(nullable:false);
		taxTotal(blank: false, nullable:false);
		partner(nullable:false);
		commissionRate(nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "commissions";
	}
}
