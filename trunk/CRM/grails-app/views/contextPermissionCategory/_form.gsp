
<fieldset class="form">
	<f:field bean="contextPermissionCategory" property="name"/>
	<g:hiddenField name="isAll" value="${contextPermissionCategory?.isAll}"/>
	<g:hiddenField name="isNone" value="${contextPermissionCategory?.isNone}"/>
</fieldset>