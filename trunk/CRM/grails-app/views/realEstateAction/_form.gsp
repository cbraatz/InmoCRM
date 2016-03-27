
<fieldset class="form">
	<f:field bean="realEstateAction" property="date"/>
	<f:field bean="realEstateAction" property="description"/>
	<f:field bean="realEstateAction" property="realEstateActionType" input-propId="${realEstateAction?.realEstateActionType?.id}"/>
	<f:field bean="realEstateAction" property="partner" input-propId="${realEstateAction?.partner?.id}" input-allowNull="${true}"/>
	<f:field bean="realEstateAction" property="cost"/>
	<f:field bean="realEstateAction" property="currency" input-propId="${realEstateAction?.currency?.id}" input-allowNull="${true}"/>
	<g:hiddenField name="concession" value="${realEstateAction?.concession?.id}"/>
</fieldset>