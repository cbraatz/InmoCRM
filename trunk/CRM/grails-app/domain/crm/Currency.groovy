package crm

class Currency extends CrmDomain{
	String name;
	String plural;
	String symbol;
	Boolean hasDecimals;
	Boolean isDefault;
	Boolean isInvoicingCurrency;
	Country country;
	static hasMany = [propertyDemands:PropertyDemand, concessions:Concession, commissions:Commission, moneyTransactions:MoneyTransaction, actions:Action,
					 incomes:Income, expenses:Expense, issuedInvoices:IssuedInvoice, incomingInvoices:IncomingInvoice, incomePayments:IncomePayment, 
					 expensePayments:ExpensePayment, currencyExchagesSourceCurrency:CurrencyExchange, currencyExchagesTargetCurrency:CurrencyExchange, managedProperties:ManagedProperty,
					 bankAccounts:BankAccount, paymentsInCurrency:Payment, paymentsOutCurrency:Payment, moneyTransactionTotals:MoneyTransactionTotal, defaultDateRanges:DefaultDateRange/*InsuranceDemand,ThirdPartyIncome,ThirdPartyPayment,InsuranceProposal,Policy*/];
	static mappedBy = [currencyExchagesSourceCurrency: "sourceCurrency", currencyExchagesTargetCurrency: "targetCurrency",
					   paymentsInCurrency:"inCurrency", paymentsOutCurrency:"outCurrency"];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		plural(blank:false, nullable:false, unique:true, size:1..50);
		symbol(blank:false, nullable:false, unique:true, size:1..10);
		hasDecimals(nullable:false);
		isDefault(nullable:false);
		isInvoicingCurrency(nullable:false);
		country(nullable:true);
    }
	public static Currency getDefaultCurrency(){
		List<Currency> list=Currency.executeQuery("from Currency where isDefault = :d",[d:true]);	
		if(list.size()==1){
			return list.get(0);
		}else{
			System.err.println("Currency list size = "+list.size());
			return null;
		}
	}
	@Override
	public static String getPluralName(){
		return "currencies";
	}
}
