<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'expensePayment.label', default: 'ExpensePayment')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-expensePayment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-expensePayment" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="expensePayment.internalID.label" default="Internal Id"/></span>
		        <f:display bean="expensePayment" property="internalID"/>
		        <span id="name-label" class="property-label"><g:message code="expensePayment.dueDate.label" default="Due Date"/></span>
		        <f:display bean="expensePayment" property="dueDate"/>
		        <span id="name-label" class="property-label"><g:message code="expensePayment.amount.label" default="Amount"/></span>
		        <f:display bean="expensePayment" property="amount"/>
		        <span id="name-label" class="property-label"><g:message code="expensePayment.payedAmount.label" default="Payed Amount"/></span>
		        <span class="property-value">${crm.Utils.formatDecimals(expensePayment.getPayedTotalAmount())}</span>
		        <span id="name-label" class="property-label"><g:message code="expensePayment.currency.label" default="Currency"/></span>
		        <f:display bean="expensePayment" property="currency"/>
		        <span id="name-label" class="property-label"><g:message code="expensePayment.expense.label" default="Expense ID"/></span>
		        <f:display bean="expensePayment" property="expense.id"/>
		        <span id="name-label" class="property-label"><g:message code="expensePayment.isPaid.label" default="Is Paid"/></span>
		        <f:display bean="expensePayment" property="isPaid"/>
		        <span id="name-label" class="property-label"><g:message code="expensePayment.isCanceled.label" default="Is Canceled"/></span>
		        <f:display bean="expensePayment" property="isCanceled"/>
		    </fieldset>
            <g:form resource="${this.expensePayment}" method="CANCEL" method="GET">
                <fieldset class="buttons">
                	<g:if test="${expensePayment.isPaid}">
                		<g:link class="invoice" action="create" controller="incomingInvoice" params="[pid:expensePayment.id]"><g:message code="default.button.invoice.label" default="Invoice" /></g:link>
                    </g:if>
					<g:else>
					      <g:link class="pay" action="create" controller="payment" params="[obj:'expense', pid:expensePayment.id]"><g:message code="default.button.pay.label" default="Pay" /></g:link>
                    <!--<g:link class="cancel" action="cancel" resource="${this.expensePayment}"><g:message code="default.button.cancel.label" default="Cancel" onclick="return confirm('${message(code: 'default.button.cancel.confirm.message', default: 'Are you sure?')}');" /></g:link>  -->
					</g:else>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
