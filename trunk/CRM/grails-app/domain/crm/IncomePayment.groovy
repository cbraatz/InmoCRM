package crm
import org.hibernate.collection.internal.PersistentSet

class IncomePayment {
	String internalId;
	Date dueDate;
	Float amount;
	Currency currency;
	Income income;
	Boolean isCanceled;
	Boolean isPaid;
	static belongsTo = Income;
	static hasMany = [payments:Payment, issuedInvoices:IssuedInvoice];
    static constraints = {
		internalId(blank:false, nullable:false, unique:true, size:1..40);
		dueDate(blank:false, nullable:false);
		amount(blank:false, nullable:false);
		currency(nullable:false);
		income(nullable:false);
		isCanceled(nullable:false);
		isPaid(nullable:false);
    }
	
	public float getPayedTotalAmount(){
		PersistentSet list=this.payments;
		float amount=0;
		list.each{
			amount=amount+it.amount;
		}
		return amount;
	}
}
