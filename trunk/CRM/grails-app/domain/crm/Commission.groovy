package crm

class Commission {
	Float percentage;
	String description;
	Float fixedAmount;
	Float taxTotal;
	Currency currency;
	CrmUser owner;
	Partner partner;
	CommissionRate commissionRate;
	static hasMany = [expenses:Expense];
    static constraints = {
		description(blank: true, nullable:true, widget:'textArea', size:0..200);
		percentage(blank: true, nullable:true);
		fixedAmount(blank: true, nullable:true);
		currency(nullable:false);
		owner(nullable:false);
		taxTotal(blank: false, nullable:false);
		partner(nullable:false);
		commissionRate(nullable:false);
    }
}
