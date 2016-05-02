
<fieldset class="form">
	<f:field bean="action" property="date"/>
	<f:field bean="action" property="description"/>
	<f:field bean="action" property="actionType" input-propId="${action?.actionType?.id}"/>
	<f:field bean="action" property="partner" input-propId="${action?.partner?.id}" input-allowNull="${true}"/>
	<f:field bean="action" property="cost"/>
	<f:field bean="action" property="currency" input-propId="${action?.currency?.id}" input-allowNull="${true}"/>
	<g:hiddenField name="concession" value="${action?.concession?.id}"/>
</fieldset>