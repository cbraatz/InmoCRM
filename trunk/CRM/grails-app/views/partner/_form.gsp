
<fieldset class="form">
	<f:field bean="partner" property="name"/>
	<f:field bean="partner" property="IDNumber"/>
	<f:field bean="partner" property="birthDate"/>
	<f:field bean="partner" property="phone"/>
	<f:field bean="partner" property="corporatePhone"/>
	<f:field bean="partner" property="emailAddress"/>
	<f:field bean="partner" property="country" widget-propId="${client?.country?.id}"/>
	<f:field bean="partner" property="gender" widget-propId="${partner?.gender?.id}"/>
	<f:field bean="partner" property="profession" widget-propId="${partner?.profession?.id}"/>
	<f:field bean="partner" property="partnerRole" widget-propId="${partner?.partnerRole?.id}"/>
	<f:field bean="partner" property="maritalStatus" widget-propId="${partner?.maritalStatus?.id}"/>
	<f:field bean="partner" property="invitedBy" widget-propId="${partner?.invitedBy?.id}"/>
	<div class="fieldcontain">
		<label id="partner-label" class="property-label"><g:message code="partner.photo.label" default="Photo"/></label>
		<input type="file" id="fileUpload" name="photo" class="property-value"/>
	</div>
	<f:field bean="partner" property="isAgent"/>
	<f:field bean="partner" property="isActive"/>
</fieldset>

<h1><g:message code="address.label"/></h1>
<g:render template="/address/multiForm" model="['parentBean':'partner']"/>