package crm

class TaxRate {
	String name
	Float percentage
	static hasMany = [incomeTypes:IncomeType, expenseTypes:ExpenseType, defaultsDateRanges:DefaultsDateRange/*, exchangeGood, insuranceProduct*/];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		percentage(blank:false, nullable:false, min:0F, max:100F, scale:2);
    }
}
