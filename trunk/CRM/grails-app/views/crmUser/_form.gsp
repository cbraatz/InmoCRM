<fieldset class="form">
	<f:field bean="crmUser" property="login"/>
	<f:field bean="crmUser" property="password">
		<g:passwordField name="${property}" value="" />
	</f:field>
	<f:field property="password" label="Repeate password">
		<g:passwordField name="pass2" value="" />
	</f:field>
	<f:field bean="crmUser" property="emailAddress"/>
	<f:field bean="crmUser" property="partner" widget-propId="${crmUser?.partner?.id}"/>
	<f:field bean="crmUser" property="isActive"/>
	<f:field bean="crmUser" property="hasAccess"/>
	<g:if test="${session.user.isAdmin.booleanValue==true}">
    	<f:field bean="crmUser" property="isAdmin"/>
    </g:if>
    <g:else test="${session.user.isAdmin.booleanValue==true}">
    	<g:hiddenField name="isAdmin" value="${crmUser?.isAdmin}"/>
    </g:else>
	<g:hiddenField name="addedBy.id" value="${crmUser?.addedBy?.id}"/>
	
</fieldset>