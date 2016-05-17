package crm

class OperationType extends CrmDomain{
	String sale;
	String rent;
	Language language;
    static constraints = {
		sale(blank:false, nullable:false, unique:true, size:1..20);
		rent(blank:false, nullable:false, unique:true, size:1..20);
		language(blank:false, nullable:false, unique:true);
    }
}
