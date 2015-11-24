<div class="more" id="vendor-item">
	<label><g:message code="vendor.label" default="Vendor"/></label>
</div>
<ul class="nav-item-list" id="vendor-opts">
	<li><g:link controller="vendor" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="vendor" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#vendor-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#vendor-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>