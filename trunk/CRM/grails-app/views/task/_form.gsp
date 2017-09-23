<%@ page import="crm.NotificationMethod" %>
<fieldset class="form">
	<f:field bean="task" property="name"/>
	<f:field bean="task" property="description">
		<g:textArea name="${property}" maxlength="500" value="${it.value}" rows="10" cols="50"/>
	</f:field>
	<f:field bean="task" property="dateTime"/>
	<f:field bean="task" property="minutesBeforeNotification"/>
	<f:field bean="task" property="priorityLevel" widget-propId="${task?.priorityLevel?.id}"/>
	<f:field bean="task" property="assignee" widget-propId="${task?.assignee?.id}"/>
	<f:field bean="task" property="taskStatus" widget-propId="${task?.taskStatus?.id}"/>

	<g:hiddenField name="creator.id" value="${session?.user?.id}"/>
	<g:hiddenField name="internalID" value="${task?.internalID}"/>
	<div class="fieldcontain">
	<label class="property-label" id="manyToMany-label"><g:message code="task.notificationMethods.label"/></label>
	<g:render template="/notificationMethod/notificationMethodsForm"/>
	</div>
</fieldset>
