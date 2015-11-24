<fieldset class="form">
	<f:field bean="incomeType" property="name"/>
	<f:field bean="incomeType" property="taxRate" input-propId="${incomeType?.taxRate?.id}"/>
	<f:field bean="incomeType" property="description">
		<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="4" cols="50"/>
	</f:field>
	<f:field bean="incomeType" property="billingDefaultDescription"/>
	<f:field bean="incomeType" property="isConcessionRelated"/>
</fieldset>