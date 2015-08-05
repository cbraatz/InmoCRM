package crm

import java.util.Date;

class ExpensePayment{
	String internalId;
	Date dueDate;
	Date paymentDate;
	Float amount;
	Float payedAmount;
	Currency currency;
	Expense expense;
	PaymentMethod paymentMethod;
	boolean isCanceled;
	static hasMany = [moneyTransactions:MoneyTransaction, incomingInvoices:IncomingInvoice];
	static constraints = {
		internalId(blank:false, nullable:false, unique:true, size:5..40);
		dueDate(blank:false, nullable:false);
		paymentDate(blank:false, nullable:false);
		amount(blank:false, nullable:false);
		payedAmount(blank:false, nullable:false);
		currency(nullable:false);
		expense(nullable:false);
		paymentMethod(nullable:false);
		isCanceled(nullable:false);
	}
}
