package crm

import crm.ui.SearchResultItemList

class SearchController {

    def index() { }
	
	def searchResults(String searchKeyWords){
		SearchResultItemList searchResultList=new SearchResultItemList();
		if(searchKeyWords != null){
			def searchDomains =[];
			grailsApplication.getArtefacts("Domain").each{
				SearchAttribute[] searchAttributes=it.getClazz().searchByAttributes();
				if(searchAttributes){//if searchByAttributes is null we do not need to search by this domain class
					StringBuilder query=new StringBuilder()
					query.append("from "+it.fullName+ " where ");
					boolean first=true;
					searchAttributes.each{a->
						
						if(a.isStringAttribute()==true){
							if(first==false){
								query.append(" OR ");
							}else{
								first=false;
							}
							query.append(a.getAttribute()+" like '%"+searchKeyWords+"%'");
						}else{
							if(Utils.isNumber(searchKeyWords)){
								if(first==false){
									query.append(" OR ");
								}else{
									first=false;
								}
								query.append(a.getAttribute()+" = "+searchKeyWords);
							}
						}
					}
					def result=it.getClazz().executeQuery(query.toString());
					if(result.size > 0){
						searchResultList.addAll(result, searchAttributes, searchKeyWords);
					}
				}
			}
		}
		respond searchResultList, model:[resultList:searchResultList, searchKeyWords:searchKeyWords]
	}
}
