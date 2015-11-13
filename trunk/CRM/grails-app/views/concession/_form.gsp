
<fieldset class="form">
	<div class="fieldcontain">
		<f:field bean="concession" property="startDate"/>
		<f:field bean="concession" property="endDate"/>
		<f:field bean="concession" property="commissionAmount"/>
		<f:field bean="concession" property="commissionPercentage"/>
		<f:field bean="concession" property="client"  input-propId="${concession?.client?.id}"/>
		<f:field bean="concession" property="description">
			<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="4" cols="50"/>
		</f:field>
		<f:field bean="concession" property="barter"/>
		<f:field bean="concession" property="financing"/>
		<f:field bean="concession" property="isNegotiable"/>
		<f:field bean="concession" property="publishInMLS"/>
		<f:field bean="concession" property="publishInPortals"/>
		<f:field bean="concession" property="keys"/>
		<f:field bean="concession" property="addedBy" input-propId="${concession?.addedBy?.id}"/>
		<f:field bean="concession" property="adSummary"/>
		<f:field bean="concession" property="adText">
			<g:textArea name="${property}" maxlength="500" value="${it.value}" rows="4" cols="70"/>
		</f:field>
		
		<g:hiddenField name="propertyDemand" value="${concession?.propertyDemand?.id}"/>
		<g:hiddenField name="isActive" value="${concession?.isActive}"/>
		<g:hiddenField name="isSoldByCompany" value="${concession?.isSoldByCompany}"/>
		
		<h1><g:message code="concession.contract.title"/></h1>
		<f:field bean="concession" property="contract.documentURL"/>
		<f:field bean="concession" property="contract.contractType" input-propId="${concession?.contract?.contractType?.id}"/>
		<f:field bean="concession" property="contract.internalID"/>
	</div>
</fieldset>