<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'keyWord.label', default: 'KeyWord')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-keyWord" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-keyWord" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <fieldset class="fieldcontain">
            	<span id="keys-label" class="property-label"><g:message code="keyWord.keys.label" default="Keys"/></span>
		        <f:display bean="keyWord" property="keys"/>
		        <span id="locale-label" class="property-label"><g:message code="keyWord.locale.label" default="Locale"/></span>
		        <f:display bean="keyWord" property="locale"/>
		        <span id="propertyType-label" class="property-label"><g:message code="keyWord.propertyType.label" default="Property Type"/></span>
		        <f:display bean="keyWord" property="propertyType"/>
            </fieldset>
            
            <g:form resource="${this.keyWord}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.keyWord}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
