package crm

import java.util.Date;
import org.hibernate.collection.internal.PersistentSet

class ExpensePayment{
	String internalId;
	Date dueDate;
	Double amount;
	Currency currency;
	Expense expense;
	Boolean isCanceled;
	Boolean isPaid;
	static belongsTo = Expense;
	static hasMany = [payments:Payment, incomingInvoices:IncomingInvoice];
	static constraints = {
		internalId(blank:false, nullable:false, unique:true, size:5..40);
		dueDate(blank:false, nullable:false);
		amount(blank:false, nullable:false);
		currency(nullable:false);
		expense(nullable:false);
		isCanceled(nullable:false);
		isPaid(nullable:false);
	}
	
	public Double getPayedTotalAmount(){
		PersistentSet list=this.payments;
		double amount=0;
		list.each{
			amount=amount+it.amount;
		}
		return amount;
	}
}
