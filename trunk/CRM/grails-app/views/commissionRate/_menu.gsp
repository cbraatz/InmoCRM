<div class="more" id="commissionRate-item">
	<label><g:message code="commissionRate.label" default="Commission Rate"/></label>
</div>
<ul class="nav-item-list" id="commissionRate-opts">
	<li><g:link controller="commissionRate" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="commissionRate" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#commissionRate-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#commissionRate-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>