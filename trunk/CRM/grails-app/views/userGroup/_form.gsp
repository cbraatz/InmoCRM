<fieldset class="form">
	<f:field bean="userGroup" property="name"/>
	<g:hiddenField name="isAdmin" value="${userGroup?.isAdmin}"/>
</fieldset>