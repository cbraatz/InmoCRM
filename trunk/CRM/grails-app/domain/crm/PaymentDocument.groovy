package crm

import java.util.Date;

class PaymentDocument {
	PaymentMethod paymentMethod;
	String internalId;
	Date startDate;
	Date endDate;
	Bank bank;
	static hasMany=[paymentsIn:Payment, paymentsOut:Payment];
	static mappedBy = [paymentsIn:"inPaymentDocument", paymentsOut:"outPaymentDocument"];
    static constraints = {
		paymentMethod(nullable:false);
		internalId(blank:true, nullable:true, size:1..40);//should be nullable=false but it is validated after submit
		startDate(blank:true, nullable:true);
		endDate(blank:true, nullable:true);
		bank(nullable:true);
    }
}
