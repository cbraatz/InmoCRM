<fieldset class="form">
	<f:field bean="soldProperty" property="date"/>
	<f:field bean="soldProperty" property="sellPrice"/>
	<f:field bean="soldProperty" property="commissionAmount"/>
	<f:field bean="soldProperty" property="currency" widget-propId="${soldProperty?.currency?.id}"/>
	<f:field bean="soldProperty" property="propertyDemand" widget-propId="${soldProperty?.propertyDemand?.id}"/>
	<f:field bean="soldProperty" property="crmUser" widget-propId="${soldProperty?.crmUser?.id}"/>
	<f:field bean="soldProperty" property="client" widget-propId="${soldProperty?.client?.id}"/>
	<f:field bean="soldProperty" property="isConfirmed"/>
	<g:hiddenField name="managedProperty" value="${soldProperty?.managedProperty?.id}"/>
</fieldset>