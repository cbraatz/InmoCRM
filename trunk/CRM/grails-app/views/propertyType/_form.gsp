
<fieldset class="form">
	<f:field bean="propertyType" property="name"/>
	<f:field bean="propertyType" property="dimensionMeasuringUnit" input-propId="${propertyType?.dimensionMeasuringUnit?.id}"/>
	<f:field bean="propertyType" property="description">
		<g:textArea name="${property}" maxlength="100" value="${it.value}" rows="2" cols="50"/>
	</f:field>
	<f:field bean="propertyType" property="keywords">
		<g:textArea name="${property}" maxlength="500" value="${it.value}" rows="6" cols="50"/>
	</f:field>
</fieldset>