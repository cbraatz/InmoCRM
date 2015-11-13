package crm

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
	public static void main(String[] args){
		def fileResourceInstanceList = []
		//def f = new File(grailsApplication.config.images.location.toString())
		def f = new File("external\\uploads\\images\\property\\105")
		if( f.exists() ){
			f.eachFile(){ file->
			if( !file.isDirectory() )
				System.err.println(file.name);
			}
		}
	}
}
