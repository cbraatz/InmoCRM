package crm

class MoneyTransaction {
	Float amount;
	String relatedInternalID;
	IncomePayment incomePayment;
	ExpensePayment expensePayment;
	Currency currency;
	PaymentMethod paymentMethod;
	TransactionType transactionType;
	BankAccount bankAccount;
	//MoneyTransactionTotal moneyTransactionTotal; nullable true
	//boolean isCashTotalAdjustment; nullable false
    static constraints = {
		amount(blank: false, nullable: false);
		relatedInternalID(blank: true, nullable: true, size:0..40);//to define related transactions, transactions without related transactions will be null
		incomePayment(nullable: true);
		expensePayment(nullable: true);
		currency(nullable: false);
		paymentMethod(nullable: false);
		transactionType(nullable: false);
		bankAccount(nullable: true);
    }
}