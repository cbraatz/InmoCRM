package crm.db

import java.util.List;

import crm.ReportDesignerColumn
import crm.Utils;
import crm.exception.CRMException;

class CrmDbTableList {
	private final List<CrmDbTable> tables=new ArrayList<>();
	
	public CrmDbTableList(List<ReportDesignerColumn> reportDesignerColumns) throws CRMException{
		if(null==reportDesignerColumns){
			throw new IllegalArgumentException(message(code: 'default.invalid.paramethers.error', args: ["reportDesignerColumns = null"]));
		}
		reportDesignerColumns.each{
			
			if(null==it.foreignTableName){
				System.out.println(it.toString());
				this.addTableIfNotAlreadyAdded(it.tableName, it.parentTableName, false);
			}else{
				System.err.println(it.toString());
				this.addTableIfNotAlreadyAdded(it.foreignTableName, it.tableName, true);
			}
		}
		/*itera las tablas y remplaza el nombre del parent por el alias
		boolean exists=false;
		for (CrmDbTable tbl : tables){
			if(null != tbl.getParent()){
				exists=false;
				for (CrmDbTable tbl2 : tables){
					if(tbl.getParent().equals(tbl2.getName())){
						tbl.setAlias(tbl2.getAlias());
						exists=true;
						break;
					}
				}
				if(!exists){
					throw new CRMException("Parent Domain object with name '"+tbl.getParent()+ "' was not found in loaded table list.");
				}
			}
		}*/
	}
	
	private boolean addTableIfNotAlreadyAdded(String tableName, String parentName, boolean isForeignTable)throws CRMException{
		boolean exists=false;
		for(CrmDbTable tbl: this.tables){
			if(tbl.getName().equals(tableName)){
				exists=true;
				break;
			}
		}
		if(!exists){
			//cada vez que se encuentra una tabla nueva recorre todas las tablas ya cargadas y en el while crea un nuevo alias y queda ahi hasta que se encuentre uno que no exista, primero toma la primera letra de la tabla el lowerCase, luego la segunda y así, en ultima instance agrega la primera letra concatenado con un shourt UUID
			exists=true;
			int idx=1;
			while(exists==true && idx <= tableName.length()){
				exists=false;
				for (CrmDbTable tbl : this.tables){
					if(tbl.getAlias().equals(tableName.substring(0, idx).toLowerCase())){
						exists=true;
						break;
					}
				}
				if(exists){
					idx++;
				}
			}
			if(this.tables.isEmpty() && isForeignTable==true){
				throw new CRMException("A foreign table can not be the first table added to the list");
			}else{	
				if(idx > tableName.length()){
					this.tables.add(new CrmDbTable(tableName, parentName, tableName.substring(0, 1).toLowerCase()+Utils.getShortUUID(), isForeignTable));
				}else{
					this.tables.add(new CrmDbTable(tableName, parentName, tableName.substring(0, idx).toLowerCase(), isForeignTable));
				}
			}
			//System.out.println("Agregando tabla "+tableName+" con alias="+tables.get(tableName));
		}
	}
	
	//select c.id, m.id, u.name from Concession as c INNER JOIN c.managedProperties as m, CrmUser as u WHERE c.agent = u.id
	//no anda el inner join xx on c.id=m.id ni el where c.id=m.id xq tira error.
	
	public CrmDbTable getTableByName(String tableName){
		for(CrmDbTable tbl: this.tables){
			if(tbl.getName().equals(tableName)){
				return tbl;
			}
		}
		return null;
	}
	public String getTableAliasByName(String tableName) throws CRMException{
		CrmDbTable tbl=this.getTableByName(tableName);
		if(null!=tbl){
			return tbl.getAlias();
		}else{
			throw new CRMException("Table '"+tableName+"' was not found by name in Table's list.");
		}
	}
	/*public String getParentTableAliasByChildTableName(String childTable) throws CRMException{
		CrmDbTable tbl=this.getTableByName(childTable);
		if(null!=tbl){
			tbl=this.getTableByName(tbl.getParent());
			if(null!=tbl){
				return tbl.getAlias();
			}else{
				return null;
			}
		}else{
			throw new CRMException("Table '"+childTable+"' was not found by name in Table's list.");
		}
	}*/
	public List<CrmDbTable> getTables(){
		return this.tables;
	}
}
