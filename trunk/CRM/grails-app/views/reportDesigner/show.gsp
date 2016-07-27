<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'reportDesigner.label', default: 'Report Designer')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-reportDesigner" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-reportDesigner" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="reportDesigner.name.label" default="Name"/></span>
		        <f:display bean="reportDesigner" property="name"/>
		        <span id="description-label" class="property-label"><g:message code="reportDesigner.description.label" default="Description"/></span>
		        <f:display bean="reportDesigner" property="description"/>
		        <span id="reportFolder-label" class="property-label"><g:message code="reportDesigner.reportFolder.label" default="Report Folder"/></span>
		        <f:display bean="reportDesigner" property="reportFolder"/>
            </fieldset>
            
            <g:form resource="${this.reportDesigner}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="step2" resource="${this.reportDesigner}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:link class="run" action="run" resource="${this.reportDesigner}"><g:message code="default.button.run.label" default="Run" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
