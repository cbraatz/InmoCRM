package crm
import crm.exception.WebPageCreationException
import grails.util.Holders;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

class ManagedProperty {
	String title;//web page file name
	String propertyDescription;
	String measures;
	String publicAddress;
	String publicCashPrice;
	String publicCreditPrice;
	Double price;
	Double value;
	Double clientInitialPrice;
	Integer valueDegree;
	Currency currency;
	Date addedDate;
	Integer placedBillboards;
	Float area;
	Float excess;
	Client owner;
	Address address;
	PropertyType propertyType;
	Boolean inWeb;
	static hasMany = [propertyUsages:PropertyUsage, buildings:Building, concessions:Concession, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription, featuresByProperty:FeatureByProperty, uploadedImages:UploadedImage, propertyUnits:PropertyUnit/*,realEstateAction,propertyDocument,insuredGood,tagSelectedValue,customFieldSelectedValue*/];
    static constraints = {
		title(blank:false, nullable:false, size:1..100, unique:true);
		propertyDescription(blank:false, nullable:false, widget:'textArea', size:20..500);
		measures(blank:false, nullable:false, size:1..40);
		publicAddress(blank:false, nullable:false, size:1..100);
		publicCashPrice(blank:false, nullable:false, size:1..25);
		publicCreditPrice(blank:true, nullable:true, size:0..50);
		price(blank:false, nullable:false, min:0D);
		value(blank:false, nullable:false, min:0D);
		clientInitialPrice(blank:false, nullable:false, min:0D);
		valueDegree(blank:false, nullable:false, min:0, max:5);
		currency(blank:false, nullable:false);
		addedDate(nullable:false);
		placedBillboards(nullable:false, min:0);
		area(nullable:false, min:0F);
		excess(nullable:false, min:0F);
		owner(nullable:false);
		address(nullable:false);
		propertyType(nullable:false);
		inWeb(nullable:false);
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
	public void createOrUpdateWebPage() throws WebPageCreationException{
		try{
			String pageContainer=Holders.config.getProperty('web.realPath')+File.separatorChar+Holders.config.getProperty('web.page.realEstate.spanish')+ File.separatorChar+"Paraguay"+File.separatorChar+this.id;
			String title=this.title;
			title=title.replace(" ", "_");
			title=title.replace(".", "_");
			title=title.replace(",", "_");
			title=title.replace("á", "a");
			title=title.replace("é", "e");
			title=title.replace("í", "i");
			title=title.replace("ó", "o");
			title=title.replace("ú", "u");
			title=title.replace("Á", "A");
			title=title.replace("É", "E");
			title=title.replace("Í", "I");
			title=title.replace("Ó", "O");
			title=title.replace("Ú", "U");
			
			//def f = request.getFile('photo')
			new File(pageContainer).mkdirs()
			String filePath=pageContainer+File.separatorChar+File.separatorChar+title+".html";
			String fileContent=GUtils.readFile(Holders.config.getProperty('web.realPath')+File.separatorChar+"real_estate_detail.html");
			Concession conc=this.getActiveConcession();
			
			//PTITLE
			int idx=fileContent.indexOf("@#PTITLE");
			if(idx<0){throw new WebPageCreationException("String: @#PTITLE not found");}
			int idx2=fileContent.indexOf("#@",idx+2);
			String str=fileContent.substring(idx,idx2+2);
			fileContent=fileContent.replace(str, conc.adTitle);
			//PDESC
			idx=fileContent.indexOf("@#PDESC");
			if(idx<0){throw new WebPageCreationException("String: @#PDESC not found");}
			idx2=fileContent.indexOf("#@",idx+2);
			str=fileContent.substring(idx,idx2+2);
			fileContent=fileContent.replace(str, conc.adSummary);
			//HEADER
			idx=fileContent.indexOf("@#HEADER");
			if(idx<0){throw new WebPageCreationException("String: @#HEADER not found");}
			idx2=fileContent.indexOf("#@",idx+2);
			str=fileContent.substring(idx,idx2+2);
			fileContent=fileContent.replace(str, conc.adTitle);
			//RESUM
			idx=fileContent.indexOf("@#RESUM");
			if(idx<0){throw new WebPageCreationException("String: @#RESUM not found");}
			idx2=fileContent.indexOf("#@",idx+2);
			str=fileContent.substring(idx,idx2+2);
			fileContent=fileContent.replace(str, conc.adSummary);
			//REDESC
			idx=fileContent.indexOf("@#REDESC");
			if(idx<0){throw new WebPageCreationException("String: @#REDESC not found");}
			idx2=fileContent.indexOf("#@",idx+2);
			str=fileContent.substring(idx,idx2+2);
			fileContent=fileContent.replace(str, conc.adText);
			//PKEYW
			idx=fileContent.indexOf("@#PKEYW");
			if(idx<0){throw new WebPageCreationException("String: @#PKEYW not found");}
			idx2=fileContent.indexOf("#@",idx+2);
			str=fileContent.substring(idx,idx2+2);
			fileContent=fileContent.replace(str, conc.keys);
			//PRICE
			idx=fileContent.indexOf("@#PRICE");
			if(idx<0){throw new WebPageCreationException("String: @#PRICE not found");}
			idx2=fileContent.indexOf("#@",idx+2);
			str=fileContent.substring(idx,idx2+2);
			fileContent=fileContent.replace(str, (this.publicCreditPrice ? this.publicCreditPrice : this.publicCashPrice));
			//ID
			idx=fileContent.indexOf("@#ID");
			if(idx<0){throw new WebPageCreationException("String: @#ID not found");}
			idx2=fileContent.indexOf("#@",idx+2);
			str=fileContent.substring(idx,idx2+2);
			fileContent=fileContent.replace(str, this.getActiveConcession().id+"-"+this.id.toString());
			//PDESC
			idx=fileContent.indexOf("@#TYPE");
			if(idx<0){throw new WebPageCreationException("String: @#TYPE not found");}
			idx2=fileContent.indexOf("#@",idx+2);
			str=fileContent.substring(idx,idx2+2);
			fileContent=fileContent.replace(str, (conc.isForRent ? "EN ALQUILER" : "EN VENTA"));
			//AGENTCOM
			idx=fileContent.indexOf("@#AGENTCOM");
			if(idx<0){throw new WebPageCreationException("String: @#AGENTCOM not found");}
			idx2=fileContent.indexOf("#@",idx+2);
			str=fileContent.substring(idx,idx2+2);
			fileContent=fileContent.replace(str, conc.agent.agentsGreeting);
			//AGENTPH
			idx=fileContent.indexOf("@#AGENTPH");
			if(idx<0){throw new WebPageCreationException("String: @#AGENTPH not found");}
			idx2=fileContent.indexOf("#@",idx+2);
			str=fileContent.substring(idx,idx2+2);
			fileContent=fileContent.replace(str, Holders.config.getProperty('web.image.partner')+File.separatorChar+conc.agent.partner.id + File.separatorChar + "profile.jpg");
			
			//IMPORTS - LINK
			//#1
			str="rel='stylesheet' href=\"";
			idx=fileContent.indexOf(str);
			if(idx>=0){
				fileContent=fileContent.replace(str, "rel='stylesheet' href=\""+Holders.config.getProperty('web.realPath')+File.separatorChar);
			}else{
				throw new WebPageCreationException("Error replacing import #1. String: "+str+" NOT FOUND.");
			}
			//#2
			str="<script src=\"js";
			idx=fileContent.indexOf(str);
			if(idx>=0){
				fileContent=fileContent.replace(str, "<script src=\""+Holders.config.getProperty('web.realPath')+File.separatorChar+"js");
			}else{
				throw new WebPageCreationException("Error replacing import #2. String: "+str+" NOT FOUND.");
			}
			//#3
			str="<script src=\'js";
			idx=fileContent.indexOf(str);
			if(idx>=0){
				fileContent=fileContent.replace(str, "<script src=\'"+Holders.config.getProperty('web.realPath')+File.separatorChar+"js");
			}else{
				throw new WebPageCreationException("Error replacing import #3. String: "+str+" NOT FOUND.");
			}
			
			//adding images
			def dire = new File(Holders.config.getProperty('web.realPath')+File.separatorChar+Holders.config.getProperty('web.image.property')+File.separatorChar+this.id);
			def fir=true;
			int addedCount=0;
			String newStr, prevStr=null;
			if( dire.exists() ){
				if(dire.isDirectory()){
					if(dire.list().length>0){
						dire.eachFile(){ file->
							UploadedImage uploadedImage=UploadedImage.findByFileName(file.name);
							if(uploadedImage){
								if(uploadedImage.addToWeb){
									newStr="<li data-transition=\"slotfade-vertical\" data-slotamount=\"7\"><img data-retina src=\""+dire.getAbsolutePath().replace("\\", "/")+"/"+file.name+"\"></li>";
									if(fir){
										String oldStr="<li data-transition=\"slotfade-vertical\" data-slotamount=\"7\"><img data-retina src=\"img/crm/home/b3.jpg\"></li>";
										idx=fileContent.indexOf(oldStr);
										if(idx>=0){
											fileContent=fileContent.replace(oldStr, newStr);
										}else{
											throw new WebPageCreationException("Error replacing Property Images. String: "+oldStr+" NOT FOUND.");
										}
										fir=false;
									}else{
										int x=fileContent.indexOf(prevStr)+prevStr.length();
										String str1=fileContent.substring(0,x);
										String str2=fileContent.substring(x+1);
										fileContent=str1+"\n"+newStr+"\n"+str2;
									}
									addedCount++;
									prevStr=new String(newStr);
								}
							}else{
								throw new WebPageCreationException("File:"+file.name+" is not loaded into the CRM.");
							}
						}
					}else{
						throw new WebPageCreationException("The specified image container directory is empty. Directory:"+dire.getAbsolutePath());
					}
				}else{
					throw new WebPageCreationException("The specified image container directory is not a directory. Specified Directory is:"+dire.getAbsolutePath());
				}
			}else{
				throw new WebPageCreationException("The specified image container directory does not exist."); 
			}
			if(addedCount==0){
				throw new WebPageCreationException("No files were added to the web.");
			}
			//IMPORTS #4
			str="src=\"img";
			idx=fileContent.indexOf(str);
			if(idx>=0){
				fileContent=fileContent.replace(str, "src=\""+Holders.config.getProperty('web.realPath')+File.separatorChar+"img");//esta despues de agregar las imagenes xq sino remplaza la imagen por defecto
			}else{
				throw new WebPageCreationException("Error replacing import #4. String: "+str+" NOT FOUND.");
			}
			GUtils.writeFile(filePath, fileContent);
			this.inWeb=true;
			this.save();
			
		}catch(Exception e){
			throw new WebPageCreationException(e);
		}
	}
}