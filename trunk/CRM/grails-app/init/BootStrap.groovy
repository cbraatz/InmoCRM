import crm.enums.income.RelatedDomain;

import java.util.Date;

import grails.util.Environment;
import crm.*;
import crm.commands.ContextCrmActionsByCategoryCommand

import java.text.SimpleDateFormat

class BootStrap {

    def init = { servletContext ->
		println("Starting init......................."+Environment.current);
		File tempDir=new File("temp");
		File uploadsDir=new File("uploads");
		if(!tempDir.exists()){
			if(tempDir.mkdirs()==false){
				crm.CrmLogger.logError(this.getClass(), "TempDir was not created.");
			}
		}
		if(!uploadsDir.exists()){
			if(uploadsDir.mkdirs()==false){
				crm.CrmLogger.logError(this.getClass(), "UploadsDir was not created.");
			}
		}
		
		//if(Environment.current.equals(Environment.DEVELOPMENT)){
		if(CrmConfig.count()==0){
			addCommonData();
			if(Environment.current.equals(Environment.DEVELOPMENT)){
				addDevelopmentData();
			}
			if(Environment.current.equals(Environment.TEST)){
				addTestData();
			}
		}
		//}
		println("Finishing init......................");
		crm.CrmLogger.logWarning(this.getClass(), "Finishing init......................");
    }
    def destroy = {
    }
	
	private void addTestData(){
		//Domain
		this.saveObj(new Domain(name: "inmueblespy.com", realPath:"/var/www/html", locale:Locale.findBySymbol("es_PY"), realEstateFolder:"inmuebles"));
		this.saveObj(new Domain(name: "inmuebles-paraguay.com.py", realPath:"/var/www/html", locale:Locale.findBySymbol("es_PY"), realEstateFolder:"inmuebles"));
		this.saveObj(new Domain(name: "inmobilien-paraguay.com.de", realPath:"/var/www/html", locale:Locale.findBySymbol("de_GE"), realEstateFolder:"inmobilien"));
	}
	private void addDevelopmentData(){
		//Domain
		this.saveObj(new Domain(name: "inmueblespy.com", realPath:"C:/Users/claus/Documents/TRABAJO/inmo/cb_rep/web_trunk/HTML/html", locale:Locale.findBySymbol("es_PY"), realEstateFolder:"inmuebles"));
		this.saveObj(new Domain(name: "inmuebles-paraguay.com.py", realPath:"C:/Users/claus/Documents/TRABAJO/inmo/cb_rep/web_trunk/HTML/html", locale:Locale.findBySymbol("es_PY"), realEstateFolder:"inmuebles"));
		this.saveObj(new Domain(name: "inmobilien-paraguay.com.de", realPath:"C:/Users/claus/Documents/TRABAJO/inmo/cb_rep/web_trunk/HTML/html", locale:Locale.findBySymbol("de_GE"), realEstateFolder:"inmobilien"));
	}
	private void addCommonData(){
		//Language
		this.saveObj(new Language(name: "Español", symbol:"es", prepositionOfPlace:"en"));
		this.saveObj(new Language(name: "English", symbol:"en", prepositionOfPlace:"in"));
		this.saveObj(new Language(name: "Português", symbol:"pt", prepositionOfPlace:"em"));
		this.saveObj(new Language(name: "Deutsch", symbol:"de", prepositionOfPlace:"in"));
		
		//Bank
		this.saveObj(new Bank(name: "Banco Atlas"));
		this.saveObj(new Bank(name: "Banco Continental"));
		this.saveObj(new Bank(name: "Banco Familiar"));
		this.saveObj(new Bank(name: "Banco Itapúa"));
		this.saveObj(new Bank(name: "Banco Itaú"));
		this.saveObj(new Bank(name: "Banco Regional"));
		this.saveObj(new Bank(name: "Banco Visión"));
		
		//country
		this.saveObj(new Country(name: "Paraguay"));
		this.saveObj(new Country(name: "Argentina"));		
		this.saveObj(new Country(name: "Brasil"));		
		this.saveObj(new Country(name: "Uruguay"));		
		this.saveObj(new Country(name: "Deutschland"));
		this.saveObj(new Country(name: "United States"));
		
		//department
		this.saveObj(new Department(name: "Itapúa", country: Country.findByName("Paraguay")));		
		this.saveObj(new Department(name: "Alto Paraná", country: Country.findByName("Paraguay")));		
		
		//city
		this.saveObj(new City(name: "Obligado", department: Department.findByName("Itapúa")));		
		this.saveObj(new City(name: "Encarnación", department: Department.findByName("Itapúa")));		
		this.saveObj(new City(name: "Hohenau", department: Department.findByName("Itapúa")));
		
		//Zone
		this.saveObj(new Zone(name: "Obligado Centro", description: "Obligado Centro", isCenter:true, city:City.findByName("Obligado")));
		this.saveObj(new Zone(name: "Encarnación Centro", description: "Encarnación Centro", isCenter:true, city:City.findByName("Encarnación")));
		this.saveObj(new Zone(name: "Hohenau Centro", description: "Hohenau Centro", isCenter:true, city:City.findByName("Hohenau")));
		
		//MaritalStatus
		this.saveObj(new MaritalStatus(name: "Soltero/a"));		
		this.saveObj(new MaritalStatus(name: "Casado/a"));
		
		//Gender
		this.saveObj(new Gender(name: "Masculino"));		
		this.saveObj(new Gender(name: "Femenino"));
		
		//profession
		this.saveObj(new Profession(name: "Agricultor"));		
		this.saveObj(new Profession(name: "Ingeniero Agrónomo"));		
		this.saveObj(new Profession(name: "Agente de Seguros"));
		
		City ct=City.findByName("Obligado");
		//neighborhood
		this.saveObj(new Neighborhood(name:"Centro", description:"test", city: ct, zone: ct.getCenterZone()));		
		this.saveObj(new Neighborhood(name:"Barrio Los Colonos", description:"test", city: ct, zone: ct.getCenterZone()));		
		this.saveObj(new Neighborhood(name:"Los Cedrales", description:"test", city: ct, zone: ct.getCenterZone()));
		
		
		
		//Address
		this.saveObj(new Address(streetOne:"Calle 1", streetTwo:"Calle 2", number:"1568", addressLine:"Calle 1 1522 c/ Calle 2", reference:"Frente al Centro de Salud.", description:"Casa de rejas blancas y patio amplio.", code:"6000", latitude:Double.parseDouble("-27.054249533179895"), longitude:Double.parseDouble("-55.6293557718102"), homePhone:"0988541258", city: ct, neighborhood: Neighborhood.findByName("Centro"), zone: ct.getCenterZone()));
		
		//PartnerRole
		this.saveObj(new PartnerRole(name: "Atalaya", isEmployee:false, description:"Atalaya o vendedor externo."));
		this.saveObj(new PartnerRole(name: "Agente", isEmployee:true, description:"Agente o vendedor interno."));
		this.saveObj(new PartnerRole(name: "Gerente de Venta", isEmployee:true, description:"Gerente de ventas."));
		
		//Partner
		this.saveObj(new Partner(name: "Default", emailAddress: "default_partner@test.com", gender: Gender.findByName("Masculino"), phone:"0", IDNumber:"0", birthDate: new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("01/01/1900"), country: Country.findByName("Paraguay"), address:Address.findByAddressLine("Calle 1 1522 c/ Calle 2"), isActive:true, partnerRole:PartnerRole.findByName("Agente"), isAgent:true));
		this.saveObj(new Partner(name: "Partner 2", emailAddress: "default_partner2@test.com", gender: Gender.findByName("Masculino"), phone:"0", IDNumber:"1", birthDate: new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("01/01/1900"), country: Country.findByName("Paraguay"), address:Address.findByAddressLine("Calle 1 1522 c/ Calle 2"), isActive:true, partnerRole:PartnerRole.findByName("Atalaya"), isAgent:false));
		this.saveObj(new Partner(name: "Partner 3", emailAddress: "default_partner3@test.com", gender: Gender.findByName("Masculino"), phone:"0", IDNumber:"2", birthDate: new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("01/01/1900"), country: Country.findByName("Paraguay"), address:Address.findByAddressLine("Calle 1 1522 c/ Calle 2"), isActive:true, partnerRole:PartnerRole.findByName("Agente"), isAgent:true));
		//CrmUser
		def defUsr=new CrmUser(partner: Partner.findByName("Default"), login:"test", password:CrmUser.encodePassword("test"), emailAddress: "default_user@test.com", isActive:true, hasAccess:true, isAdmin:true);
		this.saveObj(defUsr);
		def testUsr=new CrmUser(partner: Partner.findByName("Partner 2"), login:"test2", password:CrmUser.encodePassword("test"), emailAddress: "test2_user@test.com", isActive:true, hasAccess:true, isAdmin:false);
		this.saveObj(testUsr);
		def testUsr3=new CrmUser(partner: Partner.findByName("Partner 3"), login:"test3", password:CrmUser.encodePassword("test"), emailAddress: "test3_user@test.com", isActive:true, hasAccess:true, isAdmin:true);
		this.saveObj(testUsr3);
		//ContextPermissionCategory
		ContextPermissionCategory cpcat=new ContextPermissionCategory(name:"Todos", isAll:true, isNone:false);
		this.saveObj(cpcat);
		/*crm.enums.software.CrmController.values().each{
			for(crm.enums.software.CrmAction a:it.actions){
				this.saveObj(new CrmActionByContextCategory(crmController:it.name(), crmAction:a.name(), contextPermissionCategory:cpcat));
			}
		}*/
		ContextPermissionCategory cpcat2=new ContextPermissionCategory(name:"Ninguno", isAll:false, isNone:true);
		this.saveObj(cpcat2);
		//ContextCrmActionsByCategoryCommand x=new ContextCrmActionsByCategoryCommand(cpcat);
		
		//UserGroup
		/*UserGroup adminGroup=new UserGroup(name:"Test_Group", isAdmin:true, contextPermissionCategory:ContextPermissionCategory.findByIsAll(true));
		adminGroup.addToCrmUsers(defUsr);
		this.saveObj(adminGroup);
		defUsr.addToUserGroups(adminGroup);
		this.saveObj(defUsr);
		
		UserGroup testGroup=new UserGroup(name:"Test_Group", isAdmin:false, contextPermissionCategory:ContextPermissionCategory.findByIsAll(true));
		testGroup.addToCrmUsers(testUsr);
		this.saveObj(testGroup);
		testUsr.addToUserGroups(testGroup);
		this.saveObj(testUsr);*/
		
		//ReportFolder
		this.saveObj(new ReportFolder(name:"Reportes Públicos", crmUser: defUsr, isPublic:new Boolean("true")));
		
		//ClientCategory
		this.saveObj(new ClientCategory(name: "Pequeño", description:"Clientes con sueldo mínimo o menos."));		
		this.saveObj(new ClientCategory(name: "Pequeño-Medio", description:"Clientes con más de sueldo mínimo, hasta 3 sueldos mínimo."));
		
		//Client
		this.saveObj(new Client(name: "Cliente 1", description: "test", IDNumber:"0", birthDate: new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("15/06/1980"), phone:"0985000000", phone2:"0985000001", notificationPhone:"0985000002", emailAddress: "cliente1@test.com", country: Country.findByName("Paraguay"), maritalStatus: MaritalStatus.findByName("Soltero/a"), profession: Profession.findByName("Agricultor"), gender: Gender.findByName("Masculino"), address:Address.findByAddressLine("Calle 1 1522 c/ Calle 2"), category:ClientCategory.findByName("Pequeño"), crmUser: CrmUser.findByLogin("test"), isActive:true, readsEmail:true, readsSms:true, receiveNotifications:true, isProspectiveClient:false));
		
		//Vendor
		this.saveObj(new Vendor(name: "Cooperativa Colonias Unidas", description: "Cooperativa", phone:"071720512", emailAddress: "ccu@test.com", TIN:"80090084-2"));
		
		//TaxRate
		this.saveObj(new TaxRate(name: "10 %", percentage:10));		
		this.saveObj(new TaxRate(name: "5 %", percentage:5));		
		this.saveObj(new TaxRate(name: "0 %", percentage:0));
		
		//BuildingFeature
		this.saveObj(new BuildingFeature(name: "Acondicionador de aire", plural:"Acondicionadores de aire", description: "Cuenta con acondicionador de aire (antiguo o split)", hasValue:true));		
		this.saveObj(new BuildingFeature(name: "Cocina con amoblado básico", plural:"Cocinas con amoblado básico", description: "Cuenta con los muebles básicos de cocina", hasValue:false));		
		this.saveObj(new BuildingFeature(name: "Amoblamiento completo", plural:"Amoblamiento completo", description: "Cuenta con amoblamiento completo de todo el inmueble", hasValue:false));
		
		//PropertyFeature
		this.saveObj(new PropertyFeature(name: "Agua potable", plural:"Agua potable", description: "Cuenta servicio de agua potable", hasValue:false));
		this.saveObj(new PropertyFeature(name: "Ande", plural:"Ande", description: "Cuenta con servicio de Ande", hasValue:false));
		this.saveObj(new PropertyFeature(name: "Baño", plural:"Baños", description: "Cantidad de baños", defaultWebIcon:"b-icon b-icon--bathrooms", hasValue:true));
		this.saveObj(new PropertyFeature(name: "Dormitorio", plural:"Dormitorios", description: "Cantidad de dormitorios", defaultWebIcon: "b-icon b-icon--bed", hasValue:true));
		this.saveObj(new PropertyFeature(name: "Garage", plural:"Garages", description: "Espacios para vehículos", defaultWebIcon: "b-icon b-icon--garage", hasValue:true));
		
		//DimensionMeasuringUnit
		this.saveObj(new DimensionMeasuringUnit(name: "Metro", nameInPlural:"Metros", abbreviation:"m", abbreviationInPlural:"m", isDefault:true, isArea:false));		
		this.saveObj(new DimensionMeasuringUnit(name: "Metro cuadrado", nameInPlural:"Metros cuadrados", abbreviation:"m2", abbreviationInPlural:"m2", isDefault:true, isArea:true));		
		this.saveObj(new DimensionMeasuringUnit(name: "Hectárea", nameInPlural:"Hectáreas", abbreviation:"ha", abbreviationInPlural:"ha", isDefault:true, isArea:true));
		
		//BuildingType
		this.saveObj(new BuildingType(name: "Casa", description:"Inmueble independiente y de uso habitacional", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("m2")));		
		this.saveObj(new BuildingType(name: "Edificio de Departamentos", description:"Inmueble independiente que está dividido en departamentos", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("m2")));		
		this.saveObj(new BuildingType(name: "Piso", description:"Un piso de un edificio", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("m2"), language:Language.findByName("Español")));		
		this.saveObj(new BuildingType(name: "Local Comercial", description:"Inmueble en planta baja destinado a actividades comerciales", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("m2")));		
		this.saveObj(new BuildingType(name: "Depósito", description:"Inmueble destinado a guardar/depositar bienes", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("m2")));
		
		//BuildingFeatureByBuildingType
		//casa
		  BuildingType bt=BuildingType.findByName("Casa");
			//Acondicionador de aire
			BuildingFeature bf=BuildingFeature.findByName("Acondicionador de aire");
			this.saveObj(bt.addToBuildingFeatures(bf));
			this.saveObj(bf.addToBuildingTypes(bt));
			//Cocina con amoblado básico
			bf=BuildingFeature.findByName("Cocina con amoblado básico");
			this.saveObj(bt.addToBuildingFeatures(bf));
			this.saveObj(bf.addToBuildingTypes(bt));
			//Amoblamiento completo
			bf=BuildingFeature.findByName("Amoblamiento completo");
			this.saveObj(bt.addToBuildingFeatures(bf));
			this.saveObj(bf.addToBuildingTypes(bt));
		//Edificio de Departamentos
			bt=BuildingType.findByName("Edificio de Departamentos");
			  //Acondicionador de aire
			  bf=BuildingFeature.findByName("Acondicionador de aire");
			  this.saveObj(bt.addToBuildingFeatures(bf));
			  this.saveObj(bf.addToBuildingTypes(bt));
			  //Cocina con amoblado básico
			  bf=BuildingFeature.findByName("Cocina con amoblado básico");
			  this.saveObj(bt.addToBuildingFeatures(bf));
			  this.saveObj(bf.addToBuildingTypes(bt));
			  //Amoblamiento completo
			  bf=BuildingFeature.findByName("Amoblamiento completo");
			  this.saveObj(bt.addToBuildingFeatures(bf));
			  this.saveObj(bf.addToBuildingTypes(bt));
		//Piso
			  bt=BuildingType.findByName("Piso");
				//Acondicionador de aire
				bf=BuildingFeature.findByName("Acondicionador de aire");
				this.saveObj(bt.addToBuildingFeatures(bf));
				this.saveObj(bf.addToBuildingTypes(bt));
				//Cocina con amoblado básico
				bf=BuildingFeature.findByName("Cocina con amoblado básico");
				this.saveObj(bt.addToBuildingFeatures(bf));
				this.saveObj(bf.addToBuildingTypes(bt));
				//Amoblamiento completo
				bf=BuildingFeature.findByName("Amoblamiento completo");
				this.saveObj(bt.addToBuildingFeatures(bf));
				this.saveObj(bf.addToBuildingTypes(bt));
		//Usage
		this.saveObj(new Usage(name: "Cultivo", description:"Destinado al cultivo de rubros permitidos (Soja, trigo, maíz, sorgo, avena, etc.)"));		
		this.saveObj(new Usage(name: "Ganadería", description:"Destinado a la producción animal."));
		this.saveObj(new Usage(name: "Uso habitacional", description:"Destinado a la construcción de viviendas."));
		this.saveObj(new Usage(name: "Uso comercial", description:"Destinado a la construcción de locales comerciales."));
		
		//Locale
		this.saveObj(new Locale(name: "Español-Paraguay", isDefault:true, symbol:"es_PY", language:Language.findByName("Español"), country:Country.findByName("Paraguay")));
		this.saveObj(new Locale(name: "Alemán-Alemania", isDefault:false, symbol:"de_GE", language:Language.findByName("Deutsch"), country:Country.findByName("Deutschland")));
		//Domain 
		/*this.saveObj(new Domain(name: "inmueblespy.com", realPath:"/var/www/html", locale:Locale.findBySymbol("es_PY"), realEstateFolder:"inmuebles"));
		this.saveObj(new Domain(name: "inmuebles-paraguay.com.py", realPath:"D:/TRABAJOS/crm/git_projects/root/web_trunk/HTML/html", locale:Locale.findBySymbol("es_PY"), realEstateFolder:"inmuebles"));
		this.saveObj(new Domain(name: "inmobilien-paraguay.com.de", realPath:"/var/www/html", locale:Locale.findBySymbol("de_GE"), realEstateFolder:"inmobilien"));
		added in development and test data*/
		//OperationType
		this.saveObj(new OperationType(sale:"En Venta",rent:"En Alquiler",language:Language.findByName("Español")));
		this.saveObj(new OperationType(sale:"Zum Verkauf",rent:"Zu vermieten",language:Language.findByName("Deutsch")));
		
		//BroadcastMedia
		this.saveObj(new BroadcastMedia(name: "Boca a boca", adSummaryMaxLength:0, adTextMaxLength:0));
		this.saveObj(new BroadcastMedia(name: "Inmobiliaria", adSummaryMaxLength:0, adTextMaxLength:0));
		this.saveObj(new BroadcastMedia(name: "Facebook", adSummaryMaxLength:0, adTextMaxLength:0, urlToSite:"https://es-la.facebook.com/"));		
		this.saveObj(new BroadcastMedia(name: "Clasipar", adSummaryMaxLength:0, adTextMaxLength:0, urlToSite:"http://clasipar.paraguay.com/", Country: Country.findByName("Paraguay")));		
		this.saveObj(new BroadcastMedia(name: "Quebarato", adSummaryMaxLength:0, adTextMaxLength:0, urlToSite:"http://www.quebarato.com.py/", Country: Country.findByName("Paraguay")));		
		this.saveObj(new BroadcastMedia(name: "Boletín Coop. Colonias Unidas", adSummaryMaxLength:0, adTextMaxLength:0, Country: Country.findByName("Paraguay")));
		
		//BuildingCondition
		this.saveObj(new BuildingCondition(name: "Nuevo"));		
		this.saveObj(new BuildingCondition(name: "Semi nuevo"));		
		this.saveObj(new BuildingCondition(name: "Buen estado"));		
		this.saveObj(new BuildingCondition(name: "Refaccionado"));		
		this.saveObj(new BuildingCondition(name: "Necesita refacciones menores"));		
		this.saveObj(new BuildingCondition(name: "Necesita refacciones importantes"));
		
		//PriorityLevel
		this.saveObj(new PriorityLevel(name: "Máxima", color: "red", level:1));		
		this.saveObj(new PriorityLevel(name: "Alta", color: "orange", level:2));		
		this.saveObj(new PriorityLevel(name: "Media", color: "yellow", level:3));		
		this.saveObj(new PriorityLevel(name: "Baja", color: "green", level:4));	
		
		//TaskStatus
		this.saveObj(new TaskStatus(name: "Nueva", isNew:true, isClosed:false));
		this.saveObj(new TaskStatus(name: "Calendarizada", isNew:false, isClosed:false));
		this.saveObj(new TaskStatus(name: "En Proceso", isNew:false, isClosed:false));
		this.saveObj(new TaskStatus(name: "Terminada", isNew:false, isClosed:true));
		this.saveObj(new TaskStatus(name: "Cancelada", isNew:false, isClosed:true));
		
		//NotificationMethod
		this.saveObj(new NotificationMethod(name: "Email", isEmail:true, isSms:false, isInternetMessage1:false));
		this.saveObj(new NotificationMethod(name: "Mensaje de Texto", isEmail:false, isSms:true, isInternetMessage1:false));
		this.saveObj(new NotificationMethod(name: "WhatsApp", isEmail:false, isSms:false, isInternetMessage1:true));
		
		
		//InterestLevel
		this.saveObj(new InterestLevel(name: "Máximo", color: "red", level:1));		
		this.saveObj(new InterestLevel(name: "Muy Interesado", color: "orange", level:2));		
		this.saveObj(new InterestLevel(name: "Interesado", color: "yellow", level:3));		
		this.saveObj(new InterestLevel(name: "Poco Interesado", color: "green", level:4));
		
		//Currency
		this.saveObj(new Currency(name: "Guaraní", plural:"Guaraníes", symbol:"Gs", hasDecimals:false, isDefault:true, isInvoicingCurrency:true, country: Country.findByName("Paraguay")));		
		this.saveObj(new Currency(name: "Dólar", plural:"Dólares", symbol:"USS", hasDecimals:true, isDefault:false, isInvoicingCurrency:true, country: Country.findByName("Estados Unidos")));
		this.saveObj(new Currency(name: "Euro", plural:"Euros", symbol:"€", hasDecimals:true, isDefault:false, isInvoicingCurrency:false));
		
		//DefaultDateRage
		this.saveObj(new DefaultDateRange(startDate:new Date(), endDate:null, taxRate:TaxRate.findByName("10 %"), currency:null));
		this.saveObj(new DefaultDateRange(startDate:new Date(), endDate:null, taxRate:null, currency:Currency.findByName("Guaraní")));
		
		//CurrencyExchange
		this.saveObj(new CurrencyExchange(date:new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("16/09/2015"), buy:new Double("5000"), sell:new Double("5050"), sourceCurrency:Currency.findBySymbol("Gs"), targetCurrency:Currency.findBySymbol("USS")));
		this.saveObj(new CurrencyExchange(date:new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("13/09/2015"), buy:new Double("5100"), sell:new Double("5150"), sourceCurrency:Currency.findBySymbol("Gs"), targetCurrency:Currency.findBySymbol("USS")));
		this.saveObj(new CurrencyExchange(date:new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("14/09/2015"), buy:new Double("5200"), sell:new Double("5250"), sourceCurrency:Currency.findBySymbol("Gs"), targetCurrency:Currency.findBySymbol("USS")));
		this.saveObj(new CurrencyExchange(date:new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("15/09/2015"), buy:new Double("5300"), sell:new Double("5350"), sourceCurrency:Currency.findBySymbol("Gs"), targetCurrency:Currency.findBySymbol("USS")));
		this.saveObj(new CurrencyExchange(date:new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("15/09/2015"), buy:new Double("6000"), sell:new Double("6050"), sourceCurrency:Currency.findBySymbol("Gs"), targetCurrency:Currency.findByName("Euro")));
		
		//PaymentMethod
		this.saveObj(new PaymentMethod(name: "Efectivo", discountPercentage:0, isCash:true, hasStartDate:false, hasEndDate:false, hasBank:false));
		this.saveObj(new PaymentMethod(name: "Cheque", discountPercentage:0, isCash:false, hasStartDate:true, hasEndDate:false, hasBank:true));
		this.saveObj(new PaymentMethod(name: "Pagaré", discountPercentage:0, isCash:false, hasStartDate:true, hasEndDate:false, hasBank:false));
		this.saveObj(new PaymentMethod(name: "Tarjeta de Crédito", discountPercentage:3, isCash:false, hasStartDate:false, hasEndDate:false, hasBank:true));
		
		//DemandStatus
		this.saveObj(new DemandStatus(name: "Nueva", isNew:true, isClosed:false));
		this.saveObj(new DemandStatus(name: "Necesita más información", isNew:false, isClosed:false));
		this.saveObj(new DemandStatus(name: "Verificada", isNew:false, isClosed:false));
		this.saveObj(new DemandStatus(name: "Listo para cerrar", isNew:false, isClosed:false));
		this.saveObj(new DemandStatus(name: "Cerrado", isNew:false, isClosed:true));
		
		//ContractType
		this.saveObj(new ContractType(name: "No Eclusivo", isExclusive:false, description:"Contrato no exclusivo.", commissionPercentage:4, billingDefaultDescription:"Intermediación de venta de inmueble. Contrato no exclusivo."));
		this.saveObj(new ContractType(name: "Eclusivo", isExclusive:true, description:"Contrato exclusivo.", commissionPercentage:3, billingDefaultDescription:"Intermediación de venta de inmueble. Contrato exclusivo."));
		
		
		//PropertyType
		this.saveObj(new PropertyType(name:"Sitio", plural:"Sitios", internalID:"SITIO", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("m2"), description:"Lote de tierra generalmente urbano.", language:Language.findByName("Español")));
		this.saveObj(new PropertyType(name:"Terreno agrícola", plural:"Terrenos agrícolas", internalID:"TERRENO_AGRICOLA", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("ha"), description:"Lote de tierra generalmente rural destinado a la producción agrícola", language:Language.findByName("Español")));
		this.saveObj(new PropertyType(name:"Estancia", plural:"Estancias", internalID:"ESTANCIA", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("ha"), description:"Lote de tierra generalmente rural destinado a la producción animal.", language:Language.findByName("Español")));
		
		
		//Concession 1
		Concession concession1=new Concession(isNegotiable:false, startDate:new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("15/06/2015"), endDate: new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("15/06/2016"), description:"Ninguna", //propertyDemand:PropertyDemand,
											  publishInMLS:false, publishInPortals:false, barter:"NO", financing:"NO", client:Client.findByName("Cliente 1"), crmUser:CrmUser.findByLogin("test"), isActive:true, isForRent:false, totalPrice:9600, totalCommission:9600);
		//concession1.addToManagedProperties(managedProperty1);
		//this.saveObj(concession1);
		//managedProperty1.addToConcessions(concession1);
		//this.saveObj(managedProperty1);
		//Concession 2
		Concession concession2=new Concession(isNegotiable:false, startDate:new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("20/06/2015"), endDate: new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("20/06/2016"), description:"Ninguna", //propertyDemand:PropertyDemand,
			publishInMLS:false, publishInPortals:false, barter:"NO", financing:"NO", client:Client.findByName("Cliente 1"), crmUser:CrmUser.findByLogin("test"), isActive:true, isForRent:false, totalPrice:55600, totalCommission:55600);
		//concession2.addToManagedProperties(managedProperty2);
		//this.saveObj(concession2);
		//managedProperty2.addToConcessions(concession2);
		//this.saveObj(managedProperty2);
		//ManagedProperty
		
		ManagedProperty managedProperty1=new ManagedProperty(concession:concession1, title:"Terreno de 1200m2 en Obligado Centro, al lado del Centro de Salud", propertyDescription:"Terreno con vereda y árboles frutales", measures:"20m x 60m", publicAddress:"Obligado Centro, cerca del Centro de Salud", publicCashPrice:"240.000 USS", price:240000, currency:Currency.findBySymbol("USS"), value:250000,
			clientInitialPrice:240000, addedDate:new Date(), placedBillboards:1, area:1200,excess:2, address:Address.findByAddressLine("Calle 1 1522 c/ Calle 2"), propertyType:PropertyType.findByName("Sitio"), valueDegree:1, commissionAmount:9600);
		this.saveObj(managedProperty1);
		ManagedProperty managedProperty2=new ManagedProperty(concession:concession2, title:"Terreno de 2200m2 en Obligado Centro", propertyDescription:"Terreno grande en Obligado", measures:"30m x 100m", publicAddress:"Obligado Centro", publicCashPrice:"340.000 USS", price:340000, currency:Currency.findBySymbol("USS"), value:350000,
			clientInitialPrice:340000, addedDate:new Date(), placedBillboards:1, area:2200,excess:0, address:Address.findByAddressLine("Calle 1 1522 c/ Calle 2"), propertyType:PropertyType.findByName("Sitio"), valueDegree:1, commissionAmount:1500);
		this.saveObj(managedProperty2);
		System.out.println(managedProperty1.id);
		
		//PropertyUsage
		this.saveObj(new PropertyUsage(usage:Usage.findByName("Uso habitacional"), quantity:100, isQuantityInPercentage:true, isCurrentUsage:false));
		this.saveObj(new PropertyUsage(usage:Usage.findByName("Uso comercial"), quantity:100, isQuantityInPercentage:true, isCurrentUsage:false));
		
		//Building
		this.saveObj(new Building(builtSize:500, builtYear:2013, managedProperty:ManagedProperty.findByTitle("Terreno de 1200m2 en Obligado Centro, al lado del Centro de Salud"), buildingType:BuildingType.findByName("Casa"), buildingCondition:BuildingCondition.findByName("Semi nuevo"), buildingDescription:"Es una casa estilo moderno, sin goteras y ladrillo visto."));
		
		//Contract
		this.saveObj(new Contract(concession:concession1, isCurrentContract:true, internalID:Utils.getUUID(), startDate:new Date(), endDate:new Date(), contractType:ContractType.findByDescription("Contrato exclusivo.")));
		
		//UploadedImage
		this.saveObj(new UploadedImage(description:"test image", fileName:"image1.jpg", isMainImage:true, addToWeb:true, sizeInKB:1221 , managedProperty:managedProperty1));
		this.saveObj(new UploadedImage(description:"test image", fileName:"image2.jpg", isMainImage:true, addToWeb:true, sizeInKB:1221 , managedProperty:managedProperty1));
		//managedProperty1.createOrUpdateWebPage();
		//InterestType
		this.saveObj(new InterestType(name:"Simple", description:"Interés Simple", internalID:"1", isSimpleInterest:true));
		
		//ExpenseType
		this.saveObj(new ExpenseType(name:"Comisión por Intermediación", description:"Pago de comisiones por servicios prestados por Agentes Inmobiliarios", internalID:"1", selfInvoiceDefaultDescription:"Honorarios por servicios de intermediación Inmobiliaria", taxRate:TaxRate.findByName("10 %"), isCompanyExpense:true));//mejorar busqueda o no incluir esto por defecto
		this.saveObj(new ExpenseType(name:"Comisión Atalaya", description:"Pago de comisiones por servicios prestados por Agentes Inmobiliarios", internalID:"2", selfInvoiceDefaultDescription:"Honorarios por servicios de intermediación Inmobiliaria", taxRate:TaxRate.findByName("10 %"), isCompanyExpense:true));
		
		//CommissionType
		this.saveObj(new CommissionType(name:"Comisión de Venta de Inmueble", description:"Comisión por cerrar una venta de un inmueble", internalID: CommissionType.getPartnerSellCommissionTypeInternalID(), selfInvoiceDefaultDescription:"Honorarios por servicios de intermediación Inmobiliaria", expenseType:ExpenseType.findByInternalID("1"),appliedToSalesByOffice:false,appliedToAllSales:false));
		this.saveObj(new CommissionType(name:"Comisión de Captación de Inmueble", description:"Comisión por captación de un inmueble vendido", internalID: CommissionType.getPartnerCatchCommissionTypeInternalID(), selfInvoiceDefaultDescription:"Honorarios por servicios de intermediación Inmobiliaria", expenseType:ExpenseType.findByInternalID("1"),appliedToSalesByOffice:false,appliedToAllSales:false));//usado el internalID en IncomeController
		this.saveObj(new CommissionType(name:"Comisión de Venta de Inmueble con atalaya", description:"Comisión por cerrar una venta de un inmueble con agente externo", internalID: CommissionType.getPartnerWithAtalayaSellCommissionTypeInternalID(), selfInvoiceDefaultDescription:"Honorarios por servicios de intermediación Inmobiliaria", expenseType:ExpenseType.findByInternalID("1"),appliedToSalesByOffice:false,appliedToAllSales:false));
		this.saveObj(new CommissionType(name:"Comisión de Captación de Inmueble con atalaya", description:"Comisión por captación de un inmueble vendido con agente externo", internalID: CommissionType.getPartnerWithAtalayaCatchCommissionTypeInternalID(), selfInvoiceDefaultDescription:"Honorarios por servicios de intermediación Inmobiliaria", expenseType:ExpenseType.findByInternalID("1"),appliedToSalesByOffice:false,appliedToAllSales:false));//usado el internalID en IncomeController
		this.saveObj(new CommissionType(name:"Comisión por brindar informacion sobre venta", description:"Comisión para atalayas por informar sobre comprador.", internalID: CommissionType.getAtalayaSellCommissionTypeInternalID(), selfInvoiceDefaultDescription:"Honorarios por servicios de intermediación Inmobiliaria", expenseType:ExpenseType.findByInternalID("2"),appliedToSalesByOffice:false,appliedToAllSales:false));//usado el internalID en IncomeController
		this.saveObj(new CommissionType(name:"Comisión por brindar informacion sobre captacion", description:"Comisión para atalayas por informar sobre inmuebles a captar.", internalID: CommissionType.getAtalayaCatchCommissionTypeInternalID(), selfInvoiceDefaultDescription:"Honorarios por servicios de intermediación Inmobiliaria", expenseType:ExpenseType.findByInternalID("2"),appliedToSalesByOffice:false,appliedToAllSales:false));//usado el internalID en IncomeController
		
		//CommissionRate
		this.saveObj(new CommissionRate(name: "Comision Agente Venta", percentage:25, commissionType:CommissionType.getPartnerSellCommissionType(), partnerRole:PartnerRole.findByName("Agente")));
		this.saveObj(new CommissionRate(name: "Comision Agente Captacion", percentage:20, commissionType:CommissionType.getPartnerCatchCommissionType(), partnerRole:PartnerRole.findByName("Agente")));
		this.saveObj(new CommissionRate(name: "Comision Agente Venta con Atalaya", percentage:22, commissionType:CommissionType.getPartnerWithAtalayaSellCommissionType(), partnerRole:PartnerRole.findByName("Agente")));
		this.saveObj(new CommissionRate(name: "Comision Agente Captacion con Atalaya", percentage:15, commissionType:CommissionType.getPartnerWithAtalayaCatchCommissionType(), partnerRole:PartnerRole.findByName("Agente")));
		this.saveObj(new CommissionRate(name: "Comision Atalaya Venta ", percentage:13, commissionType:CommissionType.getAtalayaSellCommissionType(), partnerRole:PartnerRole.findByName("Atalaya")));
		this.saveObj(new CommissionRate(name: "Comision Atalaya Captacion", percentage:7, commissionType:CommissionType.getAtalayaCatchCommissionType(), partnerRole:PartnerRole.findByName("Atalaya")));
		
		//IncomeType
		this.saveObj(new IncomeType(name:"Honoraios por venta de Concesión", description:"Cobro de honorarios por intermediación en la venta de una concesión inmobiliaria", relatedDomain:RelatedDomain.CONCESSION, billingDefaultDescription:"Honorarios por servicios de intermediación Inmobiliaria", taxRate:TaxRate.findByName("10 %")));
		this.saveObj(new IncomeType(name:"Honorarios por tasación", description:"Cobro de honorarios por servicio de tasación inmobiliaria", relatedDomain:RelatedDomain.NONE, billingDefaultDescription:"Honorarios por servicios de tasación Inmobiliaria", taxRate:TaxRate.findByName("10 %")));
		
		//PaymentPlan
		this.saveObj(new PaymentPlan(name:"2 pagos anuales con 50% de entrega", initialFreeTimeInDays:0, regularPaymentsInDays:0, regularPaymentsInMonths:12, initialPaymentPercentage:50, numberOfParts:3, interestPercentage:0));
		this.saveObj(new PaymentPlan(name:"12 pagos mensuales y 20% entrega en 15", initialFreeTimeInDays:15, regularPaymentsInDays:0, regularPaymentsInMonths:1, initialPaymentPercentage:20, numberOfParts:12, interestPercentage:0));
		this.saveObj(new PaymentPlan(name:"5 pagos quincenales sin entrega", initialFreeTimeInDays:0, regularPaymentsInDays:15, regularPaymentsInMonths:0, initialPaymentPercentage:0, numberOfParts:5, interestPercentage:0));
		
		//TransactionType
		this.saveObj(new TransactionType(name:"Cobro de Ingresos", internalID:"INCOME_PAYMENT", isDefault:true, isInternalTransaction:false));
		this.saveObj(new TransactionType(name:"Cobro de Ingresos", internalID:"EXPENSE_PAYMENT", isDefault:true, isInternalTransaction:false));
		this.saveObj(new TransactionType(name:"Cambio", internalID:"PAYMENT_CHANGE", isDefault:true, isInternalTransaction:false));
		
		//InvoicePrinting
		this.saveObj(new InvoicePrinting(printingNumber:65898521, startDate: new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("01/01/2015"), endDate: new SimpleDateFormat(Utils.getDefaultDateFormat()).parse("01/01/2016"), firstNumber:"001", secondNumber:"001", thirdStartNumber:"0000001", quantity:50));
		
		//ActionType
		this.saveObj(new ActionType(name:"Corpida de pasto a cargo del cliente", description:"Corpida de pasto que paga el cliente.", hasCost:true, clientPays:true));
		this.saveObj(new ActionType(name:"Corpida de pasto a cargo de la Inmobiliaria", description:"Corpida de pasto que paga la inmobiliaria.", hasCost:true, clientPays:false));
		this.saveObj(new ActionType(name:"Recorrido para verificar estado", description:"Recorrido al inmueble para ver su estado.", hasCost:false, clientPays:false));
		
		//ContactType
		this.saveObj(new ContactType(name:"Informacion por email", description:"Información suministrada al cliente por email.", email:true, phoneCall:false, chat:false, showing:false, personally:false));
		this.saveObj(new ContactType(name:"Informacion por chat", description:"Información suministrada al cliente por chat.", email:false, phoneCall:false, chat:true, showing:false, personally:false));
		this.saveObj(new ContactType(name:"Informacion por llamada telefónica", description:"Información suministrada al cliente por llamada telefónica.", email:false, phoneCall:true, chat:false, showing:false, personally:false));
		this.saveObj(new ContactType(name:"Visita del inmueble con el cliente", description:"Visita del inmueble con el cliente.", email:false, phoneCall:false, chat:false, showing:true, personally:false));
		this.saveObj(new ContactType(name:"Informacion brindada personalmente", description:"Información suministrada al cliente personalmente.", email:false, phoneCall:false, chat:false, showing:false, personally:true));
	
		//CrmConfig
		this.saveObj(new CrmConfig(dateFormat:"dd/MM/yyyy", companyName:"MacroInmuebles", plan:crm.enums.software.Plan.FULL, dbVersion:1/*, crmPartnerImagePath:"uploads/partner", crmPropertyImagePath:"uploads/property", webPropertyImagePath:"img/crm/property", webPartnerImagePath:"img/crm/partner"*/));
	}
	
	private void saveObj(Object obj){
		if (!obj.save(flush: true)) {
			GUtils.printErrors(obj, null);
		}
		
	}
	
}
