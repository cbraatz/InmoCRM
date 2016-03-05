<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'webPage.label', default: 'WebPage')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-webPage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-webPage" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
			
			<fieldset class="fieldcontain">
            	<span id="title-label" class="property-label"><g:message code="webPage.title.label" default="Title"/></span>
		        <f:display bean="webPage" property="title"/>
		        <span id="summary-label" class="property-label"><g:message code="webPage.summary.label" default="Summary"/></span>
		        <f:display bean="webPage" property="summary"/>
		        <span id="firstDescription-label" class="property-label"><g:message code="webPage.firstDescription.label" default="First Description"/></span>
		        <f:display bean="webPage" property="firstDescription"/>
		        <span id="secondDescription-label" class="property-label"><g:message code="webPage.secondDescription.label" default="Second Description"/></span>
		        <f:display bean="webPage" property="secondDescription"/>
		        <span id="thirdDescription-label" class="property-label"><g:message code="webPage.thirdDescription.label" default="Third Description"/></span>
		        <f:display bean="webPage" property="thirdDescription"/>
		        <span id="callToAction-label" class="property-label"><g:message code="webPage.callToAction.label" default="Call To Action"/></span>
		        <f:display bean="webPage" property="callToAction"/>
		        <span id="price-label" class="property-label"><g:message code="webPage.price.label" default="Price"/></span>
		        <f:display bean="webPage" property="price"/>
		        <span id="keyWords-label" class="property-label"><g:message code="webPage.keyWords.label" default="Key Words"/></span>
		        <f:display bean="webPage" property="keyWords"/>
		        <span id="agentComment-label" class="property-label"><g:message code="webPage.agentComment.label" default="Agent Comment"/></span>
		        <f:display bean="webPage" property="agentComment"/>
		        <span id="operationType-label" class="property-label"><g:message code="webPage.operationType.label" default="Operation Type"/></span>
		        <f:display bean="webPage" property="operationType"/>
		        <span id="managedProperty-label" class="property-label"><g:message code="webPage.managedProperty.label" default="Managed Property"/></span>
		        <f:display bean="webPage" property="managedProperty.id"/>
		        <span id="domain-label" class="property-label"><g:message code="webPage.domain.label" default="Domain"/></span>
		        <f:display bean="webPage" property="domain"/>
		        <span id="inWeb-label" class="property-label"><g:message code="webPage.inWeb.label" default="inWeb"/></span>
				<f:display bean="webPage" property="inWeb"/>
				<g:if test="${webPage?.inWeb}">
					<span id="pageUrl-label" class="property-label"><g:message code="webPage.link.label" default="Web Page Link"/></span>
					<a href="${webPage.pageUrl}" target="_blank">${webPage.pageUrl}</a>
				</g:if>
            </fieldset>
            
            <g:form resource="${this.webPage}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.webPage}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
