package crm

class PropertyDemandType extends CrmDomain{
	String name;
	String internalID;
	static hasMany = [propertyDemands:PropertyDemand];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		internalID(blank:false, nullable:false, unique:true, size:1..10);
	}
	@Override
	public static String getPluralName(){
		return "PropertyDemandTypes";
	}
	public static String getSellDemandInternalID() {
		return "SELL"
	}
	public static String getBuyDemandInternalID() {
		return "BUY"
	}
	
	public static PropertyDemandType getSellDemand() {
		return PropertyDemandType.findByInternalID(PropertyDemandType.getSellDemandInternalID());
	}
	public static PropertyDemandType getBuyDemand() {
		return PropertyDemandType.findByInternalID(PropertyDemandType.getBuyDemandInternalID());
	}
	public boolean isSellDemand() {
		return this.internalID.equals(PropertyDemandType.getSellDemandInternalID());
	}
	public boolean isBuyDemand() {
		return this.internalID.equals(PropertyDemandType.getBuyDemandInternalID());
	}
}
