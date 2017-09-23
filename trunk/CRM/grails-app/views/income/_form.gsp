
<fieldset class="form">
	<f:field bean="income" property="date"/>
	<f:field bean="income" property="description">
		<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="4" cols="50"/>
	</f:field>
	<f:field bean="income" property="amount"/>
	<f:field bean="income" property="currency" widget-propId="${income?.currency?.id}"/>
	<f:field bean="income" property="client" widget-propId="${income?.client?.id}"/>
	<f:field bean="income" property="incomeType" widget-propId="${income?.incomeType?.id}"/>
	<f:field bean="income" property="relatedToId" required="false"/>
	<!--<f:field bean="income" property="isPaid"/>  -->
	<f:field bean="income" property="isCredit"/>
	<div id="paymentPlan-id">
		<f:field bean="income" property="paymentPlan" widget-propId="${income?.paymentPlan?.id}"/>
	</div>
</fieldset>
<script>
	function displayOrHidePaymentPlan(){
		if($("#isCredit").is(":checked")){
			$("#paymentPlan-id").show();
		}else{
			$("#paymentPlan-id").hide();
		}
	}
	
	$("#isCredit").change(function() {
		displayOrHidePaymentPlan();
	});
	
	displayOrHidePaymentPlan();
</script>