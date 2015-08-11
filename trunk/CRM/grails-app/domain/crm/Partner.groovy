package crm

import java.util.Date;

class Partner {
	String name;
	String lastName;
	String IDNumber;
	Date birthDate;
	String phone;
	String emailAddress;
	Gender gender;
	Profession profession;
	PartnerRole partnerRole;
	MaritalStatus maritalStatus;
	Address address;
	Partner invitedBy;
	boolean isActive;
	static hasMany = [invitedPartners:Partner, users:CrmUser, commissions:Commission, bankAccounts:BankAccount/*,realEstateAction,tagSelectedValue,CustomFieldSelectedValue*/];
	
	static constraints = {
		name (blank: false, nullable:false, size:1..50);
		lastName(blank:false, nullable:false, size:1..50);
		IDNumber(blank:false, nullable:false, size:1..40);
		birthDate(blank:false, nullable:false);
		phone(blank:false, nullable:false, size:1..40);
		emailAddress(blank:false, nullable:false, email: true);
		gender(nullable:true);
		profession(nullable:true);
		partnerRole(nullable:false);
		maritalStatus(nullable:true);
		address(nullable:false);
		invitedBy(nullable:true);
		isActive(nullable:false);
	}
}
