package crm.ui

import crm.CrmDomain
import crm.SearchAttribute

class SearchResultItemList {
	private List <SearchResultItem> list= new ArrayList<>();
	
	public SearchResultItemList() {	}

	public addAll(Collection <? extends CrmDomain> searchResultLit, SearchAttribute[] searchAttributes, String searchedKey) {//adds all the results for the same Domain Object
		searchResultLit.each{
			list.add(new SearchResultItem(it, searchAttributes, searchedKey));
		}
	}
	
	public List <SearchResultItem> getSearchResultItems(){
		return list;
	}
	public getSize(){
		return list.size();
	}
}
