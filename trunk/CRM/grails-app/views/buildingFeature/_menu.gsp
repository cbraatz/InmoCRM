<div class="more" id="buildingFeature-item">
	<label><g:message code="buildingFeature.label" default="Building Feature"/></label>
</div>
<ul class="nav-item-list" id="buildingFeature-opts">
	<li><g:link controller="buildingFeature" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="buildingFeature" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#buildingFeature-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#buildingFeature-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>