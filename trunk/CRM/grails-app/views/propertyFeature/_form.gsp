
<fieldset class="form">
	<f:field bean="propertyFeature" property="name"/>
	<f:field bean="propertyFeature" property="description">
		<g:textArea name="${property}" maxlength="100" value="${it.value}" rows="2" cols="50"/>
	</f:field>
</fieldset>