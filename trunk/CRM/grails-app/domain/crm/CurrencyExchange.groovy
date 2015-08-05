package crm

class CurrencyExchange {
	Date date;
	Float buy;
	Float sell;
	Currency sourceCurrency;
	Currency targetCurrency;
    static constraints = {
		date(blank: false, nullable:false, unique: ['sourceCurrency', 'targetCurrency']);
		buy(blank: false, nullable:false);
		sell(blank: false, nullable:false);
		sourceCurrency(nullable:false);
		targetCurrency(nullable:false);
    }
}
