<fieldset class="form">
	<f:field bean="expenseType" property="name"/>
	<f:field bean="expenseType" property="selfInvoiceDefaultDescription"/>
	<f:field bean="expenseType" property="taxRate" widget-propId="${expenseType?.taxRate?.id}"/>
	<f:field bean="expenseType" property="description">
		<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="4" cols="50"/>
	</f:field>
	<g:hiddenField name="internalID" value="${expenseType?.internalID?expenseType.internalID:crm.Utils.getNumericID().toString()}" />
	<f:field bean="expenseType" property="isCompanyExpense"/>
</fieldset>