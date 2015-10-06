package crm

class Payment {
	Date date;
	Float amount;//amount currency = incomePayment or ExpensePayment Currency
	Float inAmount;
	Float outAmount;
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
		this.outAmount=new Float(0);
		this.outCurrency=new Currency();
		this.outPaymentMethod=new PaymentMethod();
	}
	public Payment(Date date, Float amount, Float inAmount, Float outAmount, Currency inCurrency, Currency outCurrency, PaymentMethod inPaymentMethod, PaymentMethod outPaymentMethod, PaymentDocument inPaymentDocument, PaymentDocument outPaymentDocument, IncomePayment incomePayment, ExpensePayment expensePayment){
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
}
