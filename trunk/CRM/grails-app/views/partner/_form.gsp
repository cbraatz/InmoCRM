
<fieldset class="form">
	<f:field bean="partner" property="name"/>
	<f:field bean="partner" property="lastName"/>
	<f:field bean="partner" property="IDNumber"/>
	<f:field bean="partner" property="birthDate"/>
	<f:field bean="partner" property="phone"/>
	<f:field bean="partner" property="phone2"/>
	<f:field bean="partner" property="emailAddress"/>
	<f:field bean="partner" property="gender" input-propId="${partner?.gender?.id}"/>
	<f:field bean="partner" property="profession" input-propId="${partner?.profession?.id}"/>
	<f:field bean="partner" property="partnerRole" input-propId="${partner?.partnerRole?.id}"/>
	<f:field bean="partner" property="maritalStatus" input-propId="${partner?.maritalStatus?.id}"/>
	<f:field bean="partner" property="invitedBy" input-propId="${partner?.invitedBy?.id}"/>
	<f:field bean="partner" property="isActive"/>
</fieldset>

<h1><g:message code="address.label"/></h1>
<g:render template="/address/multiForm" model="['parentBean':'partner']"/>