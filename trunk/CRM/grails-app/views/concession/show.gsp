<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'concession.label', default: 'Concession')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-concession" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-concession" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
           
			<fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="concession.startDate.label" default="Start Date"/></span>
		        <f:display bean="concession" property="startDate"/>
		        <span id="name-label" class="property-label"><g:message code="concession.endDate.label" default="End Date"/></span>
				<f:display bean="concession" property="endDate"/>
				<span id="name-label" class="property-label"><g:message code="concession.commissionAmount.label" default="Commission Amount"/></span>
				<f:display bean="concession" property="commissionAmount"/>
				<span id="name-label" class="property-label"><g:message code="concession.commissionPercentage.label" default="Commission Percentage"/></span>
				<f:display bean="concession" property="commissionPercentage"/>
				<span id="name-label" class="property-label"><g:message code="concession.client.label" default="Client"/></span>
				<f:display bean="concession" property="client"/>
				<span id="name-label" class="property-label"><g:message code="concession.description.label" default="Description"/></span>
				<f:display bean="concession" property="description"/>
				<span id="name-label" class="property-label"><g:message code="concession.barter.label" default="Barter"/></span>
				<f:display bean="concession" property="barter"/>
				<span id="name-label" class="property-label"><g:message code="concession.financing.label" default="Financing"/></span>
				<f:display bean="concession" property="financing"/>
				<span id="name-label" class="property-label"><g:message code="concession.isNegotiable.label" default="Is Negotiable"/></span>
				<f:display bean="concession" property="isNegotiable"/>
				<span id="name-label" class="property-label"><g:message code="concession.publishInMLS.label" default="Publish in MLS"/></span>
				<f:display bean="concession" property="publishInMLS"/>
				<span id="name-label" class="property-label"><g:message code="concession.publishInPortals.label" default="Publish in Portals"/></span>
				<f:display bean="concession" property="publishInPortals"/>
				<span id="name-label" class="property-label"><g:message code="concession.agent.label" default="Agent"/></span>
				<f:display bean="concession" property="agent"/>
				<span id="name-label" class="property-label"><g:message code="concession.isForRent.label" default="Is For Rent"/></span>
				<f:display bean="concession" property="isForRent"/>
            </fieldset>
            <h1><g:message code="concession.managedProperties.label"/></h1>
            <f:table collection="${concession.managedProperties}" properties="['title', 'area', 'price', 'currency', 'owner']"/>
            <g:form resource="${this.concession}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.concession}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    <g:link class="action" action="create" controller="action" params="[cid:concession.id]"><g:message code="concession.button.action.label" default="Add Action" /></g:link>
                    <g:link class="contact" action="create" controller="contact" params="[cid:concession.id]"><g:message code="concession.button.contact.label" default="Add Contact" /></g:link>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
