<div class="more" id="propertyType-item">
	<label><g:message code="propertyType.label" default="Property Type"/></label>
</div>
<ul class="nav-item-list" id="propertyType-opts">
	<li><g:link controller="propertyType" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="propertyType" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#propertyType-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#propertyType-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>