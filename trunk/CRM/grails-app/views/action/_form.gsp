
<fieldset class="form">
	<f:field bean="action" property="date"/>
	<f:field bean="action" property="description"/>
	<f:field bean="action" property="actionType" widget-propId="${action?.actionType?.id}"/>
	<f:field bean="action" property="partner" widget-propId="${action?.partner?.id}" widget-allowNull="${true}"/>
	<f:field bean="action" property="cost"/>
	<f:field bean="action" property="currency" widget-propId="${action?.currency?.id}" widget-allowNull="${true}"/>
	<g:hiddenField name="concession" value="${action?.concession?.id}"/>
</fieldset>