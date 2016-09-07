package crm.db

import crm.ReportDesignerColumn
import crm.Utils;

class CrmDbTableList {
	private final Map<String, String> tables=new HashMap<String, String>();
	private final String START_STRING="FROM ";
	private final String SEPARATOR_STRING=" , ";
	private final String AS_STRING=" AS ";
	public CrmDbTableList(List<ReportDesignerColumn> reportDesignerColumn) {
		boolean exists=false;
		reportDesignerColumn.each{
			exists=false;
			for (Map.Entry<String, String> entry : tables.entrySet()){
				if(entry.getKey().equals(it.tableName)){
					exists=true;
				}
			}
			if(!exists){
				//cada vez que se encuentra una tabla nueva recorre todas las tablas ya cargadas y en el while crea un nuevo alias y queda ahi hasta que se encuentre uno que no exista, primero toma la primera letra de la tabla el lowerCase, luego la segunda y así, en ultima instance agrega la primera letra concatenado con un shourt UUID
				exists=true;
				int idx=1;
				while(exists==true && idx <= it.tableName.length()){
					exists=false;
					for (Map.Entry<String, String> entry : tables.entrySet()){
						if(entry.getValue().equals(it.tableName.substring(0, idx).toLowerCase())){
							exists=true;
						}
					}
					if(exists){
						idx++;
					}
				}
				if(idx > it.tableName.length()){
					tables.put(it.tableName,it.tableName.substring(0, 1).toLowerCase()+Utils.getShortUUID());
				}else{
					tables.put(it.tableName,it.tableName.substring(0, idx).toLowerCase());
				}
				System.out.println("Agregando tabla "+it.tableName+" con alias="+tables.get(it.tableName));
			}
		}
	}
	public String getFromClause(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.START_STRING);
		boolean first=true;
		for (Map.Entry<String, String> entry : tables.entrySet()){
			if(true==first){
				sb.append(entry.getKey()+this.AS_STRING+entry.getKey());
				first=false;
			}else{
				sb.append(this.SEPARATOR_STRING+entry.getKey()+this.AS_STRING+entry.getKey());
			}
		}
		return sb.toString();
	}
	/*public static String getAlias(){
		List <String> tbs=["Ca","Ca","Ca","Algomas","Ca","Ca2"];
		List <String> aliases=new ArrayList<String>();
		tbs.each{
			boolean exists=true;
			int idx=1;
			while(exists==true && idx <= it.length()){
				exists=false;
				for (String al : aliases){
					if(al.equals(it.substring(0, idx).toLowerCase())){
						exists=true;
					}
				}
				if(exists){
					idx++;
				}
			}
			if(idx > it.length()){
				String aa=it.substring(0, 1).toLowerCase()+Utils.getShortUUID()
				aliases.add(aa);
				System.out.println(aa);
			}else{
				System.out.println(it.substring(0, idx).toLowerCase());
				aliases.add(it.substring(0, idx).toLowerCase());
			}
		}
	}*/
	
	public Map getTables(){
		return this.tables;
	}
	public String getTableAliasByTableName(String tableName){
		return tables.get(tableName);
	}
}
