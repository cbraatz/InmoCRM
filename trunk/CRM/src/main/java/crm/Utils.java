package crm;

import java.util.Date;
import java.util.UUID;

public class Utils {
    public static String getActionFromUri(String uri){
        int idx=uri.lastIndexOf('/');
        System.out.println("controller="+uri.substring(1,idx));
        return uri.substring(idx+1);
    }
    public static String getControllerFromUri(String uri){
        int idx=uri.lastIndexOf('/');
        return uri.substring(1,idx);
    }
    public static String getUUID(){
    	return UUID.randomUUID().toString();
    }
    public static Long getNumericID(){
    	Date date=new Date();
    	return date.getTime();
    }
}
