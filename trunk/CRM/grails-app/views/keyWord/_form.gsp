
<fieldset class="form">
	<f:field bean="keyWord" property="keys">
		<g:textArea name="${property}" maxlength="500" value="${it.value}" rows="8" cols="60"/>
	</f:field>
	<f:field bean="keyWord" property="locale" widget-propId="${keyWord?.locale?.id}"/>
	<f:field bean="keyWord" property="propertyType" widget-propId="${keyWord?.propertyType?.id}"/>
</fieldset>