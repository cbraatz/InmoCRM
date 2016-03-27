<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'invoicesPrinting.label', default: 'InvoicesPrinting')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-invoicesPrinting" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-invoicesPrinting" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <fieldset class="fieldcontain">
            	<span id="printingNumber-label" class="property-label"><g:message code="invoicesPrinting.printingNumber.label" default="Printing Number"/></span>
		        <f:display bean="invoicesPrinting" property="printingNumber"/>
		        
		        <span id="startDate-label" class="property-label"><g:message code="invoicesPrinting.startDate.label" default="Start Date"/></span>
		        <f:display bean="invoicesPrinting" property="startDate"/>
		        
		        <span id="endDate-label" class="property-label"><g:message code="invoicesPrinting.endDate.label" default="End Date"/></span>
		        <f:display bean="invoicesPrinting" property="endDate"/>
		        
		        <span id="firstNumber-label" class="property-label"><g:message code="invoicesPrinting.firstNumber.label" default="First Number"/></span>
		        <f:display bean="invoicesPrinting" property="firstNumber"/>
		        
		        <span id="secondNumber-label" class="property-label"><g:message code="invoicesPrinting.secondNumber.label" default="Second Number"/></span>
		        <f:display bean="invoicesPrinting" property="secondNumber"/>
		        		        
		        <span id="thirdStartNumber-label" class="property-label"><g:message code="invoicesPrinting.thirdStartNumber.label" default="Third Start Number"/></span>
		        <f:display bean="invoicesPrinting" property="thirdStartNumber"/>
		        
		        <span id="quantity-label" class="property-label"><g:message code="invoicesPrinting.quantity.label" default="Quantity"/></span>
		        <f:display bean="invoicesPrinting" property="quantity"/>
            </fieldset>

            <g:form resource="${this.invoicesPrinting}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.invoicesPrinting}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
