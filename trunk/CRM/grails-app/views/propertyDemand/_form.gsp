
<fieldset class="form">
	<f:field bean="propertyDemand" property="addedDate"/>
	<f:field bean="propertyDemand" property="dueDate"/>
	<f:field bean="propertyDemand" property="client" input-propId="${propertyDemand?.client?.id}"/>
	<f:field bean="propertyDemand" property="buildingCondition" input-propId="${propertyDemand?.buildingCondition?.id}"/>
	<f:field bean="propertyDemand" property="isBuildingConditionRequired"/>
	<f:field bean="propertyDemand" property="priorityLevel" input-propId="${propertyDemand?.priorityLevel?.id}"/>
	<f:field bean="propertyDemand" property="interestLevel" input-propId="${propertyDemand?.interestLevel?.id}"/>
	<f:field bean="propertyDemand" property="isSellDemand"/>
	<f:field bean="propertyDemand" property="propertyType" input-propId="${propertyDemand?.propertyType?.id}"/>
	<f:field bean="propertyDemand" property="specifyPropertyType"/>
	<f:field bean="propertyDemand" property="isPropertyTypeRequired"/>
	<f:field bean="propertyDemand" property="buildingType" input-propId="${propertyDemand?.buildingType?.id}"/>
	<f:field bean="propertyDemand" property="specifyBuildingType"/>
	<f:field bean="propertyDemand" property="isBuildingTypeRequired"/>
	<f:field bean="propertyDemand" property="department" input-propId="${propertyDemand?.department?.id}"/>
	<f:field bean="propertyDemand" property="isDepartmentRequired"/>
	<f:field bean="propertyDemand" property="city" input-propId="${propertyDemand?.city?.id}"/>
	<f:field bean="propertyDemand" property="specifyCity"/>
	<f:field bean="propertyDemand" property="isCityRequired"/>
	<f:field bean="propertyDemand" property="neighborhood" input-propId="${propertyDemand?.neighborhood?.id}"/>
	<f:field bean="propertyDemand" property="specifyNeighborhood"/>
	<f:field bean="propertyDemand" property="isNeighborhoodRequired"/>
	<f:field bean="propertyDemand" property="zone" input-propId="${propertyDemand?.neighborhood?.id}"/>
	<f:field bean="propertyDemand" property="specifyZone"/>
	<f:field bean="propertyDemand" property="isZoneRequired"/>
	<!--<f:field bean="propertyDemand" property="mainUsage" input-propId="${propertyDemand?.mainUsage?.id}"/>  -->
	<!--<f:field bean="propertyDemand" property="specifyUsage"/>  -->
	<!--<f:field bean="propertyDemand" property="isUsageRequired"/>  -->
	<f:field bean="propertyDemand" property="propertyMinArea"/>
	<f:field bean="propertyDemand" property="propertyMaxArea"/>
	<f:field bean="propertyDemand" property="isAreaRequired"/>
	<f:field bean="propertyDemand" property="specifyPropertyFeatures"/>
	<f:field bean="propertyDemand" property="specifyBuildingFeatures"/>
	<f:field bean="propertyDemand" property="timeAvailability"/>
	<f:field bean="propertyDemand" property="offersOnly"/>
	<f:field bean="propertyDemand" property="price"/>
	<f:field bean="propertyDemand" property="minPrice"/>
	<f:field bean="propertyDemand" property="maxPrice"/>
	<f:field bean="propertyDemand" property="specifyPrice"/>
	<f:field bean="propertyDemand" property="isPriceRequired"/>
	<f:field bean="propertyDemand" property="averagePrice"/>
	<f:field bean="propertyDemand" property="currency" input-propId="${propertyDemand?.currency?.id}"/>
	<f:field bean="propertyDemand" property="owner" input-propId="${propertyDemand?.owner?.id}"/>
	<f:field bean="propertyDemand" property="assignee" input-propId="${propertyDemand?.assignee?.id}"/>
	<f:field bean="propertyDemand" property="status" input-propId="${propertyDemand?.status?.id}"/>
	<f:field bean="propertyDemand" property="additionalDescription">
		<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="3" cols="60"/>
	</f:field>
	<f:field bean="propertyDemand" property="specifyBroadcastMedia">
		<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="2" cols="60"/>
	</f:field>
</fieldset>
















