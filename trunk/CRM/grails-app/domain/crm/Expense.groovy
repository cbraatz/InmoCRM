package crm

import org.hibernate.collection.internal.PersistentSet

class Expense {
	Date date;
	String description;
	Double amount;
	Currency currency;
	Vendor vendor;
	Commission commission;
	ExpenseType expenseType;
	PaymentPlan paymentPlan;
	boolean isCredit;
	boolean isPaid;
	static hasMany = [expensePayments:ExpensePayment/*,GoodsPurchaseDetail*/];
    static constraints = {
		date(blank:false, nullable:false);
		description(blank:true, nullable:true, widget:'textArea', size:0..200);
		amount(blank:false, nullable:false);
		currency(nullable:false);
		vendor(nullable:true);
		commission(nullable:true);
		expenseType(nullable:false);
		paymentPlan(nullable:true);
		isCredit(nullable:false);
		isPaid(nullable:false);
    }
	
	public boolean areAllExpensePaymentsPaid(){
		PersistentSet list=this.expensePayments;
		list.each{
			if(!it.isPaid){
				return false
			}
		}
		return true;
	}
}
