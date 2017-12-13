<fieldset class="form">
	<div class="fieldcontain">
		<f:field bean="concession" property="startDate"/>
		<f:field bean="concession" property="endDate"/>
		<g:hiddenField name="totalPrice" value="${concession?.totalPrice}"/>
		<g:hiddenField name="totalCommission" value="${concession?.totalCommission}"/>
		<f:field bean="concession" property="client"  widget-propId="${concession?.client?.id}"/>
		<f:field bean="concession" property="description">
			<g:textArea name="${property}" maxlength="1500" value="${it.value}" rows="4" cols="50"/>
		</f:field>
		<f:field bean="concession" property="barter"/>
		<f:field bean="concession" property="financing"/>
		<f:field bean="concession" property="isNegotiable"/>
		<f:field bean="concession" property="publishInMLS"/>
		<f:field bean="concession" property="publishInPortals"/>
		<f:field bean="concession" property="crmUser" widget-propId="${concession?.crmUser?.id}"/>
		<f:field bean="concession" property="propertyDemand"  widget-propId="${concession?.propertyDemand?.id}" widget-allowNull="${true}"/>
		
		<g:hiddenField name="isActive" value="${concession?.isActive}"/>
		<g:hiddenField name="isForRent" value="${concession?.isForRent}"/>
		<g:hiddenField name="internalID" value="${concession?.internalID}"/>
	</div>
</fieldset>