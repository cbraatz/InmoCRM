<fieldset class="form">
	<div class="fieldcontain">
		<label for="fileUpload" id="fileupload-label" class="property-label"><g:message code="upload.image.selector.label" default="Upload"/></label>
		<input type="file" id="fileUpload" name="templateUpload" class="property-value"/>
	</div>
	<f:field bean="classicReportTemplate" property="description"/>
	<g:hiddenField name="name" value="${classicReportTemplate?.name}"/>
</fieldset>