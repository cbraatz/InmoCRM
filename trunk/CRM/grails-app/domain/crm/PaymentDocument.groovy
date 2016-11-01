package crm

import java.util.Date;

class PaymentDocument extends CrmDomain{
	PaymentMethod paymentMethod;
	String internalID;
	Date startDate;
	Date endDate;
	Bank bank;
	static hasMany=[paymentsInPaymentDocument:Payment, paymentsOutPaymentDocument:Payment];
	static mappedBy = [paymentsInPaymentDocument:"inPaymentDocument", paymentsOutPaymentDocument:"outPaymentDocument"];
    static constraints = {
		paymentMethod(nullable:false);
		internalID(blank:true, nullable:true, size:1..40);//should be nullable=false but it is validated after submit
		startDate(blank:true, nullable:true);
		endDate(blank:true, nullable:true);
		bank(nullable:true);
    }
	@Override
	public static String getPluralName(){
		return "paymentDocuments";
	}
}
