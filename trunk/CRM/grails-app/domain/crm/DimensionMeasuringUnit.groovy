package crm

class DimensionMeasuringUnit {
	String name;
	String nameInPlural;
	String abbreviation;
	String abbreviationInPlural;
	boolean isDefault;
	boolean isArea;
	static hasMany = [buildingTypes:BuildingType, propertyTypes:PropertyType];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..40);
		nameInPlural(blank:false, nullable:false, unique:true, size:1..40);
		abbreviation(blank:false, nullable:false, unique:true, size:1..40);
		abbreviationInPlural(blank:false, nullable:false, unique:true, size:1..40);
		isDefault(nullable:false);
		isArea(nullable:false);
    }
}
