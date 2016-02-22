<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'propertyType.label', default: 'PropertyType')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-propertyType" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-propertyType" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
			<fieldset class="fieldcontain">
            	<span id="name-label" class="property-label"><g:message code="propertyType.name.label" default="Name"/></span>
		        <f:display bean="propertyType" property="name"/>
		        <span id="name-label" class="property-label"><g:message code="propertyType.dimensionMeasuringUnit.label" default="Dimension Measuring Unit"/></span>
		        <f:display bean="propertyType" property="dimensionMeasuringUnit"/>
		        <span id="name-label" class="property-label"><g:message code="propertyType.description.label" default="Description"/></span>
		        <f:display bean="propertyType" property="description"/>
		        <span id="name-label" class="property-label"><g:message code="propertyType.keywords.label" default="Keywords"/></span>
		        <f:display bean="propertyType" property="keywords"/>
            </fieldset>
            
            <g:form resource="${this.propertyType}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.propertyType}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
