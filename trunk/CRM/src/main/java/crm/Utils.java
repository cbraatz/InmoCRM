package crm;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public static String getShortUUIDWithNumbers(String id){
    	return (!id.isEmpty() ? id+'-' : "")+Utils.getNumericID()+'-'+UUID.randomUUID().toString().substring(0, 7);
    }
    public static Long getNumericID(){
    	Date date=new Date();
    	return date.getTime();
    }
    public static Date addToDate(Date date, int days, int months){
    	//SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    	//sdf.format(new Date());
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH,months);
		c.add(Calendar.DATE,days);
		return c.getTime();
    }
    public static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    public static double round(double d, boolean withDecimals) {
    	return Utils.round(d, (withDecimals ? Utils.getDefaultDecimalPlaces() : 0));
    }
    public static String getDateInStr(Date date){
    	if(null==date){
    		return null;
    	}else{
	    	DateFormat sdf=new SimpleDateFormat(Utils.getDefaultDateFormat());
	    	return sdf.format(date);
    	}
    }
    public static String getDateInStrDBFormat(Date date){
    	DateFormat sdf=new SimpleDateFormat(Utils.getDBDateFormat());
    	return sdf.format(date);
    }
    public static Date removeTimeFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static String getDefaultDateFormat(){
		return "dd/MM/yyyy";
	}
	public static String getDBDateFormat(){
		return "yyyy/MM/dd";
	}
	public static String getIntegerPartIfNoDecimals(Double value){
		if(Utils.validateDecimals(value, false)){
			return Utils.formatIntegers(value);
		}else{
			return Utils.formatDecimalsForInput(value);
		}
	}
	public static boolean hasDecimalValue(Double value){
		if(new BigDecimal(value - Math.floor(value)).doubleValue() > 0){
			return true;
		}else{
			return false;
		}
	}
	public static String formatIntegers(Double value){
		NumberFormat nf = NumberFormat.getNumberInstance();
		DecimalFormat df = (DecimalFormat)nf;
		df.applyPattern("###,###");
		return df.format(value);
	}
	public static String formatDecimals(Double value){
		System.out.println(value.doubleValue());
		NumberFormat nf = NumberFormat.getNumberInstance();
		DecimalFormat df = (DecimalFormat)nf;
		df.applyPattern("###,###.##");
		return df.format(value);
	}

	public static String formatDecimalsForInput(Double value){
		System.out.println(value.doubleValue());
		NumberFormat nf = NumberFormat.getNumberInstance();
		DecimalFormat df = (DecimalFormat)nf;
		df.applyPattern("###.##");
		return df.format(value);
	}

	public static int getDefaultDecimalPlaces(){
		return 2;
	}
	public static boolean validateDecimals(Double value, boolean hasDecimals){
		int n=Utils.getNumberOfDecimalPlaces(value);
		if(n>0){
			return (hasDecimals==false || n > Utils.getDefaultDecimalPlaces()? false : true);
		}else{
			return true;
		}
	}
	/*public static int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
	    String string = bigDecimal.stripTrailingZeros().toPlainString();
	    int index = string.indexOf(".");
	    return index < 0 ? 0 : string.length() - index - 1;
	}*/
	public static int getNumberOfDecimalPlaces(Double value){
		String strVal = BigDecimal.valueOf(value).stripTrailingZeros().toPlainString();
		int integerPlaces = strVal.indexOf('.');
		if(integerPlaces < 0){//if it does not have dot
			return 0;
		}
		int decimalPlaces = strVal.length() - integerPlaces - 1;
		if(decimalPlaces == 1){
			if(strVal.substring(integerPlaces+1).equals("0")){//if value is x.0
				return 0;
			}
		}
		return decimalPlaces;
		
	}
	public static void main(String args[]){
		System.out.println(Utils.getIntegerPartIfNoDecimals(new Double("5444544.898")));
	}
	
}
