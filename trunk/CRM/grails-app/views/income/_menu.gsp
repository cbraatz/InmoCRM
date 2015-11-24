<div class="more" id="income-item">
	<label><g:message code="income.label" default="Income"/></label>
</div>
<ul class="nav-item-list" id="income-opts">
	<li><g:link controller="income" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="income" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#income-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#income-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>