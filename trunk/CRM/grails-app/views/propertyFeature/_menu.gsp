<div class="more" id="propertyFeature-item">
	<label><g:message code="propertyFeature.label" default="Property Feature"/></label>
</div>
<ul class="nav-item-list" id="propertyFeature-opts">
	<li><g:link controller="propertyFeature" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="propertyFeature" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#propertyFeature-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#propertyFeature-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>