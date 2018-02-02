package crm

import crm.exception.WebPageCreationException;

class WebPage extends CrmDomain{
	String title;
	String summary;
	String firstDescription;
	String secondDescription;
	String thirdDescription;
	String callToAction;
	String price;
	String keyWords;
	String agentComment;
	String operationType;
	String pageUrl;
	Domain domain;
	ManagedProperty managedProperty;
	Boolean inWeb;
    static constraints = {
		title(blank:false, nullable:false, unique:true, size:10..100);
		summary(blank:false, nullable:false, widget:'textArea', size:10..300);
		firstDescription(blank:false, nullable:false, widget:'textArea', size:10..300);
		secondDescription(blank:true, nullable:true, widget:'textArea', size:0..300);
		thirdDescription(blank:true, nullable:true, widget:'textArea', size:0..300);
		callToAction(blank:false, nullable:false, widget:'textArea', size:10..300);
		price(blank:false, nullable:false, size:1..20);
		keyWords(blank:false, nullable:false, widget:'textArea', size:10..1000);
		agentComment(blank:false, nullable:false, widget:'textArea', size:1..255);
		operationType(blank:false, nullable:false, size:1..20);
		pageUrl(blank:true, nullable:true, unique:true);
		domain(blank:false, nullable:false);
		managedProperty(blank:false, nullable:false);
		inWeb(blank:false, nullable:false);
    }	
	@Override
	public static String getPluralName(){
		return "webPages";
	}
	public String getWebPage(){
		return "http://www."+this.domain.name+this.pageUrl;
    }
	public void updateWebPageUrl(){
		this.pageUrl=this.getWebPage();
	}
	public String getCountryNameForWeb() {
		return Utils.convertToWebUrlFormat(this.managedProperty.address.city.department.country.name.toLowerCase());
	}
	public String getDepartmentNameForWeb() {
		return Utils.convertToWebUrlFormat(this.managedProperty.address.city.department.name.toLowerCase());
	}
	public String getDomainRealPathForWeb() {
		return this.domain.realPath;
	}
	public String getRealEstateFolderName() {
		return this.domain.realEstateFolder;
	}
	public String getCityNameForWeb() {
		return Utils.convertToWebUrlFormat(this.managedProperty.address.city.name.toLowerCase());
	}
	public getWebPagePath() {
		return this.getDomainRealPathForWeb()+Utils.getLocalSeparatorChar()+this.getRealEstateFolderName()+Utils.getLocalSeparatorChar()+this.getCountryNameForWeb()+Utils.getLocalSeparatorChar()+this.managedProperty.id;
	}
}