package crm.enums

import java.util.List;

public enum SortOrder {
	ASC(true),
	DESC(false)
	
	private final boolean isAscending;

	public SortOrder(boolean value) {
		this.isAscending=value;
	}
	public boolean getValue(){
		return this.value;
	}
	public static final List<SortOrder> getAllSortOrders() {
		List<SortOrder> list=new ArrayList<SortOrder>();
		list.add(SortOrder.ASC);
		list.add(SortOrder.DESC);
		return list;
	}
	/*public static final SortOrder getSortOrderByValue(Boolean value) {
		if(null==value){
			return null;
		}else{
			if (true==value.booleanValue()){
				return SortOrder.ASC;
			}else{
				return SortOrder.DESC;
			}
		}
	}*/
}
