<div class="more" id="issuedInvoice-item">
	<label><g:message code="issuedInvoice.label" default="Issued Invoice"/></label>
</div>
<ul class="nav-item-list" id="issuedInvoice-opts">
	<li><g:link controller="issuedInvoice" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#issuedInvoice-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#issuedInvoice-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>