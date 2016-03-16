<fieldset class="form">
	<f:field bean="buildingFeature" property="name"/>
	<f:field bean="buildingFeature" property="plural"/>
	<f:field bean="buildingFeature" property="description">
		<g:textArea name="${property}" maxlength="100" value="${it.value}" rows="2" cols="50"/>
	</f:field>
	<f:field bean="buildingFeature" property="defaultWebIcon"/>
	<f:field bean="buildingFeature" property="hasValue"/>
</fieldset>