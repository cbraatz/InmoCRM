<fieldset class="form">
	<f:field bean="issuedInvoice" property="date"/>
	<f:field bean="issuedInvoice" property="currency" input-propId="${issuedInvoice?.currency?.id}"/>
	<f:field bean="issuedInvoice" property="amountInIncomeCurrency" label="${message(code: 'issuedInvoice.amount.in.x.currency.label', default: 'Amount in Income Currency', args:[issuedInvoice?.incomePayment?.income?.currency?.plural])}"/>
	
	<span class="buttons">
		<g:actionSubmit action="refresh" class="save" value="${message(code: 'default.button.refresh.label', default: 'Refresh')}" />
	</span>

	<f:field bean="issuedInvoice" property="number"/>
	<f:field bean="issuedInvoice" property="amount"/>
	<f:field bean="issuedInvoice" property="amountInDefaultCurrency"/>
	<f:field bean="issuedInvoice" property="deductibleAmount"/>
	<f:field bean="issuedInvoice" property="totalTax"/>
	<f:field bean="issuedInvoice" property="client" input-propId="${issuedInvoice?.client?.id}"/>
	<!--<f:field bean="issuedInvoice" property="isAccounting"/>-->
	<!--<f:field bean="issuedInvoice" property="isCanceled"/>-->
	<g:hiddenField name="incomePayment" value="${issuedInvoice?.incomePayment?.id}"/>
	<g:hiddenField name="isAccounting" value="${issuedInvoice?.isAccounting}"/>
	<g:hiddenField name="isCanceled" value="${issuedInvoice?.isCanceled}"/>
	<span class="buttons">
		<g:actionSubmit action="save" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
	</span>
</fieldset>