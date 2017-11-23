
<fieldset class="form">
	<f:field bean="contact" property="date"/>
	<f:field bean="contact" property="description"/>
	<f:field bean="contact" property="contactType" widget-propId="${contact?.contactType?.id}"/>
	<f:field bean="contact" property="crmUser" widget-propId="${contact?.crmUser?.id}"/>
	<f:field bean="contact" property="client" widget-propId="${contact?.client?.id}"/>
	<g:hiddenField name="managedProperty" value="${contact?.managedProperty?.id}"/>
</fieldset>