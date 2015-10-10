package crm

import java.text.SimpleDateFormat

class CurrencyExchange {
	Date date;
	Double buy;
	Double sell;
	Currency sourceCurrency;
	Currency targetCurrency;
    static constraints = {
		date(blank: false, nullable:false, unique: ['sourceCurrency', 'targetCurrency']);
		buy(blank: false, nullable:false);
		sell(blank: false, nullable:false);
		sourceCurrency(nullable:false);
		targetCurrency(nullable:false);
    }
	public static CurrencyExchange getCurrencyExchangeRate(Date date, Currency sourceCurrency, Currency targetCurrency){
		List<CurrencyExchange> list=CurrencyExchange.executeQuery("from CurrencyExchange as c where c.date <= :d and c.sourceCurrency = :sc and targetCurrency = :tc order by c.date desc", [d:date, sc:sourceCurrency, tc:targetCurrency]);
		
		if(list.size()>0){
			return list.get(0);
		}else{
			System.err.println("CurrencyExchange list size = "+list.size());
			return null;
		}
	}
}
