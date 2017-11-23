<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'webPage.label', default: 'WebPage')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-webPage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <g:if test="${managedProperty}">
                	<li><g:link class="return" action="show" controller="managedProperty" id="${managedProperty?.id}"><g:message code="managedProperty.label" default="Managed Property" /></g:link></li>
                </g:if>
            </ul>
        </div>
        <div id="list-webPage" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${managedProperty.webPages}" properties="['title', 'domain', 'operationType', 'price']"/>

            <div class="pagination">
                <g:paginate total="${webPageCount ?: 0}" />
            </div>
        </div>
    </body>
</html>