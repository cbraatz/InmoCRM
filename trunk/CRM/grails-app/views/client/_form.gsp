
<fieldset class="form">
	<f:field bean="client" property="name"/>
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
	<f:field bean="client" property="country" widget-propId="${client?.country?.id}"/>
	<f:field bean="client" property="gender" widget-propId="${client?.gender?.id}"/>
	<f:field bean="client" property="profession" widget-propId="${client?.profession?.id}" widget-allowNull="${true}"/>
	<f:field bean="client" property="maritalStatus" widget-propId="${client?.maritalStatus?.id}"/>
	<f:field bean="client" property="crmUser" widget-propId="${client?.crmUser?.id}" input-propName="crmUser"/>
	<f:field bean="client" property="category" widget-propId="${client?.category?.id}" input-propName="clientCategory"/>
	<f:field bean="client" property="readsEmail"/>
	<f:field bean="client" property="readsSms"/>
	<f:field bean="client" property="receiveNotifications"/>
	<f:field bean="client" property="isProspectiveClient"/>
	<g:hiddenField name="isActive" value="${client?.isActive}"/>
</fieldset>
	<h1><g:message code="address.label"/></h1>
	<g:render template="/address/multiForm" model="['parentBean':'client']"/>