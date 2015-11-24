<div class="more" id="city-item">
	<label><g:message code="city.label" default="City"/></label>
</div>
<ul class="nav-item-list" id="city-opts">
	<li><g:link controller="city" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="city" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#city-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#city-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>