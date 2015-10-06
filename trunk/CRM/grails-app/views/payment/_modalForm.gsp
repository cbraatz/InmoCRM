<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<asset:stylesheet href="modal.css"/>		 
<!-- Bootstrap core CSS -->
<!--<link href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.1.1/css/bootstrap.css" rel="stylesheet" media="screen"/> original remplazado por el de arreiba "map.css"-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7/html5shiv.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.3.0/respond.js"></script>
<![endif]-->


	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="modalFrm">
		    <div class="modal-dialog modal-lg">
				<div class="modal-content">
					<g:form action="confirmPayment">
			        	
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title"><g:message code="payment.change.label" default="Select location"/></h4>
							</div>
							<div class="modal-body">
								<fieldset class="fieldcontain">
									<span id="name-label" class="property-label"><g:message code="change.amount.label" default="Change Amount"/></span>
									<f:display bean="payment" property="outAmount"/>
									<span id="name-label" class="property-label"><g:message code="change.currency.label" default="Change Currency"/></span>
									<f:display bean="payment" property="outCurrency"/>
									<span id="name-label" class="property-label"><g:message code="change.paymentMethod.label" default="Change Payment Method"/></span>
									<f:display bean="payment" property="outPaymentMethod"/>
									
									<g:hiddenField name="payment.amount" value="${payment.amount}"></g:hiddenField>
									<g:hiddenField name="payment.inAmount" value="${payment.inAmount}"></g:hiddenField>
									<g:hiddenField name="payment.incomePayment" value="${payment.incomePayment}"></g:hiddenField>
									<g:hiddenField name="payment.expensePayment" value="${payment.expensePayment}"></g:hiddenField>
									<g:hiddenField name="payment.inCurrency" value="${payment.inCurrency}"></g:hiddenField>
									<g:hiddenField name="payment.inPaymentMethod" value="${payment.inPaymentMethod}"></g:hiddenField>
									<g:hiddenField name="payment.inPaymentDocument.internalId" value="${payment.inPaymentDocument.internalId}"></g:hiddenField>
									<g:hiddenField name="payment.inPaymentDocument.startDate" value="${payment.inPaymentDocument.startDate}"></g:hiddenField>
									<g:hiddenField name="payment.inPaymentDocument.endDate" value="${payment.inPaymentDocument.endDate}"></g:hiddenField>
									<g:hiddenField name="payment.inPaymentDocument.bank" value="${payment.inPaymentDocument.bank}"></g:hiddenField>
								</fieldset>
							</div>
							<div class="modal-footer">
								<fieldset class="buttons">
				                	<button type="button" class="close" data-dismiss="modal"><g:message code="payment.close.label" default="Close"/></button>
				                    <g:submitButton name="pay" class="save" value="${message(code: 'default.button.confirm.payment.label', default: 'Create')}" />
			                    </fieldset>
							</div>
						
			        </g:form>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</div><!-- /container -->
		 
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<asset:javascript src="jquery-2.1.3.js"/>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<asset:javascript src="bootstrap_min.js"/>