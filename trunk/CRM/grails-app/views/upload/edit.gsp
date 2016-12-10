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
        <div id="images-upload" class="content scaffold-create" role="main">
			<g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
			<h1><g:message code="upload.image.title"/></h1><br>
			<g:form method="post"  enctype="multipart/form-data">
				<fieldset class="form">
				 	<div class="fieldcontain required">
				 		<label for="fileUpload" id="fileupload-label" class="property-label"><g:message code="upload.image.selector.label" default="Upload"/></label>
				 		<input type="file" id="fileUpload" name="fileUpload"/>
				 	</div>
				 	<div class="fieldcontain">
				 		<label for="description" id="fileDescription-label" class="property-label"><g:message code="upload.image.name.label" default="File Name"/></label>
				 		<g:textField id="description" name="description"/>
				 	</div>
				 	<div class="fieldcontain">
				 		<label for="mainImage" id="fileMain-label" class="property-label"><g:message code="upload.image.main.label" default="Main Image"/></label>
				 		<g:checkBox id="mainImage" name="mainImage"/>
				 	</div>
				 	<div class="fieldcontain">
				 		<label for="addToWebPage" id="fileWeb-label" class="property-label"><g:message code="upload.image.web.label" default="Web Image"/></label>
				 		<g:checkBox id="addToWebPage" name="addToWebPage"/>
		            </div>
		        </fieldset> 
		        <div class="buttons">
		            <span class="button"><g:actionSubmit class="upload" value="${message(code: 'default.button.upload.label', default: 'Upload')}" action="update" /></span>
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
	                            <td><g:link action="delete" id="${fileResourceInstance.id}" onclick="return confirm('Are you sure?');"><g:message code="upload.delete.image.label"/></g:link></td>
	                        </tr>
	                    </g:each>
	                    </tbody>
	                </table>
	            </div>
            </g:if>
        </div>
    </body>
</html>
