<%@ page import="crm.CrmUser" %>
<%@ page import="crm.Task" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="home.page.label"/></title>
        <g:set var="usr" value="${CrmUser.getAll().first().login}" />
    </head>
    <body>
        <div id="list-home" class="content scaffold-list" role="main">
            <h1><g:message code="task.current.user.list" args="[usr]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <f:table collection="${Task.getAll()}"  properties="['name', 'assignee', 'dateTime', 'description']"/>

            <div class="pagination">
                <g:paginate total="${incomeCount ?: 0}" />
            </div>
        </div>
    </body>
</html>