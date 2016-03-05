package crm

import crm.exception.WebPageCreationException;

class WebPage {
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
		keyWords(blank:false, nullable:false, widget:'textArea', size:10..500);
		agentComment(blank:false, nullable:false, widget:'textArea', size:1..255);
		operationType(blank:false, nullable:false, size:1..20);
		pageUrl(blank:true, nullable:true, unique:true);
		domain(blank:false, nullable:false);
		managedProperty(blank:false, nullable:false);
		inWeb(blank:false, nullable:false);
    }
	
}
