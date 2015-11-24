<div class="more" id="client-item">
	<label><g:message code="client.label" default="Client"/></label>
</div>
<ul class="nav-item-list" id="client-opts">
	<li><g:link controller="client" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="client" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#client-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#client-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>