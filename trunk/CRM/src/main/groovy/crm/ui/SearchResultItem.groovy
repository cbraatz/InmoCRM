package crm.ui


import crm.CrmDomain
import crm.ManagedProperty
import crm.SearchAttribute
import crm.exception.CRMException
import crm.GUtils

class SearchResultItem {
	private final int RESULT_DISPLAY_VALUE_LENGTH=100;
	String displayValue;
	String objectName;
	String linkTo;
	public SearchResultItem(Object obj, SearchAttribute[] searchAttributes, String searchedKey) throws CRMException{
		for(SearchAttribute sa:searchAttributes){
			String attrValue=obj[sa.attribute];
			int idx=attrValue.indexOf(searchedKey);
			if(idx>=0){
				if(attrValue.length() > searchedKey.length()){
					if(searchedKey.length() > RESULT_DISPLAY_VALUE_LENGTH){
						displayValue=searchedKey.substring(0, RESULT_DISPLAY_VALUE_LENGTH-1);
					}else{
						if(attrValue.length() <= RESULT_DISPLAY_VALUE_LENGTH){
							displayValue=attrValue;
						}else{
							if(idx+searchedKey.length() > RESULT_DISPLAY_VALUE_LENGTH){//si desde el principio hasta donde termina la palabra buscada es mayor al tamaño maximo del resultado
								int i=((RESULT_DISPLAY_VALUE_LENGTH-searchedKey.length())/2);
								if(idx-i >=0){
									displayValue=attrValue.substring(idx-i, idx+i+searchedKey.length());
								}else{
									displayValue=searchedKey.substring(0, RESULT_DISPLAY_VALUE_LENGTH-1);
								}
							}else{
								displayValue=attrValue.substring(0, RESULT_DISPLAY_VALUE_LENGTH-1);
							}
						}
					}
				}else{
					displayValue=attrValue;
				}
				this.objectName=GUtils.getClassNameFromInstance(obj);
				this.linkTo="/"+GUtils.getLowerNameFromString(this.objectName)+"/show/"+obj?.id;
				break;
			}/*else{
				throw new CRMException("El valor buscado: '"+searchedKey+"' no se encuentra en el atributo especificado:'"+attrValue+"'.")
			}*/
		}
		if(null==this.displayValue || null==this.objectName || null==this.linkTo){
			throw new CRMException("El valor buscado: '"+searchedKey+"' no se encuentra en el atributo especificado o este ultimo es nulo.")
		}
	}
	public String getDisplayValue() {
		return displayValue;
	}
	public String getObjectName() {
		return objectName;
	}
	public String getLinkTo() {
		return linkTo;
	}
}
