<div class="more" id="building-item">
	<label><g:message code="building.label" default="Building"/></label>
</div>
<ul class="nav-item-list" id="building-opts">
	<li><g:link controller="building" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="building" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#building-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#building-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>