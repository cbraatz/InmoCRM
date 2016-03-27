package crm

class InvoicesPrinting {
	Integer printingNumber;
	Date startDate;
	Date endDate;
	String firstNumber;
	String secondNumber;
	String thirdStartNumber;
	Integer quantity;

    static constraints = {
		printingNumber(blank:false, nullable:false, unique:true);
		startDate(blank:false, nullable:false);
		endDate(blank:false, nullable:false);
		firstNumber(blank:false, nullable:false, size:1..4);
		secondNumber(blank:false, nullable:false, size:1..4);
		thirdStartNumber(blank:false, nullable:false, size:1..10);
		quantity(blank:false, nullable:false);
    }
	
	public static InvoicesPrinting getLatestInvoicesPrinting(){
		return InvoicesPrinting.getAll().last();
	}
}
