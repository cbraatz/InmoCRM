package crm

class ValuePoint extends CrmDomain{
	Float value;//value of the m2 in default currency
	Date date;
	Address address;
	static hasMany = [managedProperties:ManagedProperty];
    static constraints = {
		value(blank:false, nullable:false, min:0F);
		date(blank:false, nullable:false);
    }
}
