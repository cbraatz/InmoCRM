package crm

class CommissionType extends CrmDomain{
	String name;
	String description;
	String selfInvoiceDefaultDescription;
	String internalID;
	Boolean appliedToAllSales;//se agrega la comision a todas las ventas
	Boolean appliedToSalesByOffice;//se agrega la comision a todas las ventas de la oficina
	ExpenseType expenseType;
	
	//appliedWhenCondition
	static hasMany = [commissionRates:CommissionRate];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:false, nullable:false, widget:'textArea', size:1..200);
		selfInvoiceDefaultDescription(blank:false, nullable:false, size:1..100);
		internalID(blank:false, nullable:false, unique:true, size:1..10);
		appliedToAllSales(blank:false, nullable:false);
		appliedToSalesByOffice(blank:false, nullable:false);
		expenseType(nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "commissionTypes";
	}
	public static String getPartnerSellCommissionTypeInternalID() {
		return "1"
	}
	public static String getPartnerCatchCommissionTypeInternalID() {
		return "2"
	}
	public static String getPartnerWithAtalayaSellCommissionTypeInternalID() {
		return "3"
	}
	public static String getPartnerWithAtalayaCatchCommissionTypeInternalID() {
		return "4"
	}
	public static String getAtalayaSellCommissionTypeInternalID() {
		return "5"
	}
	public static String getAtalayaCatchCommissionTypeInternalID() {
		return "6"
	}
	public static CommissionType getPartnerSellCommissionType(){
		return CommissionType.findByInternalID(CommissionType.getPartnerSellCommissionTypeInternalID());
	}
	public static CommissionType getPartnerCatchCommissionType(){
		return CommissionType.findByInternalID(CommissionType.getPartnerCatchCommissionTypeInternalID());
	}
	public static CommissionType getPartnerWithAtalayaSellCommissionType(){
		return CommissionType.findByInternalID(CommissionType.getPartnerWithAtalayaSellCommissionTypeInternalID());
	}
	public static CommissionType getPartnerWithAtalayaCatchCommissionType(){
		return CommissionType.findByInternalID(CommissionType.getPartnerWithAtalayaCatchCommissionTypeInternalID());
	}
	public static CommissionType getAtalayaSellCommissionType(){
		return CommissionType.findByInternalID(CommissionType.getAtalayaSellCommissionTypeInternalID());
	}
	public static CommissionType getAtalayaCatchCommissionType(){
		return CommissionType.findByInternalID(CommissionType.getAtalayaCatchCommissionTypeInternalID());
	}
}
