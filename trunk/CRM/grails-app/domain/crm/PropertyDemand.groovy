package crm

class PropertyDemand {
	Boolean isSellDemand;
	Date addedDate;
	Date dueDate;
	Department department;
	Boolean isDepartmentRequired;
	City city;
	String specifyCity;
	Boolean isCityRequired;
	Neighborhood neighborhood;
	String specifyNeighborhood;
	Boolean isNeighborhoodRequired;
	Float buildingArea;
	Float propertyArea;
	Float propertyMinArea;
	Float propertyMaxArea;
	Boolean isAreaRequired;
	Usage mainUsage;
	String specifyUsage;
	Boolean isUsageRequired;
	Zone zone;
	String specifyZone;
	Boolean isZoneRequired;
	String specifyPropertyFeatures;
	String specifyBuildingFeatures;
	String additionalDescription;
	PropertyType propertyType;
	String specifyPropertyType;
	Boolean isPropertyTypeRequired;
	BuildingType buildingType;
	String specifyBuildingType;
	Boolean isBuildingTypeRequired;
	String timeAvailability;
	Boolean offersOnly;
	Double price;
	Double minPrice;
	Double maxPrice;
	String specifyPrice;
	Boolean isPriceRequired;
	Currency currency;
	Client client;
	CrmUser owner;
	CrmUser assignee;
	BroadcastMedia broadcastMedia;
	String specifyBroadcastMedia;
	BuildingCondition buildingCondition;
	Boolean isBuildingConditionRequired;
	PriorityLevel priorityLevel;
	InterestLevel interestLevel;
	DemandStatus demandStatus;
	static hasMany = [concessions:Concession, comments:Comment, userNotificationSubscriptions:UserNotificationSubscription, propertyFeaturesByPropertyDemand:PropertyFeatureByPropertyDemand, buildingFeaturesByPropertyDemand:BuildingFeatureByPropertyDemand/*,RealEstateAction,TagSelectedValue,CustomFieldSelectedValue*/]
    static constraints = {
		isSellDemand(nullable:false);
		addedDate(nullable:false);
		dueDate(nullable:true);
		department(nullable:true);
		isDepartmentRequired(nullable:false);
		city(nullable:true);
		specifyCity(blank: true, nullable:true, size:0..80);
		isCityRequired(nullable:false);
		neighborhood(nullable:true);
		specifyNeighborhood(blank: true, nullable:true, size:0..80);
		isNeighborhoodRequired(nullable:false);
		buildingArea(blank: true, nullable:true);
		propertyArea(blank: true, nullable:true);
		propertyMinArea(blank: true, nullable:true);
		propertyMaxArea(blank: true, nullable:true);
		isAreaRequired(nullable:false);
		mainUsage(nullable:true);
		specifyUsage(blank: true, nullable:true, size:0..80);
		isUsageRequired(nullable:false);
		zone(nullable:true);
		specifyZone(blank: true, nullable:true, size:0..80);
		isZoneRequired(nullable:false);
		specifyPropertyFeatures(blank: true, nullable:true, size:0..80);
		specifyBuildingFeatures(blank: true, nullable:true, size:0..80);
		additionalDescription(blank: true, nullable:true, widget:'textArea', size:0..200);
		propertyType(nullable:true);
		specifyPropertyType(blank: true, nullable:true, size:0..80);
		isPropertyTypeRequired(nullable:false);
		buildingType(nullable:true);
		specifyBuildingType(blank: true, nullable:true, size:0..80);
		isBuildingTypeRequired(nullable:false);
		timeAvailability(blank: true, nullable:true, size:0..80);
		offersOnly(nullable:true);
		price(blank: true, nullable:true);
		minPrice(blank: true, nullable:true);
		maxPrice(blank: true, nullable:true);
		specifyPrice(blank: true, nullable:true, size:0..80);
		isPriceRequired(blank: false, nullable:false);
		currency(nullable:true);
		client(nullable:false);
		owner(nullable:false);
		assignee(nullable:true);
		broadcastMedia(nullable:true);
		specifyBroadcastMedia(blank: true, nullable:true, widget:'textArea', size:0..100);
		buildingCondition(nullable:true);
		isBuildingConditionRequired(nullable:false);
		priorityLevel(nullable:false);
		interestLevel(nullable:false);
		demandStatus(nullable:false);
    }
	
	public ArrayList<PropertyDemand> getSmartMatchesForSellDemand(){
		if(this.isSellDemand){
			List<PropertyDemand> demands;
			List<PropertyDemand> result=new ArrayList<PropertyDemand>();
			boolean addPD=true;
			demands=ManagedProperty.executeQuery("select pd from PropertyDemand pd join pd.demandStatus ds where ds.isClosed = ?", [false]);
			demands.each{
				addPD=true;
				if(it.isDepartmentRequired && this.department != null){
					if(this.department.id!=it.id){
						addPD=false;
					}
				}
				if(it.isCityRequired && this.city != null){
					if(this.city.id!=it.id){
						addPD=false;
					}
				}
				if(it.isNeighborhoodRequired && this.neighborhood != null){
					if(this.neighborhood.id!=it.id){
						addPD=false;
					}
				}
				if(it.isPropertyTypeRequired && this.propertyType != null){
					if(this.propertyType.id!=it.id){
						addPD=false;
					}
				}
				if(it.isBuildingTypeRequired && this.buildingType != null){
					if(this.buildingType.id!=it.id){
						addPD=false;
					}
				}
				if(it.isAreaRequired && this.area != null){
					if(this.area >= it.propertyMinArea && this.area <= it.propertyMaxArea){
						addPD=false;
					}
				}
				if(it.isZoneRequired && this.zone != null){
					if(this.zone.id!=it.id){
						addPD=false;
					}
				}
				if(it.isPriceRequired && this.price != null){
					if(this.price >= it.propertyMinPrice && this.price <= it.propertyMaxPrice){
						addPD=false;
					}
				}
				if(it.isBuildingConditionRequired && this.buildingCondition != null){
					if(this.buildingCondition.id!=it.id){
						addPD=false;
					}
				}
				if(addPD){
					result.add(it);
				}
			}
			return result;
		}else{
			return null;
		}
	}
	public ArrayList<ManagedProperty> getSmartMatchesForBuyDemand(){
		if(!this.isSellDemand){
			List paramethers=new ArrayList<Object>();
			StringBuffer query = new StringBuffer("select mp from ManagedProperty mp join mp.concessions co join mp.address a join a.city ci join mp.buildings b where ? BETWEEN co.startDate and co.endDate and co.isActive = ?");
			paramethers.add(new Date());
			paramethers.add(true);
			if(this.isDepartmentRequired && this.department != null){	
				query.append(" and ci.department = ?");
				paramethers.add(this.department);
			}
			if(this.isCityRequired && this.city != null){
				query.append(" and a.city = ?");
				paramethers.add(this.city);
			}
			if(this.isNeighborhoodRequired && this.neighborhood != null){
				query.append(" and a.neighborhood = ?");
				paramethers.add(this.neighborhood);
			}
			if(this.isPropertyTypeRequired && this.propertyType != null){
				query.append(" and mp.propertyType = ?");
				paramethers.add(this.propertyType);
			}
			if(this.isBuildingTypeRequired && this.buildingType != null){
				query.append(" and b.buildingType = ?");
				paramethers.add(this.buildingType);
			}
			if(this.isAreaRequired && this.propertyMinArea !=null && this.propertyMaxArea !=null){
				query.append(" and mp.area BETWEEN ? and ?");
				paramethers.add(this.propertyMinArea);
				paramethers.add(this.propertyMaxArea);
			}
			if(this.isZoneRequired && this.zone != null){
				query.append(" and a.zone = ?");
				paramethers.add(this.zone);
			}
			if(this.isPriceRequired && this.minPrice !=null && this.maxPrice !=null){
				query.append(" and mp.price BETWEEN ? and ?");
				paramethers.add(this.minPrice);
				paramethers.add(this.maxPrice);
			}
			if(this.isBuildingConditionRequired && this.buildingCondition != null){
				query.append(" and b.buildingCondition = ?");
				paramethers.add(this.buildingCondition);
			}
			return ManagedProperty.executeQuery(query.toString(), paramethers);
		}else{
			return null;
		}
	}
}
