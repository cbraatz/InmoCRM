<fieldset class="form">
	<div class="fieldcontain">		
		<f:field bean="managedProperty" property="propertyType" widget-propId="${managedProperty?.propertyType?.id}"/>
		<f:field bean="managedProperty" property="title"/>
		<f:field bean="managedProperty" property="propertyDescription">
			<g:textArea name="${property}" maxlength="500" value="${it.value}" rows="4" cols="70"/>
		</f:field>
		<f:field bean="managedProperty" property="measures"/>
		<f:field bean="managedProperty" property="area"/>
		<f:field bean="managedProperty" property="excess"/>
		<f:field bean="managedProperty" property="publicAddress"/>
		<f:field bean="managedProperty" property="publicCashPrice"/>
		<f:field bean="managedProperty" property="publicCreditPrice"/>
		<f:field bean="managedProperty" property="gift"/>
		<f:field bean="managedProperty" property="currency" widget-propId="${managedProperty?.currency?.id}"/>
		<f:field bean="managedProperty" property="price"/>
		<f:field bean="managedProperty" property="value"/>
		<f:field bean="managedProperty" property="clientInitialPrice"/>
		<f:field bean="managedProperty" property="commissionAmount"/>
		<f:field bean="managedProperty" property="valueDegree"/>
		<f:field bean="managedProperty" property="placedBillboards"/>
		<g:hiddenField name="resolution" value="${managedProperty?.resolution}"/>
		<g:hiddenField name="internalID" value="${managedProperty?.relatedToID}"/>
	</div>		
</fieldset>

<h1><g:message code="address.label"/></h1>
<g:render template="/address/multiForm" model="['parentBean':'managedProperty']"/>