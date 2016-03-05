
<fieldset class="form">
	<f:field bean="keyWord" property="keys">
		<g:textArea name="${property}" maxlength="500" value="${it.value}" rows="8" cols="60"/>
	</f:field>
	<f:field bean="keyWord" property="locale" input-propId="${keyWord?.locale?.id}"/>
	<f:field bean="keyWord" property="propertyType" input-propId="${keyWord?.propertyType?.id}"/>
	<f:field bean="keyWord" property="buildingType" input-propId="${keyWord?.buildingType?.id}"/>
</fieldset>