
<fieldset class="form">
	<f:field bean="vendor" property="name"/>
	<f:field bean="vendor" property="TIN"/>
	<f:field bean="vendor" property="phone"/>
	<f:field bean="vendor" property="emailAddress"/>
	<f:field bean="vendor" property="description">
		<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="4" cols="50"/>
	</f:field>
</fieldset>