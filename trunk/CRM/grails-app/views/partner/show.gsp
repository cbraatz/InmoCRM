<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'partner.label', default: 'Partner')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-partner" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-partner" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="partner.name.label" default="Name"/></span>
		        <f:display bean="partner" property="name"/>
		        <span id="name-label" class="property-label"><g:message code="partner.IDNumber.label" default="IDNumber"/></span>
		        <f:display bean="partner" property="IDNumber"/>
		        <span id="name-label" class="property-label"><g:message code="partner.birthDate.label" default="BirthDate"/></span>
		        <f:display bean="partner" property="birthDate"/>
		        <span id="name-label" class="property-label"><g:message code="partner.phone.label" default="Phone"/></span>
		        <f:display bean="partner" property="phone"/>
		        <span id="name-label" class="property-label"><g:message code="partner.corporatePhone.label" default="Corporate Phone"/></span>
		        <f:display bean="partner" property="corporatePhone"/>
		        <span id="name-label" class="property-label"><g:message code="partner.emailAddress.label" default="Email Address"/></span>
		        <f:display bean="partner" property="emailAddress"/>
		        <span id="name-label" class="property-label"><g:message code="partner.country.label" default="Nationality"/></span>
		        <f:display bean="partner" property="country"/>
		        <span id="name-label" class="property-label"><g:message code="partner.gender.label" default="Gender"/></span>
		        <f:display bean="partner" property="gender"/>
		        <span id="name-label" class="property-label"><g:message code="partner.profession.label" default="Profession"/></span>
		        <f:display bean="partner" property="profession"/>
		        <span id="name-label" class="property-label"><g:message code="partner.partnerRole.label" default="Partner Role"/></span>
		        <f:display bean="partner" property="partnerRole"/>
		        <span id="name-label" class="property-label"><g:message code="partner.maritalStatus.label" default="Marital Status"/></span>
		        <f:display bean="partner" property="maritalStatus"/>
		        <span id="name-label" class="property-label"><g:message code="partner.invitedBy.label" default="Invited By"/></span>
		        <f:display bean="partner" property="invitedBy"/>
		        <span id="name-label" class="property-label"><g:message code="partner.isAgent.label" default="Is Agent"/></span>
		        <f:display bean="partner" property="isAgent"/>
		        <span id="name-label" class="property-label"><g:message code="partner.isActive.label" default="Is Active"/></span>
		        <f:display bean="partner" property="isActive"/>
            </fieldset>
            <h1><g:message code="address.label"/></h1>
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="address.streetOne.label" default="Street One"/></span>
		        <f:display bean="partner" property="address.streetOne"/>
		        <span id="name-label" class="property-label"><g:message code="address.streetTwo.label" default="Street Two"/></span>
		        <f:display bean="partner" property="address.streetTwo"/>
		        <span id="name-label" class="property-label"><g:message code="address.addressLine.label" default="Address"/></span>
		        <f:display bean="partner" property="address.addressLine"/>
		        <span id="name-label" class="property-label"><g:message code="address.reference.label" default="Reference"/></span>
		        <f:display bean="partner" property="address.reference"/>
		        <span id="name-label" class="property-label"><g:message code="address.description.label" default="Description"/></span>
		        <f:display bean="partner" property="address.description"/>
		        <span id="name-label" class="property-label"><g:message code="address.code.label" default="Zip Code"/></span>
		        <f:display bean="partner" property="address.code"/>
		        <span id="name-label" class="property-label"><g:message code="address.latitude.label" default="Latitude"/></span>
		        <f:display bean="partner" property="address.latitude"/>
		        <span id="name-label" class="property-label"><g:message code="address.longitude.label" default="Longitude"/></span>
		        <f:display bean="partner" property="address.longitude"/>
		        <span id="name-label" class="property-label"><g:message code="address.homePhone.label" default="Home Phone"/></span>
		        <f:display bean="partner" property="address.homePhone"/>
		        <span id="name-label" class="property-label"><g:message code="address.city.label" default="City"/></span>
		        <f:display bean="partner" property="address.city"/>
		        <span id="name-label" class="property-label"><g:message code="address.neighborhood.label" default="Neighborhood"/></span>
		        <f:display bean="partner" property="address.neighborhood"/>
		        <span id="name-label" class="property-label"><g:message code="address.zone.label" default="Zone"/></span>
		        <f:display bean="partner" property="address.zone"/>
            </fieldset>
            
            
            <g:form resource="${this.partner}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.partner}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
