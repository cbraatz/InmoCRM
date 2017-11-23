<fieldset class="form">
	<div class="fieldcontain">	
		<f:field bean="contract" property="contractType" widget-propId="${contract?.contractType?.id}"/>
		
		<g:hiddenField name="isCurrentContract" value="${contract?.isCurrentContract}"/>	
		<g:hiddenField name="internalID" value="${contract?.internalID}"/>	
	</div>		
</fieldset>
		