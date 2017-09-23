<fieldset class="form">
	<f:field bean="classicReport" property="name"/>
	<f:field bean="classicReport" property="classicReportTemplate" widget-propId="${classicReport?.classicReportTemplate?.id}"/>
	<f:field bean="classicReport" property="reportFolder" widget-propId="${classicReport?.reportFolder?.id}"/>
	<g:hiddenField name="crmUser.id" value="${session?.user?.id}"/>
</fieldset>