
<fieldset class="form">
	<f:field bean="action" property="date"/>
	<f:field bean="action" property="description"/>
	<f:field bean="action" property="actionType" widget-propId="${action?.actionType?.id}"/>
	<f:field bean="action" property="crmUser" widget-propId="${action?.crmUser?.id}" widget-allowNull="${true}"/>
	<f:field bean="action" property="cost"/>
	<f:field bean="action" property="currency" widget-propId="${action?.currency?.id}" widget-allowNull="${true}"/>
	<g:hiddenField name="managedProperty" value="${action?.managedProperty?.id}"/>
</fieldset>