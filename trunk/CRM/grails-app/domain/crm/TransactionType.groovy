package crm

class TransactionType {
	String name;
	String internalID;
	boolean isDefault;
	boolean isInternalTransaction;
	static hasMany = [moneyTransactions:MoneyTransaction];
    static constraints = {
		name(blank:false, nullable:false, size:1..40);
		internalID(blank:false, nullable:false, unique:true, size:1..40);
		isDefault(nullable:false);
		isInternalTransaction(nullable:false);
    }
}
