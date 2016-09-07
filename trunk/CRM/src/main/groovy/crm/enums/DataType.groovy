package crm.enums

public enum DataType {
	STRING("java.lang.String", DataTypeGroup.STRINGS),
	CHARACTER("java.lang.Character", DataTypeGroup.CHARACTERS),
	SHORT("java.lang.Short", DataTypeGroup.NUMBERS),
	INTEGER("java.lang.Integer", DataTypeGroup.NUMBERS),
	LONG("java.lang.Long", DataTypeGroup.NUMBERS),
	FLOAT("java.lang.Float", DataTypeGroup.NUMBERS),
	DOUBLE("java.lang.Double", DataTypeGroup.NUMBERS),
	BOOLEAN("java.lang.Boolean", DataTypeGroup.BOOLEANS),
	DATE("java.util.Date", DataTypeGroup.DATES),	
	DOMAIN_CLASS("crm.CrmDomain", DataTypeGroup.DOMAIN_CLASSES)
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
		switch(this){
			case "STRING": return validateStringValue(value);
			case "CHARACTER": return validateCharacterValue(value);
			case "SHORT": return validateShortValue(value);
			case "INTEGER": return validateIntegerValue(value);
			case "LONG": return validateLongValue(value);
			case "FLOAT": return validateFloatValue(value);
			case "DOUBLE": return validateDoubleValue(value);
			case "BOOLEAN": return validateBooleanValue(value);
			case "DATE": return validateDateValue(value);
			case "DOMAIN_CLASS": return validateLongValue(value);
			otherwise: return false;
		}
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
		return (null != crm.Utils.strToDate(val));
	}
	
}
