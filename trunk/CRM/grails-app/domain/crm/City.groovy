package crm
import crm.Zone;
class City extends CrmDomain{
	String name;
	Department department;
	static hasMany = [addresses:Address, propertyDemands:PropertyDemand, neighborhoods:Neighborhood, zones:Zone/*,Office*/];
	
	static constraints = {
		name(blank: false, nullable: false, unique: 'department', size:1..50);
		department(nullable: false);
    }
	
	@Override
	public static String getPluralName(){
		return "cities";
	}
	
	public Zone getCenterZone() {
		return Zone.findByCityAndIsCenter(this,true);
	}
}
