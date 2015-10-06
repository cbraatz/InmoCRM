<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'incomePayment.label', default: 'IncomePayment')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-incomePayment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-incomePayment" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="incomePayment.internalId.label" default="Internal Id"/></span>
		        <f:display bean="incomePayment" property="internalId"/>
		        <span id="name-label" class="property-label"><g:message code="incomePayment.dueDate.label" default="Due Date"/></span>
		        <f:display bean="incomePayment" property="dueDate"/>
		        <span id="name-label" class="property-label"><g:message code="incomePayment.amount.label" default="Amount"/></span>
		        <f:display bean="incomePayment" property="amount"/>
		        <span id="name-label" class="property-label"><g:message code="incomePayment.payedAmount.label" default="Payed Amount"/></span>
		        <span class="property-value">monto pagadooooooooooooooooooooooooooooooooo</span>
		        <span id="name-label" class="property-label"><g:message code="incomePayment.currency.label" default="Currency"/></span>
		        <f:display bean="incomePayment" property="currency"/>
		        <span id="name-label" class="property-label"><g:message code="incomePayment.income.label" default="Income ID"/></span>
		        <f:display bean="incomePayment" property="income.id"/>
		        <span id="name-label" class="property-label"><g:message code="incomePayment.isPaid.label" default="Is Paid"/></span>
		        <f:display bean="incomePayment" property="isPaid"/>
		        <span id="name-label" class="property-label"><g:message code="incomePayment.isCanceled.label" default="Is Canceled"/></span>
		        <f:display bean="incomePayment" property="isCanceled"/>
		    </fieldset>
            <g:form resource="${this.incomePayment}" method="CANCEL" method="GET">
                <fieldset class="buttons">
                    <g:link class="pay" action="create" controller="payment" params="[obj:'income', pid:incomePayment.id]"><g:message code="default.button.pay.label" default="Pay" /></g:link>
                    <g:link class="cancel" action="cancel" resource="${this.income}"><g:message code="default.button.cancel.label" default="Cancel" onclick="return confirm('${message(code: 'default.button.cancel.confirm.message', default: 'Are you sure?')}');" /></g:link>
                    
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
