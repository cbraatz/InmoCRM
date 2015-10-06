package crm

class PaymentMethod {
	String name;
	Float discountPercentage;
	Boolean isCash;
	Boolean hasStartDate;
	Boolean hasEndDate;
	Boolean hasBank;
	
	static hasMany=[moneyTransactions:MoneyTransaction, paymentsIn:Payment, paymentsOut:Payment, moneyTransactionTotals:MoneyTransactionTotal, paymentDocuments:PaymentDocument];
	static mappedBy = [paymentsIn:"inPaymentMethod", paymentsOut:"outPaymentMethod"];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		discountPercentage(blank:false, nullable:false);
		isCash(nullable:false);
		hasStartDate(nullable:false);
		hasEndDate(nullable:false);
		hasBank(nullable:false);
	}
	public static PaymentMethod getDefaultCashPaymentMethod(){
		List<PaymentMethod> list=Currency.executeQuery("from PaymentMethod where isCash = :d",[d:true]);
		if(list.size()==1){
			return list.get(0);
		}else{
			System.err.println("PaymentMethod list size = "+list.size());
			return null;
		}
	}
}
