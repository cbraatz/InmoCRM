
<fieldset class="form">
	<f:field bean="city" property="name"/>
	<f:field bean="city" property="department" widget-propId="${city?.department?.id}"/>
</fieldset>