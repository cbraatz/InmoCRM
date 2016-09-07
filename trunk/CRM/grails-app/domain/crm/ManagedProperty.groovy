package crm
import java.util.List;

import crm.exception.WebPageCreationException
import grails.util.Holders;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

class ManagedProperty extends CrmDomain{
	String title;
	String propertyDescription;
	String measures;
	String publicAddress;
	String publicCashPrice;
	String publicCreditPrice;
	Double price;
	Double value;
	String clientInitialPrice;
	Double commissionAmount;
	Integer valueDegree;
	Currency currency;
	Date addedDate;
	Integer placedBillboards;
	Float area;
	Float excess;
	Client owner;
	Address address;
	PropertyType propertyType;
	Boolean soldByUs;
	
	static hasMany = [propertyUsages:PropertyUsage, buildings:Building, concessions:Concession, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription, featuresByProperty:FeatureByProperty, uploadedImages:UploadedImage, propertyUnits:PropertyUnit, advertisements:Advertisement, webPages:WebPage/*propertyDocument,insuredGood,tagSelectedValue,customFieldSelectedValue*/];
    static constraints = {
		title(blank:false, nullable:false, size:1..100, unique:true);
		propertyDescription(blank:false, nullable:false, widget:'textArea', size:20..500);
		measures(blank:false, nullable:false, size:1..40);
		publicAddress(blank:false, nullable:false, size:1..100);
		publicCashPrice(blank:false, nullable:false, size:1..25);
		publicCreditPrice(blank:true, nullable:true, size:0..50);
		price(blank:false, nullable:false, min:0D);
		value(blank:false, nullable:false, min:0D);
		clientInitialPrice(blank:true, nullable:true, size:0..100);
		commissionAmount(blank:true, nullable:true, min:0D);
		valueDegree(blank:false, nullable:false, min:0, max:5);
		currency(blank:false, nullable:false);
		addedDate(nullable:false);
		placedBillboards(nullable:false, min:0);
		area(nullable:false, min:0F);
		excess(nullable:false, min:0F);
		owner(nullable:false);
		address(nullable:false);
		propertyType(nullable:false);
		soldByUs(nullable:false);
    }
	
	public Concession getActiveConcession(){
		List<Concession> list=ManagedProperty.executeQuery("select c from ManagedProperty p join p.concessions c where p.id = :pid and c.isActive = :a",[pid:this.id, a:true]); 
		if(list.size()==1){
			return (Concession)list.get(0);
		}else{
			System.err.println("Concession list size = "+list.size());
			return null;
		}
	}
	public boolean hasImagesForWeb(){
		List<UploadedImage> list=ManagedProperty.executeQuery("select u from ManagedProperty p join p.uploadedImages u where p.id = :pid and u.addToWeb = :w",[pid:this.id, w:true]);
		return (list.size()>0);
	}
	
	@Override
	public static SearchAttribute[] searchByAttributes() {
		return [new SearchAttribute("title"),new SearchAttribute("propertyDescription"),new SearchAttribute("id",false)];
	}
}