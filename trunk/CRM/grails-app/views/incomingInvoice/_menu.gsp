<div class="more" id="incomingInvoice-item">
	<label><g:message code="incomingInvoice.label" default="Incoming Invoice"/></label>
</div>
<ul class="nav-item-list" id="incomingInvoice-opts">
	<li><g:link controller="incomingInvoice" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#incomingInvoice-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#incomingInvoice-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>