package crm

class PaymentSet {
	private Payment payment=new Payment();
	private Payment change=new Payment();
	
	public PaymentSet(Payment payment, Payment change){
		this.payment=payment;
		this.change=change;
	}
	public PaymentSet(){
	}
	public Payment getPayment(){
		return this.payment;
	}
	public void setPayment(Payment payment){
		this.payment=payment;
	}
	public Payment getChange(){
		return this.change;
	}
	public void setChange(Payment change){
		this.change=payment;
	}
}
