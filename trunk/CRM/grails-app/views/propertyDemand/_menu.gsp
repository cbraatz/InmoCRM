<div class="more" id="propertyDemand-item">
	<label><g:message code="propertyDemand.label" default="Property Demand"/></label>
</div>
<ul class="nav-item-list" id="propertyDemand-opts">
	<li><g:link controller="propertyDemand" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="propertyDemand" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#propertyDemand-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#propertyDemand-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>