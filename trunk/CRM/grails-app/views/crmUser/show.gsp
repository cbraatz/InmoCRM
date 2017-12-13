<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'crmUser.label', default: 'CrmUser')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-crmUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-crmUser" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="crmUser.login.label" default="Name"/></span>
		        <f:display bean="crmUser" property="login"/>
		        <span id="name-label" class="property-label"><g:message code="crmUser.emailAddress.label" default="Email"/></span>
		        <f:display bean="crmUser" property="emailAddress"/>
		        <span id="name-label" class="property-label"><g:message code="crmUser.partner.label" default="Partner"/></span>
		        <f:display bean="crmUser" property="partner"/>
		        <span id="name-label" class="property-label"><g:message code="crmUser.isActive.label" default="Active"/></span>
		        <f:display bean="crmUser" property="isActive"/>
		        <span id="name-label" class="property-label"><g:message code="crmUser.hasAccess.label" default="Has access"/></span>
		        <f:display bean="crmUser" property="hasAccess"/>
		        <span id="name-label" class="property-label"><g:message code="crmUser.addedBy.label" default="Added By"/></span>
		        <f:display bean="crmUser" property="addedBy"/>
            </fieldset>
            
            <g:form resource="${this.crmUser}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.crmUser}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                	<g:if test="${crmUser.partner.isAgent}">
                		<g:link class="agentComment" action="create" controller="agentComment" params="[cid:crmUser.id]"><g:message code="crmUser.button.agentComment.label" default="Add Web Comment" /></g:link>
                	</g:if>
                </fieldset>
            </g:form>
            <g:if test="${!crmUser.agentComments.isEmpty()}">
	            <h1><g:message code="agentComment.list.label"/></h1>
	        	<f:table collection="${crmUser.agentComments}" properties="['contact', 'locale']"/>
        	</g:if>
        </div>
    </body>
</html>
