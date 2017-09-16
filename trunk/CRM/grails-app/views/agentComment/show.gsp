<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'agentComment.label', default: 'AgentComment')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-agentComment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-agentComment" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
			<fieldset class="fieldcontain">
				<span id="contact-label" class="property-label"><g:message code="agentComment.contact.label" default="Contact"/></span>
		        <f:display bean="agentComment" property="contact"/>
            	<span id="locale-label" class="property-label"><g:message code="agentComment.locale.label" default="Locale"/></span>
		        <f:display bean="agentComment" property="locale"/>
		        <span id="crmUser-label" class="property-label"><g:message code="agentComment.crmUser.label" default="User"/></span>
		        <f:display bean="agentComment" property="crmUser"/>   
            </fieldset>

            <g:form resource="${this.agentComment}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.agentComment}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>