package crm

import java.util.Date;

class Partner extends CrmDomain{
	String name;
	Integer IDNumber;
	Date birthDate;
	String phone;
	String corporatePhone;
	String emailAddress;
	Country country;
	Gender gender;
	Profession profession;
	PartnerRole partnerRole;
	MaritalStatus maritalStatus;
	Address address;
	Partner invitedBy;//self referenced properties should not have the same name than the Domain. In this case the select drop down is causing LazyInitializationException on Partner/Create when it returns with any validation error from the controller.
	Boolean isActive;
	Boolean isAgent;
	Integer salary;
	Office office;
	static hasMany = [partnersInvitedBy:Partner, crmUsers:CrmUser, bankAccounts:BankAccount, commissionsByProperty:CommissionByProperty/*,tagSelectedValue,CustomFieldSelectedValue*/];
	static constraints = {
		name (blank: false, nullable:false, size:1..100);
		IDNumber(blank:false, nullable:false);
		birthDate(blank:false, nullable:false);
		phone(blank:false, nullable:false, size:1..40);
		corporatePhone(blank:true, nullable:true, size:0..40);
		emailAddress(blank:false, nullable:false, email:true, size:1..50);
		country(nullable:false);
		gender(nullable:true);
		profession(nullable:true);
		partnerRole(nullable:false);
		maritalStatus(nullable:true);
		address(nullable:false);
		invitedBy(nullable:true);
		isActive(nullable:false);
		isAgent(nullable:false);
		salary(blank:true, nullable:true);
		office(nullable:false);
	}
	public Partner(){}
	
	public Partner(def params){
		this.properties = params;
	}
	@Override
	public static String getPluralName(){
		return "partners";
	}
	@Override
	public static SearchAttribute[] searchByAttributes() {
		return [new SearchAttribute("name")];
	}
}
