	<g:render template="form"/>	
	
	<h1><g:message code="concession.property.title"/></h1>
	<g:render template="/managedProperty/form"/>
	
	<h1><g:message code="concession.address.title"/></h1>
	<g:render template="/address/multiForm" model="['parentBean':'managedProperty']"/>
	
	<h1><g:message code="propertyFeature.features.title"/></h1>
	<g:render template="/propertyFeature/cmdForm"/>

	<h1><g:message code="concession.building.title"/></h1>
	<fieldset class="form">
		<div class="fieldcontain">
			<label><g:message code="concession.building.has.building.label" default="Has Building"/></label>
			<g:checkBox name="hasBuilding" value="${displayBuilding}" id="hasBuilding"/>
		</div>
	</fieldset>
	<div id="building-section">
		<g:render template="/building/form"/>
		<h1><g:message code="buildingFeature.features.title"/></h1>
		<g:render template="/buildingFeature/cmdForm"/>
	</div>

<script>
	function displayOrHideBuildingSection(){
		if($("#hasBuilding").is(":checked")){
			$("#building-section").show();
		}else{
			$("#building-section").hide();
		}
	}
	
	$("#hasBuilding").change(function() {
		displayOrHideBuildingSection();
	});
	
	displayOrHideBuildingSection();
</script>
	
