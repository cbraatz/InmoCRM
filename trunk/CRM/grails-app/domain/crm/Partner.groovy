package crm

import java.util.Date;

class Partner {
	String name;
	Integer IDNumber;
	Date birthDate;
	String phone;
	String corporatePhone;
	String emailAddress;
	String corporateEmail;
	Gender gender;
	Profession profession;
	PartnerRole partnerRole;
	MaritalStatus maritalStatus;
	Address address;
	Partner invitedBy;
	Boolean isActive;
	Boolean isAgent;
	
	
	static hasMany = [invitedPartners:Partner, users:CrmUser, commissions:Commission, bankAccounts:BankAccount, actions:Action, contacts:Contact/*,tagSelectedValue,CustomFieldSelectedValue*/];
	
	static constraints = {
		name (blank: false, nullable:false, size:1..100);
		IDNumber(blank:false, nullable:false);
		birthDate(blank:false, nullable:false);
		phone(blank:false, nullable:false, size:1..40);
		corporatePhone(blank:true, nullable:true, size:0..40);
		emailAddress(blank:false, nullable:false, email: true, size:1..50);
		corporateEmail(blank:true, nullable:true, email: true, size:0..50);
		gender(nullable:true);
		profession(nullable:true);
		partnerRole(nullable:false);
		maritalStatus(nullable:true);
		address(nullable:false);
		invitedBy(nullable:true);
		isActive(nullable:false);
		isAgent(nullable:false);
	}
}
