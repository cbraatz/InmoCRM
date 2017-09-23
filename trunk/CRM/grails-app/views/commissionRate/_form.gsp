
<fieldset class="form">
	<f:field bean="commissionRate" property="name"/>
	<f:field bean="commissionRate" property="percentage"/>
	<f:field bean="commissionRate" property="commissionType" widget-propId="${commissionRate?.commissionType?.id}"/>
</fieldset>