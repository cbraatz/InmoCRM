package crm

class BankAccount extends CrmDomain{
	Bank bank;
	String accountNumber;
	Currency currency;
	Partner partner;
	Boolean isSavingsAccount;
	static hasMany = [moneyTransactions:MoneyTransaction];
    static constraints = {
		bank(blank: false, nullable:false);
		accountNumber(blank: false, nullable:false, unique: 'bank', size:1..40);
		currency(nullable:false);
		partner(nullable:true);
		isSavingsAccount(nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "bankAccounts";
	}
}
