package crm.enums

public enum DataType {
	STRING("java.lang.String", DataTypeGroup.STRINGS),
	CHARACTER("java.lang.Character",DataTypeGroup.CHARACTERS),
	SHORT("java.lang.Short",DataTypeGroup.NUMBERS),
	INTEGER("java.lang.Integer",DataTypeGroup.NUMBERS),
	LONG("java.lang.Long",DataTypeGroup.NUMBERS),
	FLOAT("java.lang.Float",DataTypeGroup.NUMBERS),
	DOUBLE("java.lang.Double",DataTypeGroup.NUMBERS),
	BOOLEAN("java.lang.Boolean",DataTypeGroup.BOOLEANS),
	DATE("java.util.Date",DataTypeGroup.DATES)	
	
	private final DataTypeGroup dataTypeGroup;
	private final String className;	

	public DataType(String className, DataTypeGroup dataTypeGroup) {
		this.className=className;
		this.dataTypeGroup=dataTypeGroup;
	}

	public static final DataType getByClassName(String dataTypeClassName) {
		if(null!=dataTypeClassName){
			for (DataType dt : this.values()) {
				if(dataTypeClassName.equals(dt.className)){
					return dt;
				}
			}
		}
		return null;
	}
	public boolean validateValue(String value){
		
	}
	private boolean validateStringValue(String val){
		return true;
	}
	private boolean validateCharacterValue(String val){
		return (!val.size()>1);
	}
	private boolean validateShortValue(String val){
		try{
			Short.parseShort(val);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	private boolean validateIntegerValue(String val){
		try{
			Integer.parseInt(val);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	private boolean validateLongValue(String val){
		try{
			Long.parseLong(val);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	private boolean validateFloatValue(String val){
		try{
			Float.parseFloat(val);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	private boolean validateDoubleValue(String val){
		try{
			Double.parseDouble(val);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	private boolean validateBooleanValue(String val){
		try{
			Boolean.parseBoolean(val);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	private boolean validateDateValue(String val){
		try{
			crm.Utils.strToDate(val);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
