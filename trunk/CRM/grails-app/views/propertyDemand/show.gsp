<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'propertyDemand.label', default: 'PropertyDemand')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-propertyDemand" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-propertyDemand" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            	<fieldset class="fieldcontain">
            		<span id="addedDate-label" class="property-label"><g:message code="propertyDemand.addedDate.label" default="addedDate"/></span>
					<f:display bean="propertyDemand" property="addedDate"/>
					<span id="dueDate-label" class="property-label"><g:message code="propertyDemand.dueDate.label" default="dueDate"/></span>
					<f:display bean="propertyDemand" property="dueDate"/>
					<span id="client-label" class="property-label"><g:message code="propertyDemand.client.label" default="client"/></span>
					<f:display bean="propertyDemand" property="client"/>
					<span id="buildingCondition-label" class="property-label"><g:message code="propertyDemand.buildingCondition.label" default="buildingCondition"/></span>
					<f:display bean="propertyDemand" property="buildingCondition"/>
					<span id="isBuildingConditionRequired-label" class="property-label"><g:message code="propertyDemand.isBuildingConditionRequired.label" default="isBuildingConditionRequired"/></span>
					<f:display bean="propertyDemand" property="isBuildingConditionRequired"/>
					<span id="priorityLevel-label" class="property-label"><g:message code="propertyDemand.priorityLevel.label" default="priorityLevel"/></span>
					<f:display bean="propertyDemand" property="priorityLevel"/>
					<span id="interestLevel-label" class="property-label"><g:message code="propertyDemand.interestLevel.label" default="interestLevel"/></span>
					<f:display bean="propertyDemand" property="interestLevel"/>
					<span id="isSellDemand-label" class="property-label"><g:message code="propertyDemand.isSellDemand.label" default="isSellDemand"/></span>
					<f:display bean="propertyDemand" property="isSellDemand"/>
					<span id="propertyType-label" class="property-label"><g:message code="propertyDemand.propertyType.label" default="propertyType"/></span>
					<f:display bean="propertyDemand" property="propertyType"/>
					<span id="specifyPropertyType-label" class="property-label"><g:message code="propertyDemand.specifyPropertyType.label" default="specifyPropertyType"/></span>
					<f:display bean="propertyDemand" property="specifyPropertyType"/>
					<span id="isPropertyTypeRequired-label" class="property-label"><g:message code="propertyDemand.isPropertyTypeRequired.label" default="isPropertyTypeRequired"/></span>
					<f:display bean="propertyDemand" property="isPropertyTypeRequired"/>
					<span id="buildingType-label" class="property-label"><g:message code="propertyDemand.buildingType.label" default="buildingType"/></span>
					<f:display bean="propertyDemand" property="buildingType"/>
					<span id="specifyBuildingType-label" class="property-label"><g:message code="propertyDemand.specifyBuildingType.label" default="specifyBuildingType"/></span>
					<f:display bean="propertyDemand" property="specifyBuildingType"/>
					<span id="isBuildingTypeRequired-label" class="property-label"><g:message code="propertyDemand.isBuildingTypeRequired.label" default="isBuildingTypeRequired"/></span>
					<f:display bean="propertyDemand" property="isBuildingTypeRequired"/>
					<span id="department-label" class="property-label"><g:message code="propertyDemand.department.label" default="department"/></span>
					<f:display bean="propertyDemand" property="department"/>
					<span id="isDepartmentRequired-label" class="property-label"><g:message code="propertyDemand.isDepartmentRequired.label" default="isDepartmentRequired"/></span>
					<f:display bean="propertyDemand" property="isDepartmentRequired"/>
					<span id="city-label" class="property-label"><g:message code="propertyDemand.city.label" default="city"/></span>
					<f:display bean="propertyDemand" property="city"/>
					<span id="specifyCity-label" class="property-label"><g:message code="propertyDemand.specifyCity.label" default="specifyCity"/></span>
					<f:display bean="propertyDemand" property="specifyCity"/>
					<span id="isCityRequired-label" class="property-label"><g:message code="propertyDemand.isCityRequired.label" default="isCityRequired"/></span>
					<f:display bean="propertyDemand" property="isCityRequired"/>
					<span id="neighborhood-label" class="property-label"><g:message code="propertyDemand.neighborhood.label" default="neighborhood"/></span>
					<f:display bean="propertyDemand" property="neighborhood"/>
					<span id="specifyNeighborhood-label" class="property-label"><g:message code="propertyDemand.specifyNeighborhood.label" default="specifyNeighborhood"/></span>
					<f:display bean="propertyDemand" property="specifyNeighborhood"/>
					<span id="isNeighborhoodRequired-label" class="property-label"><g:message code="propertyDemand.isNeighborhoodRequired.label" default="isNeighborhoodRequired"/></span>
					<f:display bean="propertyDemand" property="isNeighborhoodRequired"/>
					<span id="zone-label" class="property-label"><g:message code="propertyDemand.zone.label" default="zone"/></span>
					<f:display bean="propertyDemand" property="zone"/>
					<span id="specifyZone-label" class="property-label"><g:message code="propertyDemand.specifyZone.label" default="specifyZone"/></span>
					<f:display bean="propertyDemand" property="specifyZone"/>
					<span id="isZoneRequired-label" class="property-label"><g:message code="propertyDemand.isZoneRequired.label" default="isZoneRequired"/></span>
					<f:display bean="propertyDemand" property="isZoneRequired"/>				
					<!--<f:display bean="propertyDemand" property="mainUsage"/>  -->
					<!--<f:display bean="propertyDemand" property="specifyUsage"/>  -->
					<!--<f:display bean="propertyDemand" property="isUsageRequired"/>  -->
					<span id="propertyMinArea-label" class="property-label"><g:message code="propertyDemand.propertyMinArea.label" default="propertyMinArea"/></span>
					<f:display bean="propertyDemand" property="propertyMinArea"/>
					<span id="propertyMaxArea-label" class="property-label"><g:message code="propertyDemand.propertyMaxArea.label" default="propertyMaxArea"/></span>
					<f:display bean="propertyDemand" property="propertyMaxArea"/>
					<span id="isAreaRequired-label" class="property-label"><g:message code="propertyDemand.isAreaRequired.label" default="isAreaRequired"/></span>
					<f:display bean="propertyDemand" property="isAreaRequired"/>
					<span id="specifyPropertyFeatures-label" class="property-label"><g:message code="propertyDemand.specifyPropertyFeatures.label" default="specifyPropertyFeatures"/></span>
					<f:display bean="propertyDemand" property="specifyPropertyFeatures"/>
					<span id="specifyBuildingFeatures-label" class="property-label"><g:message code="propertyDemand.specifyBuildingFeatures.label" default="specifyBuildingFeatures"/></span>
					<f:display bean="propertyDemand" property="specifyBuildingFeatures"/>
					<span id="timeAvailability-label" class="property-label"><g:message code="propertyDemand.timeAvailability.label" default="timeAvailability"/></span>
					<f:display bean="propertyDemand" property="timeAvailability"/>
					<span id="offersOnly-label" class="property-label"><g:message code="propertyDemand.offersOnly.label" default="offersOnly"/></span>
					<f:display bean="propertyDemand" property="offersOnly"/>
					<span id="price-label" class="property-label"><g:message code="propertyDemand.price.label" default="price"/></span>
					<f:display bean="propertyDemand" property="price"/>
					<span id="minPrice-label" class="property-label"><g:message code="propertyDemand.minPrice.label" default="minPrice"/></span>
					<f:display bean="propertyDemand" property="minPrice"/>
					<span id="maxPrice-label" class="property-label"><g:message code="propertyDemand.maxPrice.label" default="maxPrice"/></span>
					<f:display bean="propertyDemand" property="maxPrice"/>
					<span id="specifyPrice-label" class="property-label"><g:message code="propertyDemand.specifyPrice.label" default="specifyPrice"/></span>
					<f:display bean="propertyDemand" property="specifyPrice"/>
					<span id="isPriceRequired-label" class="property-label"><g:message code="propertyDemand.isPriceRequired.label" default="isPriceRequired"/></span>
					<f:display bean="propertyDemand" property="isPriceRequired"/>
					<span id="averagePrice-label" class="property-label"><g:message code="propertyDemand.averagePrice.label" default="averagePrice"/></span>
					<f:display bean="propertyDemand" property="averagePrice"/>
					<span id="currency-label" class="property-label"><g:message code="propertyDemand.currency.label" default="currency"/></span>
					<f:display bean="propertyDemand" property="currency"/>
					<span id="owner-label" class="property-label"><g:message code="propertyDemand.owner.label" default="owner"/></span>
					<f:display bean="propertyDemand" property="owner"/>
					<span id="assignee-label" class="property-label"><g:message code="propertyDemand.assignee.label" default="assignee"/></span>
					<f:display bean="propertyDemand" property="assignee"/>
					<span id="status-label" class="property-label"><g:message code="propertyDemand.status.label" default="status"/></span>
					<f:display bean="propertyDemand" property="status"/>
					<span id="additionalDescription-label" class="property-label"><g:message code="propertyDemand.additionalDescription.label" default="additionalDescription"/></span>
					<f:display bean="propertyDemand" property="additionalDescription"/>
					<span id="specifyBroadcastMedia-label" class="property-label"><g:message code="propertyDemand.specifyBroadcastMedia.label" default="specifyBroadcastMedia"/></span>
					<f:display bean="propertyDemand" property="specifyBroadcastMedia"/>
				</fieldset>
            <g:form resource="${this.propertyDemand}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.propertyDemand}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
