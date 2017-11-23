<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'soldProperty.label', default: 'Sold Property')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-soldProperty" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-soldProperty" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
			<fieldset class="fieldcontain">
            	<span id="date-label" class="property-label"><g:message code="soldProperty.date.label" default="Date"/></span>
		        <f:display bean="soldProperty" property="date"/>
		        <span id="managedProperty-label" class="property-label"><g:message code="soldProperty.managedProperty.label" default="managedProperty"/></span>
		        <f:display bean="soldProperty" property="managedProperty.title"/>
		        <span id="sellPrice-label" class="property-label"><g:message code="soldProperty.sellPrice.label" default="sellPrice"/></span>
		        <f:display bean="soldProperty" property="sellPrice"/>
		        <span id="commissionAmount-label" class="property-label"><g:message code="soldProperty.commissionAmount.label" default="commissionAmount"/></span>
		        <f:display bean="soldProperty" property="commissionAmount"/>
		        <span id="currency-label" class="property-label"><g:message code="soldProperty.currency.label" default="currency"/></span>
		        <f:display bean="soldProperty" property="currency"/>
		        <span id="propertyDemand-label" class="property-label"><g:message code="soldProperty.propertyDemand.label" default="propertyDemand"/></span>
		        <f:display bean="soldProperty" property="propertyDemand.id"/>
		        <span id="crmUser-label" class="property-label"><g:message code="soldProperty.crmUser.label" default="crmUser"/></span>
		        <f:display bean="soldProperty" property="crmUser"/>
		        <span id="client-label" class="property-label"><g:message code="soldProperty.client.label" default="client"/></span>
		        <f:display bean="soldProperty" property="client"/>
		        <span id="isConfirmed-label" class="property-label"><g:message code="soldProperty.isConfirmed.label" default="isConfirmed"/></span>
		        <f:display bean="soldProperty" property="isConfirmed"/>
            </fieldset>

            <g:form resource="${this.soldProperty}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.soldProperty}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
