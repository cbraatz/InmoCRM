
package crm

class Domain extends CrmDomain{
	String name;
	String realPath;
	String realEstateFolder;
	Locale locale;
	static hasMany = [webPages:WebPage]
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:0..50);
		realPath(blank:false, nullable:false, size:0..120);//el path real en el servidor de la carpeta html del dominio
		realEstateFolder(blank:false, nullable:false, size:0..15);//el nombre de la carpeta que contendrá las páginas de inmuebles
		locale(blank:false, nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "domains";
	}
}
