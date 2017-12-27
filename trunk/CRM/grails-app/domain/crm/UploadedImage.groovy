package crm

class UploadedImage extends CrmDomain{
	String fileName;
	String description;
	Long sizeInKB;
	Boolean isMainImage;
	Boolean addToWeb;
	Date date;
	ManagedProperty managedProperty;
    static constraints = {
		fileName(blank:false, nullable:false, size:1..40);
		description(blank:true, nullable:true, size:0..50);
		sizeInKB(nullable:false);
		isMainImage(nullable:false);
		addToWeb(nullable:false);
		date(nullable:false);
		managedProperty(nullable:true);
    }
	@Override
	public static String getPluralName(){
		return "uploadedImages";
	}
}
