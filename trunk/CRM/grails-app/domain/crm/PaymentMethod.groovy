package crm

class PaymentMethod {
	String name;
	Float discountPercentage;
	boolean isDefault;
	boolean hasNumber;
	boolean hasStartDate;
	boolean hasEndDate;
	
	static hasMany=[moneyTransactions:MoneyTransaction, incomePayments:IncomePayment, expensePayments:ExpensePayment/*,ThirdPartyPayment,MoneyTransactionTotal*/];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..40);
		discountPercentage(blank:false, nullable:false);
		isDefault(nullable:false);
		hasNumber(nullable:false);
		hasStartDate(nullable:false);
		hasEndDate(nullable:false);
		
	}
}
