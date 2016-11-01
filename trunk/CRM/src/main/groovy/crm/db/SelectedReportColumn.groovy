package crm.db

import crm.enums.DataType;

class SelectedReportColumn {
	String name;
	DataType dataType;
	String displayName;
	public SelectedReportColumn(String name, String dataType, String displayName) {
		this.name=name;
		this.setDataType(dataType);
		this.displayName=displayName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DataType getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = DataType.valueOf(dataType);
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
