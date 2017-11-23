package crm

class Contract extends CrmDomain{
	Date startDate;
	Date endDate;
	ContractType contractType;
	String internalID;
	Boolean isCurrentContract;
	UploadedDocument uploadedDocument;
	Concession concession
    static constraints = {
		startDate(blank: false, nullable:false);
		endDate(blank: false, nullable:false);//start y end dates no son siempre iguales a las de la concesion, ya que la concesion al renovarse se estira su end date, pero el startDate queda el mismo.
		contractType(nullable:false);
		internalID(blank: false, nullable:false, size:1..40, unique:true);
		isCurrentContract(blank: false, nullable:false);
		uploadedDocument(nullable:true);
		concession(nullable:false);
    }
	@Override
	public static String getDefaultPropertyName(){
		return "internalID";
	}
	@Override
	public static String getPluralName(){
		return "contracts";
	}
	/*public static List<Contract> getAllContractsByConcession(Concession concession){
		return Contract.findAllByConcession(concession);
	}*/
}
