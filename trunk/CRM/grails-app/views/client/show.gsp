<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'client.label', default: 'Client')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-client" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-client" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="client.name.label" default="Name"/></span>
		        <f:display bean="client" property="name"/>
		        <span id="name-label" class="property-label"><g:message code="client.IDNumber.label" default="IDNumber"/></span>
		        <f:display bean="client" property="IDNumber"/>
		        <span id="name-label" class="property-label"><g:message code="client.birthDate.label" default="BirthDate"/></span>
		        <f:display bean="client" property="birthDate"/>
		        <span id="name-label" class="property-label"><g:message code="client.description.label" default="Description"/></span>
		        <f:display bean="client" property="description"/>
		        <span id="name-label" class="property-label"><g:message code="client.phone.label" default="Phone"/></span>
		        <f:display bean="client" property="phone"/>
		        <span id="name-label" class="property-label"><g:message code="client.phone2.label" default="Secundary phone"/></span>
		        <f:display bean="client" property="phone2"/>
		        <span id="name-label" class="property-label"><g:message code="client.notificationPhone.label" default="Notification phone"/></span>
		        <f:display bean="client" property="notificationPhone"/>
		        <span id="name-label" class="property-label"><g:message code="client.emailAddress.label" default="Email Address"/></span>
		        <f:display bean="client" property="emailAddress"/>
		        <span id="name-label" class="property-label"><g:message code="client.timeAvailability.label" default="Time Availability"/></span>
		        <f:display bean="client" property="timeAvailability"/>
		        <span id="name-label" class="property-label"><g:message code="client.country.label" default="Country"/></span>
		        <f:display bean="client" property="country"/>
		        <span id="name-label" class="property-label"><g:message code="client.gender.label" default="Gender"/></span>
		        <f:display bean="client" property="gender"/>
		        <span id="name-label" class="property-label"><g:message code="client.profession.label" default="Profession"/></span>
		        <f:display bean="client" property="profession"/>
		        <span id="name-label" class="property-label"><g:message code="client.maritalStatus.label" default="MaritalStatus"/></span>
		        <f:display bean="client" property="maritalStatus"/>
		        <span id="name-label" class="property-label"><g:message code="client.crmUser.label" default="Owner"/></span>
		        <f:display bean="client" property="crmUser"/>
		        <span id="name-label" class="property-label"><g:message code="client.category.label" default="Category"/></span>
		        <f:display bean="client" property="category"/>
		        <span id="name-label" class="property-label"><g:message code="client.readsEmail.label" default="Reads Email"/></span>
		        <f:display bean="client" property="readsEmail"/>
		        <span id="name-label" class="property-label"><g:message code="client.readsSms.label" default="Reads Sms"/></span>
		        <f:display bean="client" property="readsSms"/>
		        <span id="name-label" class="property-label"><g:message code="client.receiveNotifications.label" default="Receive Notifications"/></span>
		        <f:display bean="client" property="receiveNotifications"/>
		        <span id="name-label" class="property-label"><g:message code="client.isProspectiveClient.label" default="Prospective Client"/></span>
		        <f:display bean="client" property="isProspectiveClient"/>
            </fieldset>
            <h1><g:message code="address.label"/></h1>
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="address.streetOne.label" default="Street One"/></span>
		        <f:display bean="client" property="address.streetOne"/>
		        <span id="name-label" class="property-label"><g:message code="address.streetTwo.label" default="Street Two"/></span>
		        <f:display bean="client" property="address.streetTwo"/>
		        <span id="name-label" class="property-label"><g:message code="address.addressLine.label" default="Address"/></span>
		        <f:display bean="client" property="address.addressLine"/>
		        <span id="name-label" class="property-label"><g:message code="address.reference.label" default="Reference"/></span>
		        <f:display bean="client" property="address.reference"/>
		        <span id="name-label" class="property-label"><g:message code="address.description.label" default="Description"/></span>
		        <f:display bean="client" property="address.description"/>
		        <span id="name-label" class="property-label"><g:message code="address.code.label" default="Zip Code"/></span>
		        <f:display bean="client" property="address.code"/>
		        <span id="name-label" class="property-label"><g:message code="address.latitude.label" default="Latitude"/></span>
		        <f:display bean="client" property="address.latitude"/>
		        <span id="name-label" class="property-label"><g:message code="address.longitude.label" default="Longitude"/></span>
		        <f:display bean="client" property="address.longitude"/>
		        <span id="name-label" class="property-label"><g:message code="address.homePhone.label" default="Home Phone"/></span>
		        <f:display bean="client" property="address.homePhone"/>
		        <span id="name-label" class="property-label"><g:message code="address.city.label" default="City"/></span>
		        <f:display bean="client" property="address.city"/>
		        <span id="name-label" class="property-label"><g:message code="address.neighborhood.label" default="Neighborhood"/></span>
		        <f:display bean="client" property="address.neighborhood"/>
		        <span id="name-label" class="property-label"><g:message code="address.zone.label" default="Zone"/></span>
		        <f:display bean="client" property="address.zone"/>
            </fieldset>
            
            <g:form resource="${this.client}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.client}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
