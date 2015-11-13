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
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-managedProperty" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="managedProperty.propertyType.label" default="Property Type"/></span>
				<f:display bean="managedProperty" property="propertyType"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.owner.label" default="owner"/></span>
				<f:display bean="managedProperty" property="owner"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.title.label" default="title"/></span>
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
				<span id="name-label" class="property-label"><g:message code="managedProperty.valueDegree.label" default="valueDegree"/></span>
				<f:display bean="managedProperty" property="valueDegree"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.placedBillboards.label" default="placedBillboards"/></span>
				<f:display bean="managedProperty" property="placedBillboards"/>
				<span id="name-label" class="property-label"><g:message code="managedProperty.inWeb.label" default="inWeb"/></span>
				<f:display bean="managedProperty" property="inWeb"/>
			</fieldset>

            <g:form resource="${this.managedProperty}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.managedProperty}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
