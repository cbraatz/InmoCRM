<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'payment.label', default: 'Payment')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script type="text/javascript">
			$(document).ready(function() {
				$("#amount").attr("readonly", "readonly");//disable amount field
				
				//display change modal if change > 0
				var changeAmount=${payment.outAmount};
				if(changeAmount > 0){
					$('#display-change-modal-dialog')[0].click();
				}
			});
			
		</script>
    </head>
    <body>
        <a href="#create-payment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-payment" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            	<div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.payment}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.payment}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
            
				<g:render template="paymentForm"/>
				<a href="#modalFrm" data-toggle="modal" role="button" id="display-change-modal-dialog">mostrar modal</a>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.pay.label', default: 'Pay')}" />
                </fieldset>
            </g:form>
            <g:render template="modalForm"/>
        </div>
    </body>
</html>

