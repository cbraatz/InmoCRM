<%@ page import="crm.BuildingFeatureByLanguage" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
         <g:set var="entityName" value="${message(code: 'buildingFeature.label', default: 'Property Feature')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-buildingFeature" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-buildingFeature" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
			<fieldset class="fieldcontain">
				<span id="name-label" class="property-label"><g:message code="buildingFeature.name.label" default="Name"/></span>
			    <f:display bean="buildingFeature" property="name"/>
			    <span id="plural-label" class="property-label"><g:message code="buildingFeature.plural.label" default="Name in Plural"/></span>
			    <f:display bean="buildingFeature" property="plural"/>
			    <span id="description-label" class="property-label"><g:message code="buildingFeature.description.label" default="Description"/></span>
			    <f:display bean="buildingFeature" property="description"/>
           		<span id="defaultWebIcon-label" class="property-label"><g:message code="buildingFeature.defaultWebIcon.label" default="Default Web Icon"/></span>
		        <f:display bean="buildingFeature" property="defaultWebIcon"/>
		        
		        <g:set var="featureList" value="${BuildingFeatureByLanguage.findAllByBuildingFeature(buildingFeature)}" />
		        <g:if test="${featureList.size > 0}">
			        <h1><g:message code="buildingFeature.other.languages.title"/></h1>
			        <f:table collection="${buildingFeature.buildingFeaturesByLanguage}" properties="['name', 'plural', 'language']"/>
				</g:if>
            </fieldset>

            <g:form resource="${this.buildingFeature}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.buildingFeature}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    <g:link class="feature" action="translate" params="[fid:buildingFeature.id]"><g:message code="buildingFeature.add.translations.button.label" default="Add Translations" /></g:link>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>