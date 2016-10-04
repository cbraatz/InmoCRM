<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'invoicePrinting.label', default: 'InvoicePrinting')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-invoicePrinting" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-invoicePrinting" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <fieldset class="fieldcontain">
            	<span id="printingNumber-label" class="property-label"><g:message code="invoicePrinting.printingNumber.label" default="Printing Number"/></span>
		        <f:display bean="invoicePrinting" property="printingNumber"/>
		        
		        <span id="startDate-label" class="property-label"><g:message code="invoicePrinting.startDate.label" default="Start Date"/></span>
		        <f:display bean="invoicePrinting" property="startDate"/>
		        
		        <span id="endDate-label" class="property-label"><g:message code="invoicePrinting.endDate.label" default="End Date"/></span>
		        <f:display bean="invoicePrinting" property="endDate"/>
		        
		        <span id="firstNumber-label" class="property-label"><g:message code="invoicePrinting.firstNumber.label" default="First Number"/></span>
		        <f:display bean="invoicePrinting" property="firstNumber"/>
		        
		        <span id="secondNumber-label" class="property-label"><g:message code="invoicePrinting.secondNumber.label" default="Second Number"/></span>
		        <f:display bean="invoicePrinting" property="secondNumber"/>
		        		        
		        <span id="thirdStartNumber-label" class="property-label"><g:message code="invoicePrinting.thirdStartNumber.label" default="Third Start Number"/></span>
		        <f:display bean="invoicePrinting" property="thirdStartNumber"/>
		        
		        <span id="quantity-label" class="property-label"><g:message code="invoicePrinting.quantity.label" default="Quantity"/></span>
		        <f:display bean="invoicePrinting" property="quantity"/>
            </fieldset>

            <g:form resource="${this.invoicePrinting}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.invoicePrinting}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
