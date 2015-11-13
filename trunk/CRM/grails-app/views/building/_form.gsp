<%@ page import="crm.commands.FeatureByBuildingCommand" %>
<%@ page import="crm.commands.FeatureByPropertyCommand" %>
<fieldset class="form">
	<div class="fieldcontain">
		<f:field bean="building" property="builtSize"/>
		<f:field bean="building" property="builtYear"/>
		<f:field bean="building" property="buildingType" input-propId="${building?.buildingType?.id}"/>
		<f:field bean="building" property="buildingCondition" input-propId="${building?.buildingCondition?.id}"/>
		<f:field bean="building" property="buildingDescription">
			<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="4" cols="50"/>
		</f:field>
	</div>
</fieldset>