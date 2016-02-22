package crm

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	
	public static void main(String[] args){
		String realPath="D://TRABAJOS//crm//git_projects//root//web_trunk//HTML//html";
		String fileContent=GUtils.readFile(realPath+File.separatorChar+"real_estate_detail.html");
		String oldStr="<li data-transition=\"slotfade-vertical\" data-slotamount=\"7\"><img data-retina src=\"img/crm/home/b3.jpg\"></li>";
		System.err.println("IDXold="+fileContent.indexOf(oldStr));
	}
}
