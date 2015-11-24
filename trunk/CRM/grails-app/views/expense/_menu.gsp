<div class="more" id="expense-item">
	<label><g:message code="expense.label" default="Expense"/></label>
</div>
<ul class="nav-item-list" id="expense-opts">
	<li><g:link controller="expense" action="create"><g:message code="default.new.label" default="New"/></g:link></li>
	<li><g:link controller="expense" action="index"><g:message code="default.list.label" default="List"/></g:link></li>
</ul>
<script type="text/javascript">
	$(document).ready(function() {
		$("#expense-item").click(function() {
			displayOrHideOptions();
		});
	});
	
	function displayOrHideOptions(){
		var opts=$("#expense-opts");
		if(opts.is(':visible')){
			opts.hide();
		}else{
			opts.show();
		}
	}
</script>