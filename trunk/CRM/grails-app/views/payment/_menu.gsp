<div class="more" id="payment-item">
	<label><g:message code="payment.label" default="Payment"/></label>
</div>
<ul class="nav-item-list" id="payment-opts">
	<li><g:link controller="payment" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#payment-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#payment-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>