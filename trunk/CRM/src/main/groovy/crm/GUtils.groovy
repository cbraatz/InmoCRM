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
	public static String getLowerClassNameFromInstance(Object objInstance){
		String objectName=GUtils.getClassNameFromInstance(objInstance);
		return Character.toLowerCase(objectName.charAt(0)).toString()+objectName.substring(1);
	}
	public static String getLowerNameFromString(String instanceName){
		return Character.toLowerCase(instanceName.charAt(0)).toString()+instanceName.substring(1);
	}
	public static void main(String[] args){
		ManagedProperty man=new ManagedProperty(title:"Terreno de 1200m2 en Obligado Centro, al lado del Centro de Salud", propertyDescription:"Terreno con vereda y árboles frutales", measures:"20m x 60m", publicAddress:"Obligado Centro, cerca del Centro de Salud", publicCashPrice:"240.000 USS", price:240000, value:250000,
			clientInitialPrice:240000, addedDate:new Date(), placedBillboards:1, area:1200,excess:2, valueDegree:1);
		/*String sss=man["title"];
		SearchResultItem se=new SearchResultItem(man,"propertyDescription","Terreno");
		System.out.println(se.getDisplayValue());
		System.out.println(se.getObjectName());
		System.out.println(se.getLinkTo());*/
		System.out.println(GUtils.getLowerClassNameFromInstance(man));
	}	
}
