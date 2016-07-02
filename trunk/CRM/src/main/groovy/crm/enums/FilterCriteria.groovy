package crm.enums

public enum FilterCriteria {
	LIKE_VALUE(DataTypeGroup.STRINGS,1),
	EXACT_VALUE(DataTypeGroup.STRINGS,1),
	BETWEEN_VALUES(DataTypeGroup.NUMBERS,2),
	START_VALUE(DataTypeGroup.NUMBERS,1),
	END_VALUE(DataTypeGroup.NUMBERS,1),
	BETWEEN_DATES(DataTypeGroup.DATES,2),
	START_DATE(DataTypeGroup.DATES,1),
	END_DATE(DataTypeGroup.DATES,1),
	BOOLEAN_VALUE(DataTypeGroup.BOOLEANS,1),
	CHAR_VALUE(DataTypeGroup.CHARACTERS,1)
	
	private final Short numberOfFields;
	private final DataTypeGroup dataTypeGroup;
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

	public FilterCriteria(DataTypeGroup dataTypeGroup, int numberOfFields) {
		this.dataTypeGroup=dataTypeGroup;
		this.numberOfFields=numberOfFields;
	}
}
