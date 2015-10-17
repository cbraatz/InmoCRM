package crm
import org.hibernate.collection.internal.PersistentSet

class IncomePayment {
	String internalId;
	Date dueDate;
	Double amount;
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
	
	public Double getPayedTotalAmount(){
		PersistentSet list=this.payments;
		double amount=0;
		list.each{
			amount=amount+it.amount;
		}
		return amount;
	}
	public Double getInvoicedTotalAmount(){
		PersistentSet list=this.issuedInvoices;
		double amount=0;
		list.each{
			if(!it.isCanceled){
				amount=amount+it.amountInIncomeCurrency;
			}
		}
		return amount;
	}
}
