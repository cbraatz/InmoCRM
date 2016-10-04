package crm.db

import java.util.List;
import java.lang.reflect.Field;
import crm.ReportDesignerColumn;
import crm.exception.CRMException;

class CrmDbTable {
	private String name;//name without crm.
	private String alias;
	private String parent;
	private boolean isForeign;
	public CrmDbTable(String name, String parent, boolean isForeign) throws CRMException{
		this.name=name;
		this.parent=parent;
		this.isForeign=isForeign;
		if(null!=parent){
			if(!this.areValidParentAndChildTables()){
				throw new CRMException("'"+parent+ "' is not a valid Parent table for '"+name+"'.");
			}
		}
	}
	public CrmDbTable(String name, String parent, String alias, boolean isForeign) throws CRMException{
		this.name=name;
		this.parent=parent;
		this.alias=alias;
		this.isForeign=isForeign;
		if(null!=parent){
			if(!this.areValidParentAndChildTables()){
				throw new CRMException("'"+parent+ "' is not a valid Parent table for '"+name+"'.");
			}
		}
	}
	public String getName(){
		return this.name;
	}
	public String getAlias(){
		return this.alias;
	}
	public String getParent(){
		return this.parent;
	}
	public void setAlias(String alias){
		this.alias=alias;
	}
	private List<String> getOneToManySetTableNames(String table) throws CRMException{
		List<String> list=new ArrayList<String>();
		if(this.getParent()!=null){
			Class<?> parentClass=Class.forName("crm."+table);
			//Class<?> childClass=Class.forName("crm."+this.getName());
			parentClass.declaredFields.each{
				if(!it.isSynthetic()){
					if(it.getType().getName().equals("java.util.Set")){
							list.add(it.getName());
					}
				}
			}
		}
		return list;
	}
	public List<Field> getAllTableFields()  throws CRMException, NumberFormatException{
		List<Field> fields=new ArrayList<>();
		Class<?> itemClass=Class.forName("crm."+this.getName());
		//Primero agrego los fields que NO son many to one
		itemClass.declaredFields.each{
			if(!it.isSynthetic()){
				String t=it.getType().getName();
				//System.out.println("Name"+it.getName()+" Type "+t);
				if(!(t.equals("java.lang.Object") || t.equals("java.util.Set") || t.equals("org.apache.commons.logging.Log") || t.equals("org.grails.datastore.gorm.GormStaticApi") || t.equals("org.grails.datastore.gorm.GormInstanceApi") || t.equals("org.grails.datastore.gorm.GormValidationApi") || t.equals("org.springframework.validation.Errors") || t.equals("org.grails.plugins.web.controllers.api.ControllersDomainBindingApi") || t.equals("org.grails.plugins.converters.api.ConvertersApi") || t.equals("java.util.List") || it.getName().equals("version"))){
					if(!it.getType().getSuperclass().getName().equals("crm.CrmDomain")){//si NO es una relacion many to one
						fields.add(it);
					}
				}
			}
		}
		//Luego agrego los fields son many to one
		itemClass.declaredFields.each{
			if(!it.isSynthetic()){
				String t=it.getType().getName();
			   //System.out.println("Name"+it.getName()+" Type "+t);
				if(!(t.equals("java.lang.Object") || t.equals("java.util.Set") || t.equals("org.apache.commons.logging.Log") || t.equals("org.grails.datastore.gorm.GormStaticApi") || t.equals("org.grails.datastore.gorm.GormInstanceApi") || t.equals("org.grails.datastore.gorm.GormValidationApi") || t.equals("org.springframework.validation.Errors") || t.equals("org.grails.plugins.web.controllers.api.ControllersDomainBindingApi") || t.equals("org.grails.plugins.converters.api.ConvertersApi") || t.equals("java.util.List") || it.getName().equals("version"))){
					if(it.getType().getSuperclass().getName().equals("crm.CrmDomain")){//si es una relacion many to one
						fields.add(it);
					}
				}
			}
		}
		return fields;
	}
	private boolean areValidParentAndChildTables() throws CRMException{//if in parent table is a hasMany pointing to name (child)
		List<String> chs=this.getOneToManySetTableNames((this.isForeign ? this.getName() : this.getParent()));
		String tablePluralName=Class.forName("crm."+(this.isForeign ? this.getParent() : this.getName())).getPluralName();
		for(String ch:chs){
			if(ch.toUpperCase().indexOf(tablePluralName.toUpperCase())==0){//si empieza con ese nombre
				return true;
			}
		}
		System.err.println("No se encontr� la tabla "+tablePluralName+" dentro del grupo de manyToOne "+chs.toString());
		return false;
	}
}
