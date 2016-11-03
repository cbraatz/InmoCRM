package crm

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile
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
	public static void removeAllFilesFromDirectory(String directoryPath){
		def dire = new File(directoryPath);
		if( dire.exists() ){
			dire.eachFile(){ file->
				file.delete();
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
	public static String getFirstCharInUpperCase(String str){
		return Character.toUpperCase(str.charAt(0)).toString()+str.substring(1);
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
	public static String replaceIncorrectChars(String str){
		str=str.replace(" ", "_");
		str=str.replace(".", "_");
		str=str.replace(",", "_");
		str=str.replace("á", "a");
		str=str.replace("é", "e");
		str=str.replace("í", "i");
		str=str.replace("ó", "o");
		str=str.replace("ú", "u");
		str=str.replace("Á", "A");
		str=str.replace("É", "E");
		str=str.replace("Í", "I");
		str=str.replace("Ó", "O");
		str=str.replace("Ú", "U");
		return str;
	}
	public static boolean stringFinishesWith(String mainStr, String endStr){
		return mainStr.substring(mainStr.length()-endStr.length()).equals(endStr);
	}
	public static boolean transferFile(MultipartFile inFile, File outFile){
		try{
			inFile.transferTo(outFile);
			return outFile.exists();
		}catch(Exception e){
			return false;
		}
	}
	/*public static void main(String[] args){
		String query="SELECT c.isNegotiable, c.startDate FROM Concession AS c INNER JOIN c.managedProperties AS m";
		java.sql.ResultSetMetaData resm=Concession.executeQuery(query).getMetaData();
		final int columnCount = resm.getColumnCount();
		List<List<String>> rowList = new LinkedList<List<String>>();
		while(resm.next()){
		    List<String> columnList = new LinkedList<String>();
		    rowList.add(columnList);
		
		    for (int column = 1; column <= columnCount; column++){
		        Object value = resm.getObject(column);
		        columnList.add(String.valueOf(value));
		    }
		}
		System.out.println(rowList.size());
	}	*/
}
