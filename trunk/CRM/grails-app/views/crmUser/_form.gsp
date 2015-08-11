
<fieldset class="form">
	<f:field bean="crmUser" property="login"/>
	<f:field bean="crmUser" property="password">
		<g:passwordField name="${property}" value="${it.value}" />
	</f:field>
	<f:field property="password" label="Repeate password">
		<g:passwordField name="pass2" value="" />
	</f:field>
	<f:field bean="crmUser" property="emailAddress"/>
	<f:field bean="crmUser" property="partner" input-propId="${crmUser?.partner?.id}"/>
	<f:field bean="crmUser" property="isAdmin"/>
	<f:field bean="crmUser" property="isActive"/>
</fieldset>