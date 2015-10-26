package crm

import org.hibernate.collection.internal.PersistentSet


class Income {
	Date date;
	String description;
	Double amount;
	Boolean isPaid;
	Boolean isCredit;
	PaymentPlan paymentPlan;
	Currency currency;
	Client client;
	Concession concession;
	IncomeType incomeType;
	
	static hasMany = [incomePayments:IncomePayment/*,GoodsSaleDetail*/];
		
    static constraints = {
		date(blank:false, nullable:false);
		description(blank:true, nullable:true, widget:'textArea', size:0..200);
		amount(blank:false, nullable:false);
		isCredit(nullable:false);
		isPaid(nullable:false);
		paymentPlan(nullable:true);
		currency(nullable:false);
		client(nullable:true);
		concession(nullable:true);
		incomeType(nullable:true);
		
    }
	static mapping = {
		incomePayments sort: "dueDate"
	}
	public Income(){
		this.concession=new Concession();
	}
	public Income(def params){
		this.properties = params;
		this.concession=new Concession();
	}
	public boolean areAllIncomePaymentsPaid(){
		PersistentSet list=this.incomePayments;
		list.each{
			if(!it.isPaid){
				return false
			}
		}
		return true;
	}
	
	public boolean hasPayedPayments(){
		this.incomePayments.each{
			if(it.getPayedTotalAmount().doubleValue() > 0 || it.isPaid){
				return true;
			}
		}
		return false;
	}
	
	public boolean removeAllPayments(){
		this.incomePayments.each{
			it.delete();
		}
		this.incomePayments.clear();
		System.err.println("Iconme payments size = "+ this.incomePayments.size());
		
		if(this.incomePayments.size() > 0){
			return false;
		}else{
			return true;
		}
	}
}