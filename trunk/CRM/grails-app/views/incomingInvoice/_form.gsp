<g:set var="ex_curr" value="${incomingInvoice?.expensePayment?.expense?.currency?.plural}"/>
<fieldset class="form">
	<h1 class="paymentTitle"><g:message code="incomingInvoice.title.label"/></h1>
	<f:field bean="incomingInvoice" property="date"/>
	<f:field bean="incomingInvoice" property="currency" input-propId="${incomingInvoice?.currency?.id}"/>
	<f:field bean="incomingInvoice" property="amountInExpenseCurrency" label="${message(code: 'incomingInvoice.amount.in.x.currency.label', default: 'Amount in Expense currency', args:[ex_curr])}"/>
	<f:field bean="incomingInvoice" property="isSelfInvoice"/>
	<span class="buttons right refreshBtn">
		<g:actionSubmit action="refresh" class="refresh" value="${message(code: 'default.button.refresh.label', default: 'Refresh')}" />
	</span>
	
	<h1 class="paymentTitle"><g:message code="incomingInvoice.details.title.label"/></h1>
	<f:field bean="incomingInvoice" property="number"/>
	<f:field bean="incomingInvoice" property="vendor" input-propId="${incomingInvoice?.vendor?.id}"/>
	<f:field bean="incomingInvoice" property="deductibleAmount"/>
	<f:field bean="incomingInvoice" property="amount"/>
	<f:field bean="incomingInvoice" property="totalTax"/>
	<g:hiddenField name="expensePayment" value="${incomingInvoice?.expensePayment?.id}"/>
	<g:hiddenField name="isAccounting" value="${incomingInvoice?.isAccounting}"/>
	<g:hiddenField name="isAccounted" value="${incomingInvoice?.isAccounted}"/>
	<g:hiddenField name="isSelfInvoice" value="${incomingInvoice?.isSelfInvoice}"/>
</fieldset>
<script type="text/javascript">
	$(document).ready(function() {
		
	});	

	function displayOrHideRefreshButton(){
		if($("#isSelfInvoice").is(":checked")){
			$(".refreshBtn").show();
			$("#amount").attr("readonly", "readonly");//disable amount field
			$("#totalTax").attr("readonly", "readonly");//disable totalTax field
		}else{
			$(".refreshBtn").hide();
			$("#amount").removeAttr("readonly");//enable amount field
			$("#totalTax").removeAttr("readonly");//enable totalTax field
		}
	}
	
	$("#isSelfInvoice").change(function() {
		displayOrHideRefreshButton();
	});
	
	displayOrHideRefreshButton();
</script>