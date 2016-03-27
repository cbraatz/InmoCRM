
<fieldset class="form">
	<f:field bean="realEstateContact" property="date"/>
	<f:field bean="realEstateContact" property="description"/>
	<f:field bean="realEstateContact" property="realEstateContactType" input-propId="${realEstateContact?.realEstateContactType?.id}"/>
	<f:field bean="realEstateContact" property="partner" input-propId="${realEstateContact?.partner?.id}"/>
	<f:field bean="realEstateContact" property="client" input-propId="${realEstateContact?.client?.id}"/>
	<g:hiddenField name="concession" value="${realEstateContact?.concession?.id}"/>
</fieldset>