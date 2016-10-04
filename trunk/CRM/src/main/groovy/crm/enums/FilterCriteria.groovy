package crm.enums

public enum FilterCriteria {
	LIKE_VALUE(DataTypeGroup.STRINGS,1," like '#1'"),
	EXACT_VALUE(DataTypeGroup.STRINGS,1," = '#1'"),
	BETWEEN_VALUES(DataTypeGroup.NUMBERS,2," between #1 and #2"),
	START_VALUE(DataTypeGroup.NUMBERS,1," >= #1"),
	END_VALUE(DataTypeGroup.NUMBERS,1," <= #1"),
	BETWEEN_DATES(DataTypeGroup.DATES,2," between '#1' and '#2'"),
	START_DATE(DataTypeGroup.DATES,1," >= '#1'"),
	END_DATE(DataTypeGroup.DATES,1," <= '#1'"),
	BOOLEAN_VALUE(DataTypeGroup.BOOLEANS,1," = #1"),
	CHAR_VALUE(DataTypeGroup.CHARACTERS,1," = '#1'"),
	DOMAIN_CLASS(DataTypeGroup.DOMAIN_CLASSES,1," = #1")
	private final Short numberOfFields;
	private final DataTypeGroup dataTypeGroup;
	private final String whereCondition;
	public static final List<FilterCriteria> getAllFilterCriteriaList(String dataType) {
		List<FilterCriteria> list=new ArrayList<>();
		if (null!=dataType){
			for (FilterCriteria filter : this.values()) {
				if(DataType.getByClassName(dataType).dataTypeGroup == filter.dataTypeGroup){
					list.add(filter);
				}
			}
		}
		return list;
	}		

	public FilterCriteria(DataTypeGroup dataTypeGroup, int numberOfFields, String whereCondition) {
		this.dataTypeGroup=dataTypeGroup;
		this.numberOfFields=numberOfFields;
		this.whereCondition=whereCondition;
	}
	
	public String generateWhereCondition(String column, String value1, String value2){
		String res = column+this.whereCondition.replace("#1", value1);
		if(value2!=null && this.whereCondition.indexOf("#2")>=0){
			return res.replace("#2", value2);
		}else{
			return res;
		}
	}
}
