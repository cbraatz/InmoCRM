
<fieldset class="form">
	<f:field bean="webPage" property="price"/>
	<f:field bean="webPage" property="operationType"/>
	<f:field bean="webPage" property="domain" widget-propId="${webPage?.domain?.id}"/>
	<f:field bean="webPage" property="title"/>
	<f:field bean="webPage" property="summary">
		<g:textArea name="${property}" maxlength="300" value="${it.value}" rows="5" cols="60"/>
	</f:field>
	<f:field bean="webPage" property="firstDescription">
		<g:textArea name="${property}" maxlength="300" value="${it.value}" rows="5" cols="60"/>
	</f:field>
	<f:field bean="webPage" property="secondDescription">
		<g:textArea name="${property}" maxlength="300" value="${it.value}" rows="5" cols="60"/>
	</f:field>
	<f:field bean="webPage" property="thirdDescription">
		<g:textArea name="${property}" maxlength="300" value="${it.value}" rows="5" cols="60"/>
	</f:field>
	<f:field bean="webPage" property="callToAction">
		<g:textArea name="${property}" maxlength="300" value="${it.value}" rows="5" cols="60"/>
	</f:field>
	<f:field bean="webPage" property="agentComment">
		<g:textArea name="${property}" maxlength="255" value="${it.value}" rows="5" cols="60"/>
	</f:field>
	<f:field bean="webPage" property="keyWords">
		<g:textArea name="${property}" maxlength="500" value="${it.value}" rows="8" cols="60"/>
	</f:field>
	<g:hiddenField name="inWeb" value="${webPage?.inWeb}"/>	
	<g:hiddenField name="managedProperty.id" value="${webPage?.managedProperty?.id}"/>
</fieldset>