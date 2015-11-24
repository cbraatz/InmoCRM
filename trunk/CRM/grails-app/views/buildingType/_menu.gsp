<div class="more" id="buildingType-item">
	<label><g:message code="buildingType.label" default="Building Type"/></label>
</div>
<ul class="nav-item-list" id="buildingType-opts">
	<li><g:link controller="buildingType" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="buildingType" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#buildingType-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#buildingType-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>