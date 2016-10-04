package crm

class DefaultDateRange extends CrmDomain{
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
	@Override
	public static String getPluralName(){
		return "defaultDateRanges";
	}
}
