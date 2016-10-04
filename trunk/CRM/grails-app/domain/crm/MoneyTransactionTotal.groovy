package crm

class MoneyTransactionTotal extends CrmDomain{
	Date date;
	Double amount;
	Currency currency;
	PaymentMethod paymentMethod;
	static hasMany = [moneyTransactions:MoneyTransaction/*,usersByCheckout*/];
    static constraints = {
		date(blank:false, nullable:false);
		amount(blank:false, nullable:false);
		currency(nullable:false);
		paymentMethod(nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "moneyTransactionTotals";
	}
}
