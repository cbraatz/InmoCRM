package crm

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import crm.ui.SearchResultItem
import grails.util.Holders;
class GUtils {
	public static void printErrors(Object obj, String message){

		if(message){
			System.err.&println "Error message: '"+message+"'";
		}
		if(obj){
			System.err.&println "Error in '"+obj.toString()+"'";
			obj.errors.each {
				System.err.&println it;
			}
		}
	}
	public static String readFile(String path){
		String res=null;
		try{
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			res= new String(encoded, StandardCharsets.UTF_8);
		}catch(IOException e){
			GUtils.printException(e, "Exception reading file "+path);
		}
		return res;
	}
	public static boolean writeFile(String path, String content) throws Exception{
		File file = new File(path);
		/*FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);*/
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
		bw.write(content);
		bw.close();
	}
	public static void printException(Exception e, String message){
		if(message){
			System.err.&println "Error message: '"+message+"'";
		}
		if(e){
			e.printStackTrace(System.out);
		}
	}
	public static String getClassNameFromInstance(Object objInstance){
		return Class.forName(objInstance.class.name).getSimpleName();
	}
	public static String getFirstCharInLowerCase(String str){
		return Character.toLowerCase(str.charAt(0)).toString()+str.substring(1);
	}
	public static String getLowerClassNameFromInstance(Object objInstance){
		String objectName=GUtils.getClassNameFromInstance(objInstance);
		return getFirstCharInLowerCase(objectName);
	}
	public static String getLowerClassNameFromClassName(String name){
		return getFirstCharInLowerCase(extractCRMPrefixFromClassName(name));
	}
	public static String extractCRMPrefixFromClassName(String name){
		return (name.indexOf('crm.')>=0?name.substring(4):name);//quita el crm. si tiene
	}
	public static void main(String[] args){
		Class<?> itemClass=Class.forName("crm.ManagedProperty");
		//Primero agrego los fields que NO son many to one
		itemClass.declaredFields.each{//java.lang.reflect.Field is each one
			if(!it.isSynthetic()){
				 String t=it.getType().getName();
				//System.out.println("Name"+it.getName()+" Type "+t);
				 if(!(t.equals("java.lang.Object") || t.equals("java.util.Set") || t.equals("org.apache.commons.logging.Log") || t.equals("org.grails.datastore.gorm.GormStaticApi") || t.equals("org.grails.datastore.gorm.GormInstanceApi") || t.equals("org.grails.datastore.gorm.GormValidationApi") || t.equals("org.springframework.validation.Errors") || t.equals("org.grails.plugins.web.controllers.api.ControllersDomainBindingApi") || t.equals("org.grails.plugins.converters.api.ConvertersApi") || t.equals("java.util.List") || it.getName().equals("version"))){
					 if(!it.getType().getSuperclass().getName().equals("crm.CrmDomain")){//si NO una relacion many to one
						 System.out.println(it.getName()+"  "+t);
					 }
				 }
			}
		}
		
	}	
}
