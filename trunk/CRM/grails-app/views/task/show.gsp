<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-task" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-task" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
			<fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="task.name.label" default="Name"/></span>
		        <f:display bean="task" property="name"/>
		        <span id="description-label" class="property-label"><g:message code="task.description.label" default="Description"/></span>
		        <f:display bean="task" property="description"/>
		        <span id="creator-label" class="property-label"><g:message code="task.creator.label" default="Creator"/></span>
		        <f:display bean="task" property="creator"/>
		        <span id="assignee-label" class="property-label"><g:message code="task.assignee.label" default="Assignee"/></span>
		        <f:display bean="task" property="assignee"/>
		        <span id="dateTime-label" class="property-label"><g:message code="task.dateTime.label" default="Date and Time"/></span>
		        <f:display bean="task" property="dateTime"/>
		        <span id="minutesBeforeNotification-label" class="property-label"><g:message code="task.minutesBeforeNotification.label" default="Minutes Before Notification"/></span>
		        <f:display bean="task" property="minutesBeforeNotification"/>
		        <span id="priorityLevel-label" class="property-label"><g:message code="task.priorityLevel.label" default="Priority Level"/></span>
		        <f:display bean="task" property="priorityLevel"/>
		        <span id="taskStatus-label" class="property-label"><g:message code="task.taskStatus.label" default="Task Status"/></span>
		        <f:display bean="task" property="taskStatus"/>
		        <span id="notificationMethods-label" class="property-label"><g:message code="task.notificationMethods.label" default="Notification Methods"/></span>
		        <f:display bean="task" property="notificationMethods"/>
		        <span id=internalID-label" class="property-label"><g:message code="task.internalID.label" default="Internal ID"/></span>
		        <f:display bean="task" property="internalID"/>
            </fieldset>

            <g:form resource="${this.task}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.task}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
