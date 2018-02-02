<fieldset class="form">
	<f:field bean="customReport" property="name"/>
	<f:field bean="customReport" property="customReportTemplate" widget-propId="${customReport?.customReportTemplate?.id}"/>
	<f:field bean="customReport" property="reportFolder" widget-propId="${customReport?.reportFolder?.id}"/>
	<g:hiddenField name="crmUser.id" value="${session?.user?.id}"/>
</fieldset>