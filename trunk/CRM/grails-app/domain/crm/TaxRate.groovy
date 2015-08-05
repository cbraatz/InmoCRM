package crm

class TaxRate {
	String name
	Float percentage
	static hasMany = [incomeTypes:IncomeType, expenseTypes:ExpenseType/*, exchangeGood, insuranceProduct*/];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..40);
		percentage(blank:false, nullable:false);
    }
}
