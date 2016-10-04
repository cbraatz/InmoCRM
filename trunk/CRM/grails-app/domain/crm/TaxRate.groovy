package crm

class TaxRate extends CrmDomain{
	String name
	Float percentage
	static hasMany = [incomeTypes:IncomeType, expenseTypes:ExpenseType, defaultDateRanges:DefaultDateRange/*, exchangeGood, insuranceProduct*/];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		percentage(blank:false, nullable:false, min:0F, max:100F, scale:2);
    }
	@Override
	public static String getPluralName(){
		return "taxRates";
	}
}
