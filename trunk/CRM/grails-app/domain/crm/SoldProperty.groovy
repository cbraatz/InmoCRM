package crm

class SoldProperty {
	Double sellPrice;
	Double commissionAmount;
	Date date;
	Boolean isConfirmed;
	Client client;
	CrmUser crmUser;
	Currency currency;
	PropertyDemand propertyDemand;//si la venta se realizó a través de una propertyDemand
	ManagedProperty managedProperty;
    static constraints = {
		sellPrice(blank:false, nullable:false, min:0D);
		commissionAmount(blank:false, nullable:false, min:0D);
		date(blank:false, nullable:false);
		isConfirmed(blank:false, nullable:false);
		client(nullable:false);
		crmUser(nullable:false);
		currency(nullable:false);
		propertyDemand(nullable:true);
		managedProperty(nullable:false);
    }
}
