package crm

class Currency {
	String name;
	String plural;
	String symbol;
	int decimals;
	boolean isDefault;
	Country country;
	static hasMany = [propertyDemands:PropertyDemand, concessions:Concession, commissions:Commission, moneyTransactions:MoneyTransaction,
					 incomes:Income, expenses:Expense, issuedInvoices:IssuedInvoice, issuedInvoicesDefault:IssuedInvoice, 
					 incomingInvoices:IncomingInvoice, incomingInvoicesDefault:IncomingInvoice, incomePayments:IncomePayment, 
					 expensePayments:ExpensePayment, currencyExchagesSource:CurrencyExchange, currencyExchagesTarget:CurrencyExchange,
					 bankAccounts:BankAccount/*InsuranceDemand,ThirdPartyIncome,ThirdPartyPayment,MoneyTransactionTotal,InsuranceProposal,Policy*/];
	static mappedBy = [issuedInvoices: "currency", issuedInvoicesDefault: "defaultCurrency",
					   incomingInvoices: "currency", incomingInvoicesDefault: "defaultCurrency",
					   currencyExchagesSource: "sourceCurrency", currencyExchagesTarget: "targetCurrency"];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		plural(blank:false, nullable:false, unique:true, size:1..50);
		symbol(blank:false, nullable:false, unique:true, size:1..10);
		decimals(blank:false, nullable:false);
		isDefault(nullable:false);
		country(nullable:true);
    }
}
