
<fieldset class="form">
	<f:field bean="neighborhood" property="name"/>
	<f:field bean="neighborhood" property="city" widget-propId="${neighborhood?.city?.id}"/>
	<f:field bean="neighborhood" property="description">
		<g:textArea name="${property}" maxlength="100" value="${it.value}" rows="2" cols="50"/>
	</f:field>
</fieldset>