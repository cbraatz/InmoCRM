<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>FileResource List</title>
    </head>
    <body>
        <div class="nav">
   			  <span class="menuButton"><a class="home" href="/">Home</a></span>
     </div>
        <div class="body content">
			<g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
			<h1><g:message code="upload.image.title"/></h1><br>
			<g:form method="post"  enctype="multipart/form-data">
			 	<fieldset class="fieldcontain">
			 		<span id="fileupload-label" class="property-label"><g:message code="upload.image.selector.label" default="Upload"/></span>
			 		<input type="file" id="fileUpload" name="fileUpload" class="property-value"/>
			 		<span id="fileName-label" class="property-label"><g:message code="upload.image.name.label" default="File Name"/></span>
			 		<g:textField id="description" name="description" class="property-value"/>
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
	                            <g:sortableColumn property="files" title="${message(code: 'upload.list.image.name.label', default: 'Upload')}"/>
	                            <g:sortableColumn property="description" title="${message(code: 'upload.list.image.description.label', default: 'Upload')}" colspan="3"/>
	                       </tr>
	                    </thead>
	                    <tbody>
	                    <g:each in="${fileResourceInstanceList}" status="i" var="fileResourceInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                            <td>${fileResourceInstance.fileName}</td>
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
