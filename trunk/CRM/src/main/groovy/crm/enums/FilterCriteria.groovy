package crm.enums

public enum FilterCriteria {
	LIKE_VALUE("Strings",1),
	EXACT_VALUE("Strings",1),
	BETWEEN_VALUES("Numbers",2),
	START_VALUE("Numbers",1),
	END_VALUE("Numbers",1),
	BETWEEN_DATES("Dates",2),
	START_DATE("Dates",1),
	END_DATE("Dates",1),
	BOOLEAN_VALUE("Booleans",1),
	CHAR_VALUE("Chars",1)
	
	private final String appliedTo;
	private final Short numberOfFields;
	public static final List<FilterCriteria> getAllFilterCriteriaList(String dataType) {
		List<FilterCriteria> list=new ArrayList<>();
		String applyTo="";
		switch(dataType){
			case "java.lang.String": applyTo="Strings";break;
			case "java.lang.Integer": applyTo="Numbers";break;
			case "java.lang.Float": applyTo="Numbers";break;
			case "java.lang.Double": applyTo="Numbers";break;
			case "java.lang.Short": applyTo="Numbers";break;
			case "java.lang.Boolean": applyTo="Booleans";break;
			case "java.util.Date": applyTo="Dates";break;
			case "java.util.Character": applyTo="Chars";break;
		}
		for (FilterCriteria filter : this.values()) {
			if(applyTo.equals(filter.appliedTo)){
				list.add(filter);
			}
		}
		return list;
	}		

	public FilterCriteria(String appliedTo, int numberOfFields) {
		this.appliedTo=appliedTo;
		this.numberOfFields=numberOfFields;
	}
	
	/*accepted
	This is how I have done it in the past. This way you have i18n support.
	
	gsp
	
	<g:select name="duration" from="${MyEnum.values()}" valueMessagePrefix="ENUM.MyEnum" />
	messages.properties
	
	ENUM.MyEnum.MIN15=15 Minutes
	ENUM.MyEnum.MIN30=30 Minutes
	ENUM.MyEnum.HOUR1=1 Hour
	ENUM.MyEnum.HOUR2=2 Hours
	ENUM.MyEnum.HOUR5=5 Hours
	ENUM.MyEnum.HOUR8=8 Hours
	ENUM.MyEnum.HALFDAY=half day
	ENUM.MyEnum.FULLDAY=full day*/
}
