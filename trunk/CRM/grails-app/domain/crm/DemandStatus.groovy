package crm

class DemandStatus extends CrmDomain{
	String name;
	String internalID;
	static hasMany = [propertyDemands:PropertyDemand/*InsuranceDemand*/];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		internalID(blank:false, nullable:false, unique:true, size:1..10);
	}
	@Override
	public static String getPluralName(){
		return "demandStatuses";
	}
	public static String getNewDemandStatusInternalID() {
		return "1";
	}
	public static String getNeedMoreInfoDemandStatusInternalID() {
		return "2";
	}
	public static String getVerifiedDemandStatusInternalID() {
		return "3";
	}
	public static String getTakenDemandStatusInternalID() {
		return "4";
	}
	public static String getNotRelevantDemandStatusInternalID() {
		return "5";
	}
	public static String getOldDemandStatusInternalID() {
		return "6";
	}
	public static String getClosedDemandStatusInternalID() {
		return "7";
	}
	public static DemandStatus getNewDemandStatus() {
		return DemandStatus.findByInternalID(DemandStatus.getNewDemandStatusInternalID());
	}
	public static DemandStatus getNeedMoreInfoDemandStatus() {
		return DemandStatus.findByInternalID(DemandStatus.getNeedMoreInfoDemandStatusInternalID());
	}
	public static DemandStatus getVerifiedDemandStatus() {
		return DemandStatus.findByInternalID(DemandStatus.getVerifiedDemandStatusInternalID());
	}
	public static DemandStatus getTakenDemandStatus() {
		return DemandStatus.findByInternalID(DemandStatus.getTakenDemandStatusInternalID());
	}
	public static DemandStatus getNotRelevantDemandStatus() {
		return DemandStatus.findByInternalID(DemandStatus.getNotRelevantDemandStatusInternalID());
	}
	public static DemandStatus getOldDemandStatus() {
		return DemandStatus.findByInternalID(DemandStatus.getOldDemandStatusInternalID());
	}
	public static DemandStatus getClosedDemandStatus() {
		return DemandStatus.findByInternalID(DemandStatus.getClosedDemandStatusInternalID());
	}
}
