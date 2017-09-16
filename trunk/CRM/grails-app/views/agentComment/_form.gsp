
<fieldset class="form">
	<f:field bean="agentComment" property="contact">
		<g:textArea name="${property}" maxlength="255" value="${it.value}" rows="5" cols="60"/>
	</f:field>
	<f:field bean="agentComment" property="locale" input-propId="${agentComment?.locale?.id}"/>
	<g:hiddenField name="crmUser" value="${agentComment?.crmUser?.id}"/>
</fieldset>