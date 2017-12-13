<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'address.label', default: 'Address')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-address" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-address" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="address.streetOne.label" default="Street One"/></span>
		        <f:display bean="address" property="streetOne"/>
		        <span id="name-label" class="property-label"><g:message code="address.streetTwo.label" default="Street Two"/></span>
		        <f:display bean="address" property="streetTwo"/>
		        <span id="name-label" class="property-label"><g:message code="address.addressLine.label" default="Address"/></span>
		        <f:display bean="address" property="addressLine"/>
		        <span id="name-label" class="property-label"><g:message code="address.reference.label" default="Reference"/></span>
		        <f:display bean="address" property="reference"/>
		        <span id="name-label" class="property-label"><g:message code="address.description.label" default="Description"/></span>
		        <f:display bean="address" property="description"/>
		        <span id="name-label" class="property-label"><g:message code="address.code.label" default="Zip Code"/></span>
		        <f:display bean="address" property="code"/>
		        <span id="name-label" class="property-label"><g:message code="address.latitude.label" default="Latitude"/></span>
		        <f:display bean="address" property="latitude"/>
		        <span id="name-label" class="property-label"><g:message code="address.longitude.label" default="Longitude"/></span>
		        <f:display bean="address" property="longitude"/>
		        <span id="name-label" class="property-label"><g:message code="address.homePhone.label" default="Home Phone"/></span>
		        <f:display bean="address" property="homePhone"/>
		        <span id="name-label" class="property-label"><g:message code="address.city.label" default="City"/></span>
		        <f:display bean="address" property="city"/>
		        <span id="name-label" class="property-label"><g:message code="address.zone.label" default="Zone"/></span>
		        <f:display bean="address" property="zone"/>
		        <span id="name-label" class="property-label"><g:message code="address.neighborhood.label" default="Neighborhood"/></span>
		        <f:display bean="address" property="neighborhood"/>
		        
            </fieldset>
            
            <g:form resource="${this.address}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.address}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
