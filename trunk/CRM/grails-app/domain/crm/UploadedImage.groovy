package crm

class UploadedImage extends CrmDomain{
	String fileName;
	String description;
	Long sizeInKB;
	Boolean isMainImage;
	Boolean addToWeb;
	ManagedProperty managedProperty;
    static constraints = {
		fileName(blank:false, nullable:false, size:1..40);
		description(blank:true, nullable:true, size:0..50);
		sizeInKB(nullable:false);
		isMainImage(nullable:false);
		addToWeb(nullable:false);
		managedProperty(nullable:true);
    }
	@Override
	public static String getPluralName(){
		return "uploadedImages";
	}
}
