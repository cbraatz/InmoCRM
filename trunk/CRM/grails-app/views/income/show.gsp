<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'income.label', default: 'Income')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-income" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-income" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="income.date.label" default="Date"/></span>
		        <f:display bean="income" property="date"/>
		        <span id="name-label" class="property-label"><g:message code="income.description.label" default="Description"/></span>
		        <f:display bean="income" property="description"/>
		        <span id="name-label" class="property-label"><g:message code="income.amount.label" default="Amount"/></span>
		        <f:display bean="income" property="amount"/>
		        <span id="name-label" class="property-label"><g:message code="income.currency.label" default="Currency"/></span>
		        <f:display bean="income" property="currency"/>
		        <span id="name-label" class="property-label"><g:message code="income.client.label" default="Client"/></span>
		        <f:display bean="income" property="client"/>
		        <span id="name-label" class="property-label"><g:message code="income.concession.label" default="Concession"/></span>
		        <f:display bean="income" property="concession.id"/>
		        <span id="name-label" class="property-label"><g:message code="income.incomeType.label" default="Income type"/></span>
		        <f:display bean="income" property="incomeType"/>
		        <span id="name-label" class="property-label"><g:message code="income.isPaid.label" default="Is paid"/></span>
		        <f:display bean="income" property="isPaid"/>
		        <span id="name-label" class="property-label"><g:message code="income.isCredit.label" default="Is credit"/></span>
		        <f:display bean="income" property="isCredit"/>
		        <g:if test="${income.isCredit}">
			        <span id="name-label" class="property-label"><g:message code="income.paymentPlan.label" default="Payment plan"/></span>
			        <f:display bean="income" property="paymentPlan"/>
		        </g:if>
            </fieldset>
            <h1><g:message code="income.incomePayments.label"/></h1>
            <f:table collection="${income.incomePayments}" properties="['internalID','amount', 'currency', 'dueDate', 'isPaid', 'isCanceled']" />
            
            <g:form resource="${this.income}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.income}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
