<fieldset class="form">
	<f:field bean="incomeType" property="name"/>
	<f:field bean="incomeType" property="taxRate" widget-propId="${incomeType?.taxRate?.id}"/>
	<f:field bean="incomeType" property="description">
		<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="4" cols="50"/>
	</f:field>
	<f:field bean="incomeType" property="billingDefaultDescription"/>
	<g:select class="" name="relatedDomain" value="${(incomeType?.relatedDomain!=null?crm.enums.income.RelatedDomain.valueOf(incomeType?.relatedDomain):null)}" from="${crm.enums.income.RelatedDomain.values()}" valueMessagePrefix="ENUM.RelatedDomain"/>
</fieldset>