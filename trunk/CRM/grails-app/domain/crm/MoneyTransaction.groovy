package crm

import java.util.Date;

class MoneyTransaction extends CrmDomain{
	Date date;
	Double amount;
	String internalID;
	Payment payment;
	Currency currency;
	PaymentMethod paymentMethod;
	TransactionType transactionType;
	BankAccount bankAccount;
	MoneyTransactionTotal moneyTransactionTotal;
	//boolean isCashTotalAdjustment; nullable false
	
    static constraints = {
		date(blank:false, nullable:false);
		amount(blank: false, nullable: false);
		internalID(blank: true, nullable: true, size:0..40);//to define related transactions, transactions without related transactions will be null
		payment(nullable: true);
		currency(nullable: false);
		paymentMethod(nullable: false);
		transactionType(nullable: false);
		bankAccount(nullable: true);
		moneyTransactionTotal(nullable: true);
		
    }
	public MoneyTransaction(){
		
	}
	
	public MoneyTransaction(Date date, Double amount, String internalID, Payment payment, Currency currency, PaymentMethod paymentMethod, TransactionType transactionType, BankAccount bankAccount, MoneyTransactionTotal moneyTransactionTotal){
		this.date=date;
		this.amount=amount;
		this.internalID=internalID;
		this.payment=payment;
		this.currency=currency;
		this.paymentMethod=paymentMethod;
		this.transactionType=transactionType;
		this.bankAccount=bankAccount;
		this.moneyTransactionTotal=moneyTransactionTotal;
	}
}