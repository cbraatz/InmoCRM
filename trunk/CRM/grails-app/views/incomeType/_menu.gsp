<div class="more" id="expenseType-item">
	<label><g:message code="expenseType.label" default="Expense Type"/></label>
</div>
<ul class="nav-item-list" id="expenseType-opts">
	<li><g:link controller="expenseType" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="expenseType" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#expenseType-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#expenseType-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>