<div class="more" id="managedProperty-item">
	<label><g:message code="managedProperty.label" default="Managed Property"/></label>
</div>
<ul class="nav-item-list" id="managedProperty-opts">
	<li><g:link controller="managedProperty" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#managedProperty-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#managedProperty-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>