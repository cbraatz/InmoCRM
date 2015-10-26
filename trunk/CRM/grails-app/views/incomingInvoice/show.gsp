<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'incomingInvoice.label', default: 'IncomingInvoice')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-incomingInvoice" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-incomingInvoice" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
			<fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="incomingInvoice.number.label" default="Number"/></span>
		        <f:display bean="incomingInvoice" property="number"/>
		        <span id="name-label" class="property-label"><g:message code="incomingInvoice.vendor.label" default="Vendor"/></span>
		        <f:display bean="incomingInvoice" property="vendor"/>
		        <span id="name-label" class="property-label"><g:message code="incomingInvoice.date.label" default="Date"/></span>
		        <f:display bean="incomingInvoice" property="date"/>
		        <span id="name-label" class="property-label"><g:message code="incomingInvoice.amount.label" default="Amount"/></span>
		        <f:display bean="incomingInvoice" property="amount"/>
		        <span id="name-label" class="property-label"><g:message code="incomingInvoice.amountInExpenseCurrency.label" default="Amount In Expense Currency"/></span>
		        <f:display bean="incomingInvoice" property="amountInExpenseCurrency"/>
		        <span id="name-label" class="property-label"><g:message code="incomingInvoice.totalTax.label" default="Total Tax"/></span>
		        <f:display bean="incomingInvoice" property="totalTax"/>
		        <span id="name-label" class="property-label"><g:message code="incomingInvoice.isAccounting.label" default="Is Accounting"/></span>
		        <f:display bean="incomingInvoice" property="isAccounting"/>
		        <span id="name-label" class="property-label"><g:message code="incomingInvoice.isAccounted.label" default="Is Accounted"/></span>
		        <f:display bean="incomingInvoice" property="isAccounted"/>
		        <span id="name-label" class="property-label"><g:message code="incomingInvoice.isSelfInvoice.label" default="Is Self Invoice"/></span>
		        <f:display bean="incomingInvoice" property="isSelfInvoice"/>
		        <span id="name-label" class="property-label"><g:message code="incomingInvoice.currency.label" default="Currency"/></span>
		        <f:display bean="incomingInvoice" property="currency"/>
            </fieldset>


            <g:form resource="${this.incomingInvoice}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.incomingInvoice}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
