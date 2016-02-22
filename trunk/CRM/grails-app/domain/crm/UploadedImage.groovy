package crm

class UploadedImage {
	String fileName;
	String description;
	String path;
	Long sizeInKB;
	Boolean isMainImage;
	Boolean addToWeb;
	ManagedProperty managedProperty;
    static constraints = {
		fileName(blank:false, nullable:false, size:1..40);
		description(blank:true, nullable:true, size:0..50);
		path(blank:false, nullable:false, size:1..200);
		sizeInKB(nullable:false);
		isMainImage(nullable:false);
		addToWeb(nullable:false);
		managedProperty(nullable:false);
    }
}
