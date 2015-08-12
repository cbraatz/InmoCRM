import java.util.Date;

import crm.*;

import java.text.SimpleDateFormat
class BootStrap {

    def init = { servletContext ->
		println("Starting init.......................");
		addNullData();
		addBasicData();
		println("Finishing init......................");
    }
    def destroy = {
    }
	private void addNullData(){
		this.saveObj(new Neighborhood(name: "ninguno", description: "None neighborhood."));
		this.saveObj(new Profession(name: "ninguno", description: ""));
		
	}
	private void addBasicData(){
		//country
		this.saveObj(new Country(name: "Paraguay"));
		this.saveObj(new Country(name: "Argentina"));		
		this.saveObj(new Country(name: "Brasil"));		
		this.saveObj(new Country(name: "Uruguay"));		
		this.saveObj(new Country(name: "Chile"));
		this.saveObj(new Country(name: "Estados Unidos"));
		
		//department
		this.saveObj(new Department(name: "Itapúa", country: Country.findByName("Paraguay")));		
		this.saveObj(new Department(name: "Alto Paraná", country: Country.findByName("Paraguay")));		
		
		//city
		this.saveObj(new City(name: "Obligado", department: Department.findByName("Itapúa")));		
		this.saveObj(new City(name: "Encarnación", department: Department.findByName("Itapúa")));		
		this.saveObj(new City(name: "Hohenau", department: Department.findByName("Itapúa")));
		
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
		
		//neighborhood
		this.saveObj(new Neighborhood(name:"Centro", description:"test", city:City.findByName("Obligado")));		
		this.saveObj(new Neighborhood(name:"Barrio Los Colonos", description:"test", city:City.findByName("Obligado")));		
		this.saveObj(new Neighborhood(name:"Los Cedrales", description:"test", city:City.findByName("Obligado")));
		
		//Zone
		this.saveObj(new Zone(name: "Colonias Unidas", description: "Obligado + Hohenau + Bella Vista"));
		
		//Address
		this.saveObj(new Address(streetOne:"Calle 1", streetTwo:"Calle 2", number:1568, addressLine:"Calle 1 1522 c/ Calle 2", reference:"Frente al Centro de Salud.", description:"Casa de rejas blancas y patio amplio.", code:"6000", latitude: "-27,054249533179895", longitude:"-55,6293557718102", homePhone:"0988541258", city: City.findByName("Obligado"), neighborhood: Neighborhood.findByName("Centro"), zone: Zone.findByName("Colonias Unidas")));
		
		//PartnerRole
		this.saveObj(new PartnerRole(name: "Admin", isEmployee:"false", description:"Default Partner."));
		
		//Partner
		this.saveObj(new Partner(name: "Default", lastName:"Partner", gender: Gender.findByName("Masculino"), phone:"0", IDNumber:"0", emailAddress: "admin_partner@test.com", birthDate: new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1900"), address:Address.findByAddressLine("Calle 1 1522 c/ Calle 2"), isActive:"true", partnerRole:PartnerRole.findByName("Admin")));
		
		//crmuser
		this.saveObj(new CrmUser(partner: Partner.findByName("Default"), name:"nobody", password:"123456", emailAddress: "default_user@test.com", isAdmin:"true", isActive:"true", isDefault:"true"));
		
		//ClientCategory
		this.saveObj(new ClientCategory(name: "Pequeño", description:"Clientes con sueldo mínimo o menos."));		
		this.saveObj(new ClientCategory(name: "Pequeño-Medio", description:"Clientes con más de sueldo mínimo, hasta 3 sueldos mínimo."));
		
		//client
		this.saveObj(new Client(name: "Cliente 1", lastName: "Apellido 1", description: "test", IDNumber:"0", birthDate: new SimpleDateFormat("dd/MM/yyyy").parse("15/06/1980"), phone:"0985000000", phone2:"0985000001", notificationPhone:"0985000002", emailAddress: "cliente1@test.com", nationality: Country.findByName("Paraguay"), maritalStatus: MaritalStatus.findByName("Soltero/a"), profession: Profession.findByName("Agricultor"), gender: Gender.findByName("Masculino"), address:Address.findByAddressLine("Calle 1 1522 c/ Calle 2"), category:ClientCategory.findByName("Pequeño"), owner: CrmUser.findByName("nobody"), isActive:"true", readsEmail:"true", readsSms:"true", receiveNotifications:"true", isProspectiveClient:"false"));
		
		//TaxRate
		this.saveObj(new TaxRate(name: "10 %", percentage:10));		
		this.saveObj(new TaxRate(name: "5 %", percentage:5));		
		this.saveObj(new TaxRate(name: "0 %", percentage:0));
		
		//BuildingFeature
		this.saveObj(new BuildingFeature(name: "Acondicionador de aire", description: "Cuenta con acondicionador de aire (antiguo o split)", hasValue:false));		
		this.saveObj(new BuildingFeature(name: "Cocina con amoblado básico", description: "Cuenta con los muebles básicos de cocina", hasValue:false));		
		this.saveObj(new BuildingFeature(name: "Amoblamiento completo", description: "Cuenta con amoblamiento completo de todo el inmueble", hasValue:false));
		
		//DimensionMeasuringUnit
		this.saveObj(new DimensionMeasuringUnit(name: "Metro", nameInPlural:"Metros", abbreviation:"m", abbreviationInPlural:"m", isDefault:true, isArea:false));		
		this.saveObj(new DimensionMeasuringUnit(name: "Metro cuadrado", nameInPlural:"Metros cuadrados", abbreviation:"m2", abbreviationInPlural:"m2", isDefault:true, isArea:true));		
		this.saveObj(new DimensionMeasuringUnit(name: "Hectárea", nameInPlural:"Hectáreas", abbreviation:"ha", abbreviationInPlural:"ha", isDefault:true, isArea:true));
		
		//BuildingType
		this.saveObj(new BuildingType(name: "Casa", description:"Inmueble independiente y de uso habitacional", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("m2")));		
		this.saveObj(new BuildingType(name: "Edificio de Departamentos", description:"Inmueble independiente que está dividido en departamentos", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("m2")));		
		this.saveObj(new BuildingType(name: "Piso", description:"Un piso de un edificio", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("m2")));		
		this.saveObj(new BuildingType(name: "Local Comercial", description:"Inmueble en planta baja destinado a actividades comerciales", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("m2")));		
		this.saveObj(new BuildingType(name: "Depósito", description:"Inmueble destinado a guardar/depositar bienes", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("m2")));
		
		//Usage
		this.saveObj(new Usage(name: "Cultivo", description:"Destinado al cultivo de rubros permitidos (Soja, trigo, maíz, sorgo, avena, etc.)"));		
		this.saveObj(new Usage(name: "Ganadería", description:"Destinado a la producción animal."));
		this.saveObj(new Usage(name: "Uso habitacional", description:"Destinado a la construcción de viviendas."));
		this.saveObj(new Usage(name: "Uso comercial", description:"Destinado a la construcción de locales comerciales."));
		
		//Language
		this.saveObj(new Language(name: "Español", isDefault:true));
		this.saveObj(new Language(name: "English", isDefault:true));
		this.saveObj(new Language(name: "Português", isDefault:true));
		this.saveObj(new Language(name: "Deutsch", isDefault:true));
		
		//BroadcastMedia
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
		
		//InterestLevel
		this.saveObj(new InterestLevel(name: "Máximo", color: "red", level:1));		
		this.saveObj(new InterestLevel(name: "Muy Interesado", color: "orange", level:2));		
		this.saveObj(new InterestLevel(name: "Interesado", color: "yellow", level:3));		
		this.saveObj(new InterestLevel(name: "Poco Interesado", color: "green", level:4));
		
		//Currency
		this.saveObj(new Currency(name: "Guaraní", plural:"Guaraníes", symbol:"Gs", decimals:0, isDefault:"true", country: Country.findByName("Paraguay")));		
		this.saveObj(new Currency(name: "Dólar", plural:"Dólares", symbol:"USS", decimals:2, isDefault:"false", country: Country.findByName("Estados Unidos")));
		
		//DemandStatus
		this.saveObj(new DemandStatus(name: "Nueva", isNew:"true", isClosed:"false"));
		this.saveObj(new DemandStatus(name: "Necesita más información", isNew:"true", isClosed:"false"));
		this.saveObj(new DemandStatus(name: "Verificada", isNew:"false", isClosed:"false"));
		this.saveObj(new DemandStatus(name: "Listo para cerrar", isNew:"false", isClosed:"false"));
		this.saveObj(new DemandStatus(name: "Concretado", isNew:"false", isClosed:"true"));
		this.saveObj(new DemandStatus(name: "Cancelada", isNew:"false", isClosed:"true"));
		
		//ContractType
		this.saveObj(new ContractType(name: "No Eclusivo", isExclusive:"false", description:"Contrato no exclusivo.", commissionPercentage:4, billingDefaultDescription:"Intermediación de venta de inmueble. Contrato no exclusivo."));
		this.saveObj(new ContractType(name: "Eclusivo", isExclusive:"true", description:"Contrato exclusivo.", commissionPercentage:3, billingDefaultDescription:"Intermediación de venta de inmueble. Contrato exclusivo."));
		
		//Contract
		this.saveObj(new Contract(internalID:"1", date:new Date(), contractType:ContractType.findByName("No Exclusivo")));
		
		//PropertyType
		this.saveObj(new PropertyType(name:"Sitio", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("m2"), description:"Lote de tierra generalmente urbano."));
		this.saveObj(new PropertyType(name:"Chacra", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("ha"), description:"Lote de tierra generalmente rural destinado a la producción agrícola."));
		this.saveObj(new PropertyType(name:"Estancia", dimensionMeasuringUnit:DimensionMeasuringUnit.findByAbbreviation("ha"), description:"Lote de tierra generalmente rural destinado a la producción animal."));
		
		//ManagedProperty
		this.saveObj(new ManagedProperty(title:"Terreno de 1200m2 en Obligado Centro, al lado del Centro de Salud", description:"Terreno con vereda y árboles frutales", measures:"20m x 60m", publicAddress:"Obligado Centro, cerca del Centro de Salud", publicCashPrice:"240.000 USS", price:240000, currency:Currency.findBySymbol("USS"), value:250000, 
									clientInitialPrice:240000, addedDate:new Date(), placedBillboards:1, area:1200,excess:2, owner:Client.findByName("Cliente 1"), address:Address.findByAddressLine("Calle 1 1522 c/ Calle 2"), propertyType:PropertyType.findByName("Sitio"), isSoldByCompany:"false", inWeb:"true"));
		
		//PropertyUsage
		this.saveObj(new PropertyUsage(usage:Usage.findByName("Uso habitacional"), managedProperty:ManagedProperty.findByTitle("Terreno de 1200m2 en Obligado Centro, al lado del Centro de Salud"), quantity:100, isQuantityInPercentage:"true", isCurrentUsage:"false"));
		this.saveObj(new PropertyUsage(usage:Usage.findByName("Uso comercial"), managedProperty:ManagedProperty.findByTitle("Terreno de 1200m2 en Obligado Centro, al lado del Centro de Salud"), quantity:100, isQuantityInPercentage:"true", isCurrentUsage:"false"));
		
		//Building
		this.saveObj(new Building(builtSize:500, builtYear:2013, managedProperty:ManagedProperty.findByTitle("Terreno de 1200m2 en Obligado Centro, al lado del Centro de Salud"), buildingType:BuildingType.findByName("Casa"), buildingCondition:BuildingCondition.findByName("Semi nuevo"), description:"Es una casa estilo moderno, sin goteras y ladrillo visto."));
		
		//Concession
		this.saveObj(new Concession(adSummary:"Vendo Casa en pleno centro de Obligado", adText:"Excelente oportunidad en Obligado, se trata de una casa de 500m2 edificada sobre un terreno de 120m2 en pleno centro de Obligado", isNegotiable:"false", startDate:new SimpleDateFormat("dd/MM/yyyy").parse("15/06/2015"), endDate: new SimpleDateFormat("dd/MM/yyyy").parse("15/06/2016"), valueDegree:1, commissionAmount:9600, commissionPercentage:4, description:"Ninguna", /*propertyDemand:PropertyDemand,*/ 
									contract:Contract.findByInternalID("1"), publishInMLS:"false", publishInPortals:"false", keys:"NO", barter:"NO", financing:"NO", client:Client.findByName("Cliente 1"), owner:CrmUser.findByName("nobody"), isActive:"true"));
								
		//InterestType
		this.saveObj(new InterestType(name:"Simple", description:"Interés Simple", internalID:"1", isSimpleInterest:"true"));
		
		//ExpenseType
		this.saveObj(new ExpenseType(name:"Comisiones por Intermediación", description:"Pago de comisiones por servicios prestados por Agentes Inmobiliarios", internalID:"1", selfInvoiceDefaultDescription:"Honorarios por servicios de intermediación Inmobiliaria", taxRate:TaxRate.findByName("10 %")));//mejorar busqueda o no incluir esto por defecto
		//CommissionType
		this.saveObj(new CommissionType(name:"Comisión de Venta de Inmueble", description:"Comisión por cerrar una venta de un inmueble", internalID:"1", selfInvoiceDefaultDescription:"Honorarios por servicios de intermediación Inmobiliaria", expenseType:ExpenseType.findByInternalID("1")));
		this.saveObj(new CommissionType(name:"Comisión de Captación de Inmueble", description:"Comisión por captación de un inmueble vendido", internalID:"2", selfInvoiceDefaultDescription:"Honorarios por servicios de intermediación Inmobiliaria", expenseType:ExpenseType.findByInternalID("1")));
	}
	private void saveObj(Object obj){
		if (!obj.save(flush: true)) {
			obj.errors.each {
				println it;
				log.error it;
			}
		}
		
	}
	
}
