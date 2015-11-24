<div class="more" id="partner-item">
	<label><g:message code="partner.label" default="Partner"/></label>
</div>
<ul class="nav-item-list" id="partner-opts">
	<li><g:link controller="partner" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="partner" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#partner-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#partner-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>