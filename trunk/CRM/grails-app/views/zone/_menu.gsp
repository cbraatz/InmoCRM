<div class="more" id="zone-item">
	<label><g:message code="zone.label" default="Zone"/></label>
</div>
<ul class="nav-item-list" id="zone-opts">
	<li><g:link controller="zone" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="zone" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#zone-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#zone-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>