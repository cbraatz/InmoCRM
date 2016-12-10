<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'contextPermissionCategory.label', default: 'ContextPermissionCategory')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-contextPermissionCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-contextPermissionCategory" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.contextPermissionCategory}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.contextPermissionCategory}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
			<fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="contextPermissionCategory.name.label" default="Name"/></span>
		        <f:display bean="contextPermissionCategory" property="name"/>
            </fieldset>

            <g:form resource="${this.contextPermissionCategory}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.contextPermissionCategory}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:link class="define" action="define" controller="contextPermissionCategory" id="${contextPermissionCategory.id}"><g:message code="${(contextPermissionCategory.isAll==true || contextPermissionCategory.isNone==true ? 'contextPermissionCategory.button.view.permissions.label':'contextPermissionCategory.button.edit.permissions.label')}" default="Define"/></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
