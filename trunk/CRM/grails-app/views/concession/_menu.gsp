<div class="more" id="concession-item">
	<label><g:message code="concession.label" default="Concession"/></label>
</div>
<ul class="nav-item-list" id="concession-opts">
	<li><g:link controller="concession" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="concession" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#concession-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#concession-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>