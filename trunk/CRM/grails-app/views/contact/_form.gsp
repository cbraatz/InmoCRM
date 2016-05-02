
<fieldset class="form">
	<f:field bean="contact" property="date"/>
	<f:field bean="contact" property="description"/>
	<f:field bean="contact" property="contactType" input-propId="${contact?.contactType?.id}"/>
	<f:field bean="contact" property="partner" input-propId="${contact?.partner?.id}"/>
	<f:field bean="contact" property="client" input-propId="${contact?.client?.id}"/>
	<g:hiddenField name="concession" value="${contact?.concession?.id}"/>
</fieldset>