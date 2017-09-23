<g:set var="inc_curr" value="${issuedInvoice?.incomePayment?.income?.currency?.plural}"/>
<fieldset class="form">
	<h1 class="paymentTitle"><g:message code="issuedInvoice.title.label"/></h1>
	<f:field bean="issuedInvoice" property="date"/>
	<f:field bean="issuedInvoice" property="currency" widget-propId="${issuedInvoice?.currency?.id}"/>
	<f:field bean="issuedInvoice" property="amountInIncomeCurrency" label="${message(code: 'issuedInvoice.amount.in.x.currency.label', default: 'Amount in Income currency', args:[inc_curr])}"/>
	<span class="buttons right">
		<g:actionSubmit action="refresh" class="refresh" value="${message(code: 'default.button.refresh.label', default: 'Refresh')}" />
	</span>
	
	<h1 class="paymentTitle"><g:message code="issuedInvoice.details.title.label"/></h1>
	<f:field bean="issuedInvoice" property="number"/>
	<f:field bean="issuedInvoice" property="client" widget-propId="${issuedInvoice?.client?.id}"/>
	<f:field bean="issuedInvoice" property="amount"/>
	<f:field bean="issuedInvoice" property="totalTax"/>
	<g:hiddenField name="incomePayment" value="${issuedInvoice?.incomePayment?.id}"/>
	<g:hiddenField name="isAccounting" value="${issuedInvoice?.isAccounting}"/>
	<g:hiddenField name="isAccounted" value="${issuedInvoice?.isAccounted}"/>
	<g:hiddenField name="isCanceled" value="${issuedInvoice?.isCanceled}"/>
</fieldset>
<script type="text/javascript">
	$(document).ready(function() {
		$("#amount").attr("readonly", "readonly");//disable amount field
		$("#totalTax").attr("readonly", "readonly");//disable totalTax field
	});	
</script>