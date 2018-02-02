<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'propertyDemand.label', default: 'PropertyDemand')}" />
        <g:set var="isSellDem" value="${propertyDemand.propertyDemandType.isSellDemand()}" />
        <g:set var="isBuyDem" value="${propertyDemand.propertyDemandType.isBuyDemand()}" />
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
            		<div class="${isSellDem ? 'sell-demand' : (isBuyDem ? 'buy-demand': 'unknown-demand-type')}">
            			<span id="propertyDemandType-label" class="property-label"><g:message code="propertyDemandType.label" default="Demand Type"/></span>
						<f:display bean="propertyDemand" property="propertyDemandType"/>
	            		<div class="grouping-box demand-info">
	            			<span id="name-label" class="property-label"><g:message code="propertyDemand.name.label" default="name"/></span>
							<f:display bean="propertyDemand" property="name"/>
		            		<span id="owner-label" class="property-label"><g:message code="propertyDemand.owner.label" default="owner"/></span>
							<f:display bean="propertyDemand" property="owner"/>
							<span id="assignee-label" class="property-label"><g:message code="propertyDemand.assignee.label" default="assignee"/></span>
							<f:display bean="propertyDemand" property="assignee"/>
							<span id="demandStatus-label" class="property-label"><g:message code="propertyDemand.demandStatus.label" default="demandStatus"/></span>
							<f:display bean="propertyDemand" property="demandStatus"/>
		            		<span id="addedDate-label" class="property-label"><g:message code="propertyDemand.addedDate.label" default="addedDate"/></span>
							<f:display bean="propertyDemand" property="addedDate"/>
							<span id="dueDate-label" class="property-label"><g:message code="propertyDemand.dueDate.label" default="dueDate"/></span>
							<f:display bean="propertyDemand" property="dueDate"/>
							<span id="client-label" class="property-label"><g:message code="propertyDemand.client.label" default="client"/></span>
							<f:display bean="propertyDemand" property="client"/>
							<span id="priorityLevel-label" class="property-label"><g:message code="propertyDemand.priorityLevel.label" default="priorityLevel"/></span>
							<f:display bean="propertyDemand" property="priorityLevel"/>
							<span id="interestLevel-label" class="property-label"><g:message code="propertyDemand.interestLevel.label" default="interestLevel"/></span>
							<f:display bean="propertyDemand" property="interestLevel"/>
						</div>
						<span id="propertyType-label" class="property-label"><g:message code="propertyDemand.propertyType.label" default="propertyType"/></span>
						<f:display bean="propertyDemand" property="propertyType"/>
						<span id="specifyPropertyType-label" class="property-label"><g:message code="propertyDemand.specifyPropertyType.label" default="specifyPropertyType"/></span>
						<f:display bean="propertyDemand" property="specifyPropertyType"/>
						<g:if test="${isBuyDem}">
							<span id="isPropertyTypeRequired-label" class="property-label"><g:message code="propertyDemand.isPropertyTypeRequired.label" default="isPropertyTypeRequired"/></span>
							<f:display bean="propertyDemand" property="isPropertyTypeRequired"/>
						</g:if>
						<span id="buildingType-label" class="property-label"><g:message code="propertyDemand.buildingType.label" default="buildingType"/></span>
						<f:display bean="propertyDemand" property="buildingType"/>
						<span id="specifyBuildingType-label" class="property-label"><g:message code="propertyDemand.specifyBuildingType.label" default="specifyBuildingType"/></span>
						<f:display bean="propertyDemand" property="specifyBuildingType"/>
						<g:if test="${isBuyDem}">
							<span id="isBuildingTypeRequired-label" class="property-label"><g:message code="propertyDemand.isBuildingTypeRequired.label" default="isBuildingTypeRequired"/></span>
							<f:display bean="propertyDemand" property="isBuildingTypeRequired"/>
						</g:if>
						<span id="buildingCondition-label" class="property-label"><g:message code="propertyDemand.buildingCondition.label" default="buildingCondition"/></span>
						<f:display bean="propertyDemand" property="buildingCondition"/>
						<g:if test="${isBuyDem}">
							<span id="isBuildingConditionRequired-label" class="property-label"><g:message code="propertyDemand.isBuildingConditionRequired.label" default="isBuildingConditionRequired"/></span>
							<f:display bean="propertyDemand" property="isBuildingConditionRequired"/>
						</g:if>
						<span id="department-label" class="property-label"><g:message code="propertyDemand.department.label" default="department"/></span>
						<f:display bean="propertyDemand" property="department"/>
						<g:if test="${isBuyDem}">
							<span id="isDepartmentRequired-label" class="property-label"><g:message code="propertyDemand.isDepartmentRequired.label" default="isDepartmentRequired"/></span>
							<f:display bean="propertyDemand" property="isDepartmentRequired"/>
						</g:if>
						<span id="city-label" class="property-label"><g:message code="propertyDemand.city.label" default="city"/></span>
						<f:display bean="propertyDemand" property="city"/>
						<span id="specifyCity-label" class="property-label"><g:message code="propertyDemand.specifyCity.label" default="specifyCity"/></span>
						<f:display bean="propertyDemand" property="specifyCity"/>
						<g:if test="${isBuyDem}">
							<span id="isCityRequired-label" class="property-label"><g:message code="propertyDemand.isCityRequired.label" default="isCityRequired"/></span>
							<f:display bean="propertyDemand" property="isCityRequired"/>
						</g:if>
						<span id="neighborhood-label" class="property-label"><g:message code="propertyDemand.neighborhood.label" default="neighborhood"/></span>
						<f:display bean="propertyDemand" property="neighborhood"/>
						<span id="specifyNeighborhood-label" class="property-label"><g:message code="propertyDemand.specifyNeighborhood.label" default="specifyNeighborhood"/></span>
						<f:display bean="propertyDemand" property="specifyNeighborhood"/>
						<g:if test="${isBuyDem}">
							<span id="isNeighborhoodRequired-label" class="property-label"><g:message code="propertyDemand.isNeighborhoodRequired.label" default="isNeighborhoodRequired"/></span>
							<f:display bean="propertyDemand" property="isNeighborhoodRequired"/>
						</g:if>
						<span id="zone-label" class="property-label"><g:message code="propertyDemand.zone.label" default="zone"/></span>
						<f:display bean="propertyDemand" property="zone"/>
						<span id="specifyZone-label" class="property-label"><g:message code="propertyDemand.specifyZone.label" default="specifyZone"/></span>
						<f:display bean="propertyDemand" property="specifyZone"/>
						<g:if test="${isBuyDem}">
							<span id="isZoneRequired-label" class="property-label"><g:message code="propertyDemand.isZoneRequired.label" default="isZoneRequired"/></span>
							<f:display bean="propertyDemand" property="isZoneRequired"/>	
							<span id="propertyMinArea-label" class="property-label"><g:message code="propertyDemand.propertyMinArea.show.label" default="propertyMinArea" args="[propertyDemand?.propertyType?.dimensionMeasuringUnit?.abbreviationInPlural]"/></span>
							<f:display bean="propertyDemand" property="propertyMinArea"/>
							<span id="propertyMaxArea-label" class="property-label"><g:message code="propertyDemand.propertyMaxArea.show.label" default="propertyMaxArea" args="[propertyDemand?.propertyType?.dimensionMeasuringUnit?.abbreviationInPlural]"/></span>
							<f:display bean="propertyDemand" property="propertyMaxArea"/>
						</g:if>	
						<g:if test="${isSellDem}">
							<span id="propertyArea-label" class="property-label"><g:message code="propertyDemand.propertyArea.show.label" default="propertyArea" args="[propertyDemand?.propertyType?.dimensionMeasuringUnit?.abbreviationInPlural]"/></span>
							<f:display bean="propertyDemand" property="propertyArea"/>
							<span id="buildingArea-label" class="property-label"><g:message code="propertyDemand.buildingArea.show.label" default="buildingArea" args="[propertyDemand?.buildingType?.dimensionMeasuringUnit?.abbreviationInPlural]"/></span>
							<f:display bean="propertyDemand" property="buildingArea"/>
						</g:if>
						<g:if test="${isBuyDem}">
							<span id="isAreaRequired-label" class="property-label"><g:message code="propertyDemand.isAreaRequired.label" default="isAreaRequired"/></span>
							<f:display bean="propertyDemand" property="isAreaRequired"/>
						</g:if>
						<span id="specifyPropertyFeatures-label" class="property-label"><g:message code="propertyDemand.specifyPropertyFeatures.label" default="specifyPropertyFeatures"/></span>
						<f:display bean="propertyDemand" property="specifyPropertyFeatures"/>
						<span id="specifyBuildingFeatures-label" class="property-label"><g:message code="propertyDemand.specifyBuildingFeatures.label" default="specifyBuildingFeatures"/></span>
						<f:display bean="propertyDemand" property="specifyBuildingFeatures"/>
						<span id="timeAvailability-label" class="property-label"><g:message code="propertyDemand.timeAvailability.label" default="timeAvailability"/></span>
						<f:display bean="propertyDemand" property="timeAvailability"/>
						<g:if test="${isBuyDem}">
							<!--<span id="offersOnly-label" class="property-label"><g:message code="propertyDemand.offersOnly.label" default="offersOnly"/></span>-->
							<!--<f:display bean="propertyDemand" property="offersOnly"/>-->
						</g:if>
						<g:if test="${isSellDem}">
							<span id="price-label" class="property-label"><g:message code="propertyDemand.price.label" default="price"/></span>
						</g:if>
						<g:else>
							<span id="maxPrice-label" class="property-label"><g:message code="propertyDemand.maxPrice.label" default="Max Price"/></span>
						</g:else>
						<f:display bean="propertyDemand" property="price"/>
						<span id="specifyPrice-label" class="property-label"><g:message code="propertyDemand.specifyPrice.label" default="Specify Price"/></span>
						<f:display bean="propertyDemand" property="specifyPrice"/>
						<g:if test="${isBuyDem}">
							<!--<span id="isPriceRequired-label" class="property-label"><g:message code="propertyDemand.isPriceRequired.label" default="isPriceRequired"/></span>-->
							<!--<f:display bean="propertyDemand" property="isPriceRequired"/>-->
						</g:if>
						<span id="currency-label" class="property-label"><g:message code="propertyDemand.currency.label" default="Currency"/></span>
						<f:display bean="propertyDemand" property="currency"/>
						<g:if test="${isSellDem}">
							<span id="broadcastMedia-label" class="property-label"><g:message code="propertyDemand.broadcastMedia.label" default="broadcastMedia"/></span>
							<f:display bean="propertyDemand" property="broadcastMedia"/>
							<span id="specifyBroadcastMedia-label" class="property-label"><g:message code="propertyDemand.specifyBroadcastMedia.label" default="specifyBroadcastMedia"/></span>
							<f:display bean="propertyDemand" property="specifyBroadcastMedia"/>
						</g:if>
						<span id="additionalDescription-label" class="property-label"><g:message code="propertyDemand.additionalDescription.label" default="additionalDescription"/></span>
						<f:display bean="propertyDemand" property="additionalDescription"/>
						<!--<f:display bean="propertyDemand" property="usage"/><f:display bean="propertyDemand" property="specifyUsage"/><!--<f:display bean="propertyDemand" property="isUsageRequired"/>-->
					</div>
				</fieldset>
				<g:if test="${isSellDem}">     
		            <h1><g:message code="propertyDemand.buy.smart.matches.title"/></h1>
		            <f:table collection="${propertyDemand.getSmartMatchesForSellDemand()}" properties="['propertyType','buildingType','city','price','currency','interestLevel']" />
	            </g:if>
	            <g:else>     
		            <h1><g:message code="propertyDemand.sell.smart.matches.title"/></h1>
		            <f:table collection="${propertyDemand.getSmartMatchesForBuyDemand()}" properties="['title', 'propertyType','price','currency','area','builtArea']" />
	            </g:else>
            <g:form resource="${this.propertyDemand}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.propertyDemand}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    <g:if test="${isSellDem}">
                    	<g:link class="addConcession" action="create" controller="concession" params="[pdid:propertyDemand.id]"><g:message code="propertyDemand.button.add.concession.from.pd.label" default="Add Concession" /></g:link>
                	</g:if>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
