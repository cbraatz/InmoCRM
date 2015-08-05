package crm

class IncomePayment {
	String internalId;
	Date dueDate;
	Date paymentDate;
	Float amount;
	Float payedAmount;
	Currency currency;
	Income income;
	PaymentMethod paymentMethod;
	boolean isCanceled;
	static hasMany = [moneyTransactions:MoneyTransaction, issuedInvoices:IssuedInvoice];
    static constraints = {
		internalId(blank:false, nullable:false, unique:true, size:1..40);
		dueDate(blank:false, nullable:false);
		paymentDate(blank:false, nullable:false);
		amount(blank:false, nullable:false);
		payedAmount(blank:false, nullable:false);
		currency(nullable:false);
		income(nullable:false);
		paymentMethod(nullable:false);
		isCanceled(nullable:false);
    }
}
