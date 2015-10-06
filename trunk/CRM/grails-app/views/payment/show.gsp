<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'payment.label', default: 'Payment')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-payment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-payment" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="payment.date.label" default="Date"/></span>
		        <f:display bean="payment" property="date"/>
		        <span id="name-label" class="property-label"><g:message code="payment.amount.label" default="Amount"/></span>
		        <f:display bean="payment" property="amount"/>
		        <span id="name-label" class="property-label"><g:message code="payment.currency.label" default="Currency"/></span>
		        <f:display bean="payment" property="currency"/>
		        <span id="name-label" class="property-label"><g:message code="payment.paymentMethod.label" default="Payment Method"/></span>
		        <f:display bean="payment" property="paymentMethod"/>
		        <g:if test="${payment?.paymentMethod?.hasNumber}">
			        <span id="name-label" class="property-label"><g:message code="payment.paymentDocument.internalId.label" default="Document Number"/></span>
			        <f:display bean="payment" property="paymentDocument.internalId"/>
		        </g:if>
		        <g:if test="${payment?.paymentMethod?.hasStartDate}">
			        <span id="name-label" class="property-label"><g:message code="payment.paymentDocument.internalId.startDate.label" default="Document Start Date"/></span>
			        <f:display bean="payment" property="paymentDocument.documentStartDate"/>
		        </g:if>
			    <g:if test="${payment?.paymentMethod?.hasEndDate}">
			        <span id="name-label" class="property-label"><g:message code="payment.paymentDocument.internalId.endDate.label" default="Document End Date"/></span>
		        	<f:display bean="payment" property="paymentDocument.documentEndDate"/>
		        </g:if>
		        <span id="name-label" class="property-label"><g:message code="incomePayment.id.label" default="Income Payment ID"/></span>
		        <f:display bean="payment" property="incomePayment.id"/>
		        <span id="name-label" class="property-label"><g:message code="expensePayment.id.label" default="Expense Payment ID"/></span>
		        <f:display bean="payment" property="expensePayment.id"/>
            </fieldset>
		    
            <g:form resource="${this.payment}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.payment}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
