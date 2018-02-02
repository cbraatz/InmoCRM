<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'customReport.label', default: 'CustomReport')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-customReport" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-customReport" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="customReport.name.label" default="Name"/></span>
		        <f:display bean="customReport" property="name"/>
		        <span id="customReportTemplate-label" class="property-label"><g:message code="customReport.customReportTemplate.label" default="Custom Report Template"/></span>
		        <f:display bean="customReport" property="customReportTemplate"/>
		        <span id="reportFolder-label" class="property-label"><g:message code="customReport.reportFolder.label" default="Report Folder"/></span>
		        <f:display bean="customReport" property="reportFolder"/>
		        <span id="crmUser-label" class="property-label"><g:message code="customReport.crmUser.label" default="User"/></span>
		        <f:display bean="customReport" property="crmUser"/>
            </fieldset>

            <g:form resource="${this.customReport}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.customReport}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
