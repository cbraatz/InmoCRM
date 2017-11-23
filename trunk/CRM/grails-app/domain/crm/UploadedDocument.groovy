package crm

class UploadedDocument extends CrmDomain{
	String fileName;
	String description;
	Long sizeInKB;
	Concession concession;

    static constraints = {
		fileName(blank:false, nullable:false, size:1..40);
		description(blank:true, nullable:true, size:0..50);
		sizeInKB(nullable:false);
		concession(nullable:true);
    }
	@Override
	public static String getPluralName(){
		return "uploadedDocuments";
	}
}
