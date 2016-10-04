package crm

import java.text.SimpleDateFormat

class CurrencyExchange extends CrmDomain{
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
	@Override
	public static String getPluralName(){
		return "currencyExchanges";
	}
	public CurrencyExchange(){	}
	public CurrencyExchange(Date date, Double buy, Double sell,	Currency sourceCurrency, Currency targetCurrency){
		this.date=date;
		this.buy=buy;
		this.sell=sell;
		this.sourceCurrency=sourceCurrency;
		this.targetCurrency=targetCurrency;
	}
	
	public static CurrencyExchange getCurrencyExchangeRate(Date date, Currency sourceCurrency, Currency targetCurrency){
		if(sourceCurrency.id == targetCurrency.id){
			System.out.println("SourceCurrency and TargetCurrency are equals. Returning CurrencyExchange with value '1'.");
			return new CurrencyExchange(date, new Double(1), new Double(1),	sourceCurrency, targetCurrency);
		}
		if(sourceCurrency.isDefault){
			List<CurrencyExchange> list=CurrencyExchange.executeQuery("from CurrencyExchange as c where c.date <= :d and c.sourceCurrency = :sc and targetCurrency = :tc order by c.date desc", [d:date, sc:sourceCurrency, tc:targetCurrency]);
			
			if(list.size()>0){
				return list.get(0);
			}else{
				System.err.println("CurrencyExchange list size = "+list.size());
				return null;
			}
		}else{
			System.err.println("SourceCurrency is not default currency and it should be.");
			return null;
		}
	}
}
