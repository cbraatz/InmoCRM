package crm

class CrmConfig {
	String uploadURL;
    String dateFormat;
    String companyName;
    static constraints = {
        uploadURL(blank:false, nullable:false, size:1..200);
        dateFormat(blank:false, nullable:false, size:1..50);
        companyName(blank:false, nullable:false, size:1..100);
    }
}
