package crm

class Payment extends CrmDomain{
	Date date;
	Double amount;//amount currency = incomePayment or ExpensePayment Currency
	Double inAmount;
	Double outAmount;
	PaymentMethod inPaymentMethod;
	PaymentMethod outPaymentMethod;
	PaymentDocument inPaymentDocument;
	PaymentDocument outPaymentDocument;
	IncomePayment incomePayment;
	ExpensePayment expensePayment;
	Currency inCurrency;
	Currency outCurrency;
	//ThirdPartyPayment thirdPartyPayment;
	static hasMany=[moneyTransactions:MoneyTransaction];
    static constraints = {
		date(nullable:false);
		amount(blank:false, nullable:false, scale:2);
		inAmount(blank:false, nullable:false, scale:2);
		outAmount(blank:false, nullable:false, scale:2);
		inPaymentMethod(nullable:false);
		outPaymentMethod(nullable:false);
		inPaymentDocument(nullable:true);
		outPaymentDocument(nullable:true);
		incomePayment(nullable:true);
		expensePayment(nullable:true);
		inCurrency(nullable:false);
		outCurrency(nullable:false);
		//thirdPartyPayment(nullable:true);
    }
	public Payment(){
		this.inPaymentDocument=new PaymentDocument();//needs to be initialized for Payment form
		this.outAmount=new Double(0);
	}
	public Payment(Date date, Double amount, Double inAmount, Double outAmount, Currency inCurrency, Currency outCurrency, PaymentMethod inPaymentMethod, PaymentMethod outPaymentMethod, PaymentDocument inPaymentDocument, PaymentDocument outPaymentDocument, IncomePayment incomePayment, ExpensePayment expensePayment){
		this.date=date;
		this.amount=amount;
		this.inAmount=inAmount;
		this.outAmount=outAmount;
		this.inCurrency=inCurrency;
		this.outCurrency=outCurrency;
		this.inPaymentMethod=inPaymentMethod;
		this.outPaymentMethod=outPaymentMethod;
		this.inPaymentDocument=inPaymentDocument;
		this.outPaymentDocument=outPaymentDocument;
		this.incomePayment=incomePayment;
		this.expensePayment=expensePayment;
	}
	@Override
	public static String getPluralName(){
		return "payments";
	}
}
