<fieldset class="form">
	<f:field bean="commissionByProperty" property="partner" widget-propId="${commissionByProperty?.partner?.id}"/>
	<f:field bean="commissionByProperty" property="percentage"/>
	<f:field bean="commissionByProperty" property="amount"/>
	<f:field bean="commissionByProperty" property="commissionRate"  widget-propId="${commissionByProperty?.commissionRate?.id}"/>
	<f:field bean="commissionByProperty" property="description">
		<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="4" cols="50"/>
	</f:field>
	<g:hiddenField name="managedProperty" value="${commissionByProperty?.managedProperty?.id}"/>
 </fieldset> 
		     