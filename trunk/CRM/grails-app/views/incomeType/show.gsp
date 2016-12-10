<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'incomeType.label', default: 'IncomeType')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-incomeType" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-incomeType" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
			<fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="incomeType.name.label" default="Name"/></span>
		        <f:display bean="incomeType" property="name"/>
		        <span id="name-label" class="property-label"><g:message code="incomeType.description.label" default="Description"/></span>
		        <f:display bean="incomeType" property="description"/>
		        <span id="name-label" class="property-label"><g:message code="incomeType.taxRate.label" default="Tax Rate"/></span>
		        <f:display bean="incomeType" property="taxRate"/>
		        <span id="name-label" class="property-label"><g:message code="incomeType.billingDefaultDescription.label" default="Billing Default Description"/></span>
		        <f:display bean="incomeType" property="billingDefaultDescription"/>
		        <span id="name-label" class="property-label"><g:message code="incomeType.relatedDomain.label" default="Related Domain"/></span>
		        <span class="property-value"><g:message code="ENUM.RelatedDomain.${incomeType.relatedDomain}"/></span> 
            </fieldset>
            
            <g:form resource="${this.incomeType}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.incomeType}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
