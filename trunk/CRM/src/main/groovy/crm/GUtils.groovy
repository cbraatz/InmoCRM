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
}
