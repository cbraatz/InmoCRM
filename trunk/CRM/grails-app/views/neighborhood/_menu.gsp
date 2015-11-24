<div class="more" id="neighborhood-item">
	<label><g:message code="neighborhood.label" default="Neighborhood"/></label>
</div>
<ul class="nav-item-list" id="neighborhood-opts">
	<li><g:link controller="neighborhood" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="neighborhood" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#neighborhood-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#neighborhood-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>