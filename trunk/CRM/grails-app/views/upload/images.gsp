<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'managedProperty.label', default: 'ManagedProperty')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-managedProperty" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <g:if test="${managedProperty}">
                	<li><g:link class="return" action="show" controller="managedProperty" id="${managedProperty?.id}"><g:message code="managedProperty.label" default="Managed Property" /></g:link></li>
                </g:if>
            </ul>
        </div>
        <div id="images-upload" class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
			<h1><g:message code="upload.image.title"/></h1><br>
			<g:form method="post"  enctype="multipart/form-data">
			 	<fieldset class="fieldcontain">
			 		<span id="fileupload-label" class="property-label"><g:message code="upload.image.selector.label" default="Upload"/></span>
			 		<input type="file" id="fileUpload" name="fileUpload" class="property-value"/>
			 		<span id="fileDescription-label" class="property-label"><g:message code="upload.image.name.label" default="File Name"/></span>
			 		<g:textField id="description" name="description" class="property-value"/>
			 	</fieldset>
			 	<fieldset class="fieldcontain">
			 		<span id="fileMain-label" class="property-label"><g:message code="upload.image.main.label" default="Main Image"/></span>
			 		<g:checkBox id="mainImage" name="mainImage" class="property-value"/>
			 	</fieldset>
			 	<fieldset class="fieldcontain">
			 		<span id="fileWeb-label" class="property-label"><g:message code="upload.image.web.label" default="Web Image"/></span>
			 		<g:checkBox id="addToWebPage" name="addToWebPage" class="property-value"/>
	            </fieldset>
	            <div class="buttons">
	                <span class="button"><g:actionSubmit class="upload" value="${message(code: 'default.button.upload.label', default: 'Upload')}" action="uploadImage" /></span>
	            </div>
	            
	        </g:form>
			<g:if test="${fileResourceInstanceList.size > 0}">
	            <h1><g:message code="upload.image.list.title"/></h1>
				<div id="success"></div>
	            <div class="list">
	                <table>
	                    <thead>
	                        <tr>
	                            <g:sortableColumn property="files" title="${message(code: 'upload.list.image.name.label', default: 'Name')}"/>
	                            <g:sortableColumn property="main" title="${message(code: 'upload.list.image.main.label', default: 'Is Main Image')}"/>
	                            <g:sortableColumn property="web" title="${message(code: 'upload.list.image.web.label', default: 'Add to web')}"/>
	                            <g:sortableColumn property="description" title="${message(code: 'upload.list.image.description.label', default: 'Description')}" colspan="3"/>
	                       </tr>
	                    </thead>
	                    <tbody>
	                    <g:each in="${fileResourceInstanceList}" status="i" var="fileResourceInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                            <td>${fileResourceInstance.fileName}</td>
	                            <td>${fileResourceInstance.isMainImage}</td>
	                            <td>${fileResourceInstance.addToWeb}</td>
	                            <td>${fileResourceInstance.description}</td>
	                            <td><g:link action="deleteImage" id="${fileResourceInstance.id}" onclick="return confirm('Are you sure?');"><g:message code="upload.delete.image.label"/></g:link></td>
	                        </tr>
	                    </g:each>
	                    </tbody>
	                </table>
	            </div>
            </g:if>
        </div>
    </body>
</html>
