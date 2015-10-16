
<fieldset class="form">
	<div class="fieldcontain">
		<span id="name-label" class="property-label"><g:message code="xPayment.internalId.label" default="Income/Expense Payment Internal Id"/></span>
		<span class="property-value" aria-labelledby="payment-label">${payment.incomePayment? payment.incomePayment.internalId :(payment.expensePayment? payment.expensePayment.internalId : 'unknown payment')}</span>
	</div>
	<f:field bean="payment" property="amount" label="xPayment.amount.label"/>
	<f:field bean="payment" property="date"/>
	
	<h4 class="paymentTitle"><g:message code="payment.title.label"/></h2>
	<f:field bean="payment" property="inAmount"/>
	<f:field bean="payment" property="inCurrency" input-propId="${payment?.inCurrency?.id}"/>
	<f:field bean="payment" property="inPaymentMethod" input-propId="${payment?.inPaymentMethod?.id}"/>
	<f:field bean="payment" property="inPaymentDocument.internalId"/>
	<f:field bean="payment" property="inPaymentDocument.startDate"/>
	<f:field bean="payment" property="inPaymentDocument.endDate"/>
	<f:field bean="payment" property="inPaymentDocument.bank" input-propId="${payment?.inPaymentDocument?.bank?.id}"/>
	
	
	<g:hiddenField name="incomePayment" value="${payment?.incomePayment?.id}"/>
	<g:hiddenField name="expensePayment" value="${payment?.expensePayment?.id}"/>
</fieldset>
<script type="text/javascript">
	$(document).ready(function() {
		$("#amount").attr("readonly", "readonly");//disable amount field
				
		//display change modal if change > 0
		var changeAmount=${payment.outAmount};
		if(changeAmount > 0){
			$('#display-change-modal-dialog')[0].click();
		}
	});	
</script>
