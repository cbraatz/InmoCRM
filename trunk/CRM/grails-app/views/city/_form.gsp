
<fieldset class="form">
	<f:field bean="city" property="name"/>
	<f:field bean="city" property="department" input-propId="${city?.department?.id}"/>
</fieldset>