<fieldset class="form">
	<f:field bean="crmUser" property="name"/>
	<f:field bean="crmUser" property="password">
		<g:passwordField name="${property}" value="" />
	</f:field>
	<f:field property="password" label="Repeate password">
		<g:passwordField name="pass2" value="" />
	</f:field>
	<f:field bean="crmUser" property="emailAddress"/>
	<f:field bean="crmUser" property="partner" input-propId="${crmUser?.partner?.id}"/>
	<f:field bean="crmUser" property="isActive"/>
	<g:hiddenField name="addedBy.id" value="${crmUser?.addedBy?.id}"/>
</fieldset>