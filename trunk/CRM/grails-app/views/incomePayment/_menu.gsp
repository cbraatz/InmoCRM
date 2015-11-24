<div class="more" id="incomePayment-item">
	<label><g:message code="incomePayment.label" default="Income Payment"/></label>
</div>
<ul class="nav-item-list" id="incomePayment-opts">
	<li><g:link controller="incomePayment" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#incomePayment-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#incomePayment-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>