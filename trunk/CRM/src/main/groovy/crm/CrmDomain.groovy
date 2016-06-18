package crm

public abstract class CrmDomain{
	public static SearchAttribute[] searchByAttributes(){//returns the searchBy attribute name, null if it is not searchable
		return null;
	}
	public static getDefaultPropertyName(){
		return "name";
	}
}
