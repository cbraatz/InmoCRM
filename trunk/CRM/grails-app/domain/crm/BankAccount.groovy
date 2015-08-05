package crm

class BankAccount {
	Bank bank;
	String accountNumber;
	Currency currency;
	Partner partner;
	boolean isSavingsAccount;
	static hasMany = [moneyTransactions:MoneyTransaction];
    static constraints = {
		bank(blank: false, nullable:false);
		accountNumber(blank: false, nullable:false, unique: 'bank', size:1..40);
		currency(nullable:false);
		partner(nullable:true);
		isSavingsAccount(nullable:false);
    }
}
