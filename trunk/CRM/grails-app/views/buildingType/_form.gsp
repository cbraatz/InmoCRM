
<fieldset class="form">
	<f:field bean="buildingType" property="name"/>
	<f:field bean="buildingType" property="dimensionMeasuringUnit" input-propId="${buildingType?.dimensionMeasuringUnit?.id}"/>
	<f:field bean="buildingType" property="description">
		<g:textArea name="${property}" maxlength="100" value="${it.value}" rows="2" cols="50"/>
	</f:field>
</fieldset>