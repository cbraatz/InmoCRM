package crm

import java.lang.reflect.Field;

import crm.enums.data.DataType;
import crm.GUtils;
//import java.lang.reflect.Method;

class ReportDesignerColumn extends CrmDomain{
	String propertyName;
	String displayName;
	String tableName;
	String foreignTableName;//Constraint table name eg Concession.CrmUser ==> CrmUser
	String foreignTableDisplay;//Constraint table changed/displayed name eg Concession.Agent ==> Agent
	String parentTableName;//si la tabla es root, parent es null, pero sino hay que especificar la tabla parent. Ej Concession es parent de Comment. Concession es root por eso no tiene parent
	String primaryFilterValue;
	String secondaryFilterValue;
	String filterCriteria;
	Boolean selected;
	Boolean filterBy;
	Boolean sortBy;
	Boolean groupBy;
	Integer sortPosition;
	String sortOrder;
	Integer groupPosition;
	Integer columnWidth;
	ReportDesigner reportDesigner;
	String dataType; //no debería ser persistido

	//static transients = ["dataType"]
    static constraints = {
		propertyName(blank:false, nullable:false, size:1..50);
		displayName(blank:false, nullable:false, size:1..50);
		tableName(blank:false, nullable:false, size:1..50);
		foreignTableName(blank:true, nullable:true, size:0..50);
		foreignTableDisplay(blank:true, nullable:true, size:0..50);
		parentTableName(blank:true, nullable:true, size:0..50);
		primaryFilterValue(blank:true, nullable:true, size:0..100);
		secondaryFilterValue(blank:true, nullable:true, size:0..100);
		filterCriteria(blank:true, nullable:true);
		selected(blank:false, nullable:false);
		filterBy(blank:false, nullable:false);
		sortBy(blank:false, nullable:false);
		groupBy(blank:false, nullable:false);
		sortPosition(blank:true, nullable:true);
		sortOrder(blank:true, nullable:true, size:0..4);
		groupPosition(blank:true, nullable:true);
		columnWidth(blank:true, nullable:true);
		reportDesigner(blank:false, nullable:false);
		dataType(blank:false, nullable:false, size:1..10);
    }
	
	public ReportDesignerColumn() { }
	
	public ReportDesignerColumn(String tableName, String parentTableName, Field field) {
		if(field.getType().getSuperclass().getName().equals("crm.CrmDomain")){//si es una relacion many to one
			try {
				//Class<crm.CrmDomain> crmDom=(Class<crm.CrmDomain>) field.getType();
				//Method m=crmDom.getMethod("getDefaultPropertyName", null);//una opcion a estas 3 linease seria en una clase groovy hacer    this.name=field.getName()+"."+field.getType().getDefaultPropertyName();
				//this.name=field.getName()+"."+m.invoke(null, null);
				
				this.propertyName=field.getType().getDefaultPropertyName();//solo se puede hacer si es un Domain Object
				this.foreignTableName=GUtils.extractCRMPrefixFromClassName(field.getType().getName());
				this.foreignTableDisplay=field.getName();
				this.dataType=(String) DataType.DOMAIN;
			} catch (Exception e) {
				GUtils.printException(e, null);
			}
		}else{
			this.propertyName=field.getName();
			this.foreignTableName=null;
			this.foreignTableDisplay=null;
			this.dataType=(String) DataType.getByClassName(field.getType().getName());
		}
		this.tableName=GUtils.extractCRMPrefixFromClassName(tableName);
		this.parentTableName=(parentTableName!=null?GUtils.extractCRMPrefixFromClassName(parentTableName):null);
		this.selected=false;
		this.filterBy=false;
		this.sortBy=false;
		this.groupBy=false;
		this.sortPosition=0;
		this.groupPosition=0;
		//this.filterValue=null;
		System.out.println("prop="+propertyName+"  tableName="+tableName+"  foreignTableName="+foreignTableName+"   tableDisplay="+foreignTableDisplay+"   dataType="+dataType);
	}
	@Override
	public static String getPluralName(){
		return "reportDesignerColumns";
	}
	public String getLabelName(){
		return (null == this.foreignTableName ? GUtils.getFirstCharInLowerCase(this.tableName) : GUtils.getFirstCharInLowerCase(this.foreignTableName))+'.'+(this.foreignTableDisplay != null ? this.foreignTableDisplay : this.propertyName)+'.label';
	}
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("tableName="+tableName);
		sb.append(", foreignTableName="+foreignTableName);
		sb.append(", foreignTableDisplay="+foreignTableDisplay);
		sb.append(", parentTableName="+parentTableName);
		sb.append(", propertyName="+propertyName);
		sb.append(", primaryFilterValue="+primaryFilterValue);
		sb.append(", secondaryFilterValue="+secondaryFilterValue);
		sb.append(", filterCriteria="+filterCriteria);
		sb.append(", selected="+selected);
		sb.append(", filterBy="+filterBy);
		sb.append(", sortBy="+sortBy);
		sb.append(", groupBy="+groupBy);
		sb.append(", sortPosition="+sortPosition);
		sb.append(", sortOrder="+sortOrder);
		sb.append(", groupPosition="+groupPosition);
		sb.append(", columnWidth="+columnWidth);
		sb.append(", reportDesigner="+reportDesigner);
		sb.append(", dataType="+dataType);
		return sb.toString();
	}
	/*public String getDataType(){
		Class<?> itemClass=Class.forName("crm."+this.tableName);
		for(java.lang.reflect.Field f:itemClass.declaredFields){//java.lang.reflect.Field is each one
			if(this.propertyName.equals(f.getName())){
				if(!f.isSynthetic()){
					 String t=f.getType().getName();
					//System.out.println("Name"+it.getName()+" Type "+t);
					 if(!(t.equals("java.lang.Object") || t.equals("java.util.Set") || t.equals("org.apache.commons.logging.Log") || t.equals("org.grails.datastore.gorm.GormStaticApi") || t.equals("org.grails.datastore.gorm.GormInstanceApi") || t.equals("org.grails.datastore.gorm.GormValidationApi") || t.equals("org.springframework.validation.Errors") || t.equals("org.grails.plugins.web.controllers.api.ControllersDomainBindingApi") || t.equals("org.grails.plugins.converters.api.ConvertersApi") || t.equals("java.util.List") || it.getName().equals("version"))){
						 if(f.getType().getSuperclass().getName().equals("crm.CrmDomain")){//si es una relacion many to one
							 return "crm.CrmDomain";
						 }else{
						 	 return t;
						 }
					 }
				 }else{
				 	return null;
				 }
			}
			//break;
		}
	}*/
}
