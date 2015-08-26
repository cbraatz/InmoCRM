
<fieldset class="form">
	<f:field bean="client" property="name"/>
	<f:field bean="client" property="lastName"/>
	<f:field bean="client" property="IDNumber"/>
	<f:field bean="client" property="birthDate"/>
	<f:field bean="client" property="description">
		<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="4" cols="50"/>
	</f:field>
	<f:field bean="client" property="phone"/>
	<f:field bean="client" property="phone2"/>
	<f:field bean="client" property="notificationPhone"/>
	<f:field bean="client" property="emailAddress"/>
	<f:field bean="client" property="timeAvailability"/>
	<f:field bean="client" property="nationality"/>
	<f:field bean="client" property="gender" input-propId="${client?.gender?.id}"/>
	<f:field bean="client" property="profession" input-propId="${client?.profession?.id}"/>
	<f:field bean="client" property="maritalStatus" input-propId="${client?.maritalStatus?.id}"/>
	<f:field bean="client" property="owner" input-propId="${client?.owner?.id}" input-propName="crmUser"/>
	<f:field bean="client" property="category" input-propId="${client?.category?.id}" input-propName="clientCategory"/>
	<f:field bean="client" property="isActive"/>
	<f:field bean="client" property="readsEmail"/>
	<f:field bean="client" property="readsSms"/>
	<f:field bean="client" property="receiveNotifications"/>
	<f:field bean="client" property="isProspectiveClient"/>
</fieldset>

<h1><g:message code="address.label"/></h1>
<g:render template="/address/multiForm" model="['parentBean':'client']"/>