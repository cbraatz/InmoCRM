<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'expense.label', default: 'Expense')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-expense" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-expense" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="expense.date.label" default="Date"/></span>
		        <f:display bean="expense" property="date"/>
		        <span id="name-label" class="property-label"><g:message code="expense.description.label" default="Description"/></span>
		        <f:display bean="expense" property="description"/>
		        <span id="name-label" class="property-label"><g:message code="expense.amount.label" default="Amount"/></span>
		        <f:display bean="expense" property="amount"/>
		        <span id="name-label" class="property-label"><g:message code="expense.currency.label" default="Currency"/></span>
		        <f:display bean="expense" property="currency"/>
		        <span id="name-label" class="property-label"><g:message code="expense.vendor.label" default="Vendor"/></span>
		        <f:display bean="expense" property="vendor"/>
		        <span id="name-label" class="property-label"><g:message code="expense.expenseType.label" default="Expense type"/></span>
		        <f:display bean="expense" property="expenseType"/>
		        <span id="name-label" class="property-label"><g:message code="expense.isPaid.label" default="Is paid"/></span>
		        <f:display bean="expense" property="isPaid"/>
		        <span id="name-label" class="property-label"><g:message code="expense.isCredit.label" default="Is credit"/></span>
		        <f:display bean="expense" property="isCredit"/>
		        <g:if test="${expense.isCredit}">
			        <span id="name-label" class="property-label"><g:message code="expense.paymentPlan.label" default="Payment plan"/></span>
			        <f:display bean="expense" property="paymentPlan"/>
		        </g:if>
            </fieldset>
            <h1><g:message code="expense.expensePayments.label"/></h1>
            <f:table collection="${expense.expensePayments}" properties="['internalID','amount', 'currency', 'dueDate', 'isPaid', 'isCanceled']" />
            
            <g:form resource="${this.expense}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.expense}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
