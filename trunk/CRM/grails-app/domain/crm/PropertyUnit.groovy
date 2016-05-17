package crm

class PropertyUnit extends CrmDomain{
	String propertyID1;
	String propertyID2;
	String propertyID3;
	ManagedProperty managedProperty;
    static constraints = {
		propertyID1(blank:false, nullable:false, size:1..20);
		propertyID2(blank:true, nullable:true, size:0..20);
		propertyID3(blank:true, nullable:true, size:0..20);
		managedProperty(nullable:false);
    }
}
