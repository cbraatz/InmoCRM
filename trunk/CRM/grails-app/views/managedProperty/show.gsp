<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'managedProperty.label', default: 'ManagedProperty')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-managedProperty" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
        	<g:set var="conc" value="${this.managedProperty.getActiveConcession()}" />
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <g:if test="${conc}">
                	<li><g:link class="return" action="show" controller="concession" id="${conc.id}"><g:message code="concession.label" default="Concession" /></g:link></li>
                </g:if>
            </ul>
        </div>
        <div id="show-managedProperty" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            	<div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.managedProperty}">
	            <ul class="errors" role="alert">
	                <g:eachError bean="${this.managedProperty}" var="error">
	                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
	                </g:eachError>
	            </ul>
            </g:hasErrors>
            
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="managedProperty.propertyType.label" default="Property Type"/></span>
				<f:display bean="managedProperty" property="propertyType"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.title.label" default="Title"/></span>
				<f:display bean="managedProperty" property="title"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.propertyDescription.label" default="propertyDescription"/></span>
				<f:display bean="managedProperty" property="propertyDescription"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.measures.label" default="measures"/></span>
				<f:display bean="managedProperty" property="measures"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.area.label" default="area"/></span>
				<f:display bean="managedProperty" property="area"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.excess.label" default="excess"/></span>
				<f:display bean="managedProperty" property="excess"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.publicAddress.label" default="publicAddress"/></span>
				<f:display bean="managedProperty" property="publicAddress"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.publicCashPrice.label" default="publicCashPrice"/></span>
				<f:display bean="managedProperty" property="publicCashPrice"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.publicCreditPrice.label" default="publicCreditPrice"/></span>
				<f:display bean="managedProperty" property="publicCreditPrice"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.currency.label" default="currency"/></span>
				<f:display bean="managedProperty" property="currency"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.price.label" default="price"/></span>
				<f:display bean="managedProperty" property="price"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.value.label" default="value"/></span>
				<f:display bean="managedProperty" property="value"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.clientInitialPrice.label" default="clientInitialPrice"/></span>
				<f:display bean="managedProperty" property="clientInitialPrice"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.commissionAmount.label" default="commission Amount"/></span>
				<f:display bean="managedProperty" property="commissionAmount"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.valueDegree.label" default="valueDegree"/></span>
				<f:display bean="managedProperty" property="valueDegree"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.placedBillboards.label" default="placedBillboards"/></span>
				<f:display bean="managedProperty" property="placedBillboards"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.soldByUs.label" default="Sold by Us"/></span>
				<f:display bean="managedProperty" property="soldByUs"/>
			</fieldset>
			
			 <h1><g:message code="address.label"/></h1>
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="address.streetOne.label" default="Street One"/></span>
		        <f:display bean="managedProperty" property="address.streetOne"/>
		        <span id="name-label" class="property-label"><g:message code="address.streetTwo.label" default="Street Two"/></span>
		        <f:display bean="managedProperty" property="address.streetTwo"/>
		        <span id="name-label" class="property-label"><g:message code="address.addressLine.label" default="Address"/></span>
		        <f:display bean="managedProperty" property="address.addressLine"/>
		        <span id="name-label" class="property-label"><g:message code="address.reference.label" default="Reference"/></span>
		        <f:display bean="managedProperty" property="address.reference"/>
		        <span id="name-label" class="property-label"><g:message code="address.description.label" default="Description"/></span>
		        <f:display bean="managedProperty" property="address.description"/>
		        <span id="name-label" class="property-label"><g:message code="address.code.label" default="Zip Code"/></span>
		        <f:display bean="managedProperty" property="address.code"/>
		        <span id="name-label" class="property-label"><g:message code="address.latitude.label" default="Latitude"/></span>
		        <f:display bean="managedProperty" property="address.latitude"/>
		        <span id="name-label" class="property-label"><g:message code="address.longitude.label" default="Longitude"/></span>
		        <f:display bean="managedProperty" property="address.longitude"/>
		        <span id="name-label" class="property-label"><g:message code="address.homePhone.label" default="Home Phone"/></span>
		        <f:display bean="managedProperty" property="address.homePhone"/>
		        <span id="name-label" class="property-label"><g:message code="address.city.label" default="City"/></span>
		        <f:display bean="managedProperty" property="address.city"/>
		        <span id="name-label" class="property-label"><g:message code="address.neighborhood.label" default="Neighborhood"/></span>
		        <f:display bean="managedProperty" property="address.neighborhood"/>
		        <span id="name-label" class="property-label"><g:message code="address.zone.label" default="Zone"/></span>
		        <f:display bean="managedProperty" property="address.zone"/>
            </fieldset>
			<g:if test="${managedProperty.buildings.size() > 0}">
				<h1><g:message code="managedProperty.buildings.label"/></h1>
	            <f:table collection="${managedProperty.buildings}" properties="['buildingType','builtSize', 'builtYear', 'buildingCondition', 'buildingDescription']" />
	       </g:if>
	       <g:if test="${managedProperty.featuresByProperty.size() > 0}">     
	            <h1><g:message code="managedProperty.featuresByProperty.title"/></h1>
	            <f:table collection="${managedProperty.featuresByProperty}" properties="['propertyFeature','value', 'description']" />
            </g:if>
            
            <g:form resource="${this.managedProperty}">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.managedProperty}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit action="addEditImages" class="addEditImages" value="${message(code: 'default.button.add.edit.images.label', default: 'Add or Edit Images')}"/>
                	<g:if test="${managedProperty.hasImagesForWeb()}">
                		<g:link class="web" action="create" controller="webPage" params="[pid:managedProperty.id]"><g:message code="managedProperty.button.web.label" default="Web Page" /></g:link>
                    </g:if>
                    <!--<g:actionSubmit action="delete" class="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />  -->
                </fieldset>
            </g:form>
        </div>
    </body>
</html>