package crm

import org.hibernate.collection.internal.PersistentSet

class Expense extends CrmDomain{
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
	static mapping = {
		expensePayments sort: "dueDate"
	}
	@Override
	public static String getPluralName(){
		return "expenses";
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
	
	public boolean hasPayedPayments(){
		this.expensePayments.each{
			if(it.getPayedTotalAmount().doubleValue() > 0 || it.isPaid){
				return true;
			}
		}
		return false;
	}
	
	public boolean removeAllPayments(){
		this.expensePayments.each{
			it.delete();
		}
		this.expensePayments.clear();

		if(this.expensePayments.size() > 0){
			return false;
		}else{
			return true;
		}
	}
}
