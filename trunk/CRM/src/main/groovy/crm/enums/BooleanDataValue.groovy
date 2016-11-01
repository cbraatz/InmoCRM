package crm.enums

import java.util.List;

import crm.exception.CRMException
import crm.enums.BooleanDataValue;
import crm.enums.DataTypeGroup;

public enum BooleanDataValue {
	TRUE(true),
	FALSE(false)
	private final boolean booleanValue;

	public BooleanDataValue(boolean booleanValue) {
		this.booleanValue=booleanValue;
	}
	public static final List<BooleanDataValue> getAllBooleanValues() {
		List<BooleanDataValue> list=new ArrayList<BooleanDataValue>();
		list.add(BooleanDataValue.TRUE);
		list.add(BooleanDataValue.FALSE);
		return list;
	}
}
