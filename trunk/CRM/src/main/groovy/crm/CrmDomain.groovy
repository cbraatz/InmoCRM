package crm

import java.lang.reflect.Field;
public abstract class CrmDomain{
	public static SearchAttribute[] searchByAttributes(){//returns the searchBy attribute name, null if it is not searchable
		return null;
	}
	public static String getDefaultPropertyName(){
		return "name";
	}
	public static String getPluralName(){
		return null;
	}
	/*def beforeValidate() {
        if(null==this.id){
			this.id = Utils.getShortUUID();
        }
    }*/
}
