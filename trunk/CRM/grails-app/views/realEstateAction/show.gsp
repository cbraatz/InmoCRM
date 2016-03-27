<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'realEstateAction.label', default: 'RealEstateAction')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-realEstateAction" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            	<li><g:link class="return" action="show" controller="concession" id="${realEstateAction?.concession?.id}"><g:message code="concession.label" default="Concession" /></g:link></li>
            </ul>
        </div>
        <div id="show-realEstateAction" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
			<fieldset class="fieldcontain">
				<span id="concession-label" class="property-label"><g:message code="realEstateAction.concession.label" default="Concession"/></span>
		        <f:display bean="realEstateAction" property="concession.id"/>
            	<span id="date-label" class="property-label"><g:message code="realEstateAction.date.label" default="Date"/></span>
		        <f:display bean="realEstateAction" property="date"/>
		        <span id="description-label" class="property-label"><g:message code="realEstateAction.description.label" default="Description"/></span>
		        <f:display bean="realEstateAction" property="description"/>
		        <span id="realEstateActionType-label" class="property-label"><g:message code="realEstateAction.realEstateActionType.label" default="RealEstateActionType"/></span>
		        <f:display bean="realEstateAction" property="realEstateActionType"/>
		        <span id="partner-label" class="property-label"><g:message code="realEstateAction.partner.label" default="Partner"/></span>
		        <f:display bean="realEstateAction" property="partner"/>
		        <span id="cost-label" class="property-label"><g:message code="realEstateAction.cost.label" default="Cost"/></span>
		        <f:display bean="realEstateAction" property="cost"/>
		        <span id="currency-label" class="property-label"><g:message code="realEstateAction.currency.label" default="Currency"/></span>
		        <f:display bean="realEstateAction" property="currency"/>
            </fieldset>
            
            <g:form resource="${this.realEstateAction}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.realEstateAction}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
