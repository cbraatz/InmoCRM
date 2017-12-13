
<fieldset class="form">
	<f:field bean="zone" property="name"/>
	<f:field bean="zone" property="city" widget-propId="${zone?.city?.id}"/>
	<f:field bean="zone" property="description">
		<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="4" cols="50"/>
	</f:field>
	<g:hiddenField name="isCenter" value="${zone?.isCenter}"/>
</fieldset>