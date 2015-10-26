<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'issuedInvoice.label', default: 'IssuedInvoice')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-issuedInvoice" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-issuedInvoice" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
			<fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="issuedInvoice.number.label" default="Number"/></span>
		        <f:display bean="issuedInvoice" property="number"/>
		        <span id="name-label" class="property-label"><g:message code="issuedInvoice.client.label" default="Client"/></span>
		        <f:display bean="issuedInvoice" property="client"/>
		        <span id="name-label" class="property-label"><g:message code="issuedInvoice.date.label" default="Date"/></span>
		        <f:display bean="issuedInvoice" property="date"/>
		        <span id="name-label" class="property-label"><g:message code="issuedInvoice.amount.label" default="Amount"/></span>
		        <f:display bean="issuedInvoice" property="amount"/>
		        <span id="name-label" class="property-label"><g:message code="issuedInvoice.amountInIncomeCurrency.label" default="Amount In Income Currency"/></span>
		        <f:display bean="issuedInvoice" property="amountInIncomeCurrency"/>
		        <span id="name-label" class="property-label"><g:message code="issuedInvoice.totalTax.label" default="Total Tax"/></span>
		        <f:display bean="issuedInvoice" property="totalTax"/>
		        <span id="name-label" class="property-label"><g:message code="issuedInvoice.isAccounting.label" default="Is Accounting"/></span>
		        <f:display bean="issuedInvoice" property="isAccounting"/>
		        <span id="name-label" class="property-label"><g:message code="issuedInvoice.isAccounted.label" default="Is Accounted"/></span>
		        <f:display bean="issuedInvoice" property="isAccounted"/>
		        <span id="name-label" class="property-label"><g:message code="issuedInvoice.currency.label" default="Currency"/></span>
		        <f:display bean="issuedInvoice" property="currency"/>
            </fieldset>


            <g:form resource="${this.issuedInvoice}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.issuedInvoice}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
