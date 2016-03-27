package crm

class DefaultsDateRange {
	Date startDate;
	Date endDate;
	Currency currency;
	TaxRate taxRate;
    static constraints = {
		startDate(nullable:false);
		endDate(nullable:true);//end = null when is actual default
		currency(nullable:true);
		taxRate(nullable:true);
    }
}
