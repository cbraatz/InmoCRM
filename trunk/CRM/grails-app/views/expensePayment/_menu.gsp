<div class="more" id="expensePayment-item">
	<label><g:message code="expensePayment.label" default="Expense Payment"/></label>
</div>
<ul class="nav-item-list" id="expensePayment-opts">
	<li><g:link controller="expensePayment" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#expensePayment-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#expensePayment-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>