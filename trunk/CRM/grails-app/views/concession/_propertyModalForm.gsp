		        	
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title"><g:message code="payment.change.label" default="Select location"/></h4>
</div>
<div class="modal-body">
	<fieldset class="fieldcontain">
		<f:field bean="managedProperty" property="addedDate"/>
	</fieldset>
</div>
<div class="modal-footer">
	<fieldset class="buttons">
		<button type="button" class="close" data-dismiss="modal"><g:message code="payment.close.label" default="Close"/></button>
		<g:actionSubmit action="confirmPayment" class="save" value="${message(code: 'default.button.confirm.payment.label', default: 'Create')}" />
	</fieldset>
</div>
				