 <fieldset class="form">
	<div class="buy-demand">
		<f:field bean="propertyDemand" property="isSellDemand"/>
		<div class="grouping-box demand-info">
			<f:field bean="propertyDemand" property="name"/>
			<f:field bean="propertyDemand" property="creator" input-propId="${propertyDemand?.creator?.id}"/>
			<f:field bean="propertyDemand" property="assignee" input-propId="${propertyDemand?.assignee?.id}"/>
			<g:if test="${actionName.equals('edit')}">
				<f:field bean="propertyDemand" property="demandStatus" input-propId="${propertyDemand?.demandStatus?.id}"/>
			</g:if>
			<f:field bean="propertyDemand" property="addedDate"/>
			<f:field bean="propertyDemand" property="dueDate"/>
			<f:field bean="propertyDemand" property="client" input-propId="${propertyDemand?.client?.id}"/>
			<f:field bean="propertyDemand" property="priorityLevel" input-propId="${propertyDemand?.priorityLevel?.id}"/>
			<f:field bean="propertyDemand" property="interestLevel" input-propId="${propertyDemand?.interestLevel?.id}"/>
		</div>
		<f:field bean="propertyDemand" property="propertyType" input-propId="${propertyDemand?.propertyType?.id}"/>
		<f:field bean="propertyDemand" property="specifyPropertyType"/>
		<div class="buy-only">
			<f:field bean="propertyDemand" property="isPropertyTypeRequired"/>
		</div>
		<f:field bean="propertyDemand" property="buildingType" input-propId="${propertyDemand?.buildingType?.id}" input-allowNull="${true}"/>
		<f:field bean="propertyDemand" property="specifyBuildingType"/>
		<div class="buy-only">
			<f:field bean="propertyDemand" property="isBuildingTypeRequired"/>
		</div>
		<f:field bean="propertyDemand" property="buildingCondition" input-propId="${propertyDemand?.buildingCondition?.id}"/>
		<div class="buy-only">
			<f:field bean="propertyDemand" property="isBuildingConditionRequired"/>
		</div>
		<f:field bean="propertyDemand" property="department" input-propId="${propertyDemand?.department?.id}"/>
		<div class="buy-only">
			<f:field bean="propertyDemand" property="isDepartmentRequired"/>
		</div>
		<f:field bean="propertyDemand" property="city" input-propId="${propertyDemand?.city?.id}" input-allowNull="${true}"/>
		<f:field bean="propertyDemand" property="specifyCity"/>
		<div class="buy-only">
			<f:field bean="propertyDemand" property="isCityRequired"/>
		</div>
		<span class="fieldcontain">
			<span id="neighborhood-label" class="property-label"  style="margin: 1em 0.25em 0 0;"><g:message code="propertyDemand.neighborhood.label" default="neighborhood"/></span>
		</span>
		<div id="neighborhoodField"></div>
		<f:field bean="propertyDemand" property="specifyNeighborhood"/>
		<div class="buy-only">
			<f:field bean="propertyDemand" property="isNeighborhoodRequired"/>
		</div>
		<f:field bean="propertyDemand" property="specifyZone"/>
		<div class="buy-only">
			<f:field bean="propertyDemand" property="isZoneRequired"/>
		</div>
		<g:hiddenField name="isUsageRequired" value="${propertyDemand?.isUsageRequired}"/>
		<div class="sell-only">
			<f:field bean="propertyDemand" property="propertyArea"/>
			<f:field bean="propertyDemand" property="buildingArea"/>
		</div>
		<div class="buy-only">
			<f:field bean="propertyDemand" property="propertyMinArea"/>
			<f:field bean="propertyDemand" property="propertyMaxArea"/>
			<f:field bean="propertyDemand" property="isAreaRequired"/>
		</div>
		<f:field bean="propertyDemand" property="specifyPropertyFeatures"/>
		<f:field bean="propertyDemand" property="specifyBuildingFeatures"/>
		<f:field bean="propertyDemand" property="timeAvailability"/>
		<div class="buy-only">
			<!--<f:field bean="propertyDemand" property="offersOnly"/>-->
		</div>
		<div class="sell-only">
			<f:field bean="propertyDemand" property="price"/>
		</div>
		<div class="buy-only">
			<f:field bean="propertyDemand" property="minPrice"/>
			<f:field bean="propertyDemand" property="maxPrice"/>
		</div>
		<f:field bean="propertyDemand" property="specifyPrice"/>
		<div class="buy-only">
			<!--<f:field bean="propertyDemand" property="isPriceRequired"/>-->
		</div>
		<f:field bean="propertyDemand" property="currency" input-propId="${propertyDemand?.currency?.id}"/>
		<f:field bean="propertyDemand" property="additionalDescription">
			<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="3" cols="60"/>
		</f:field>
		<div class="sell-only">
			<f:field bean="propertyDemand" property="broadcastMedia" input-propId="${propertyDemand?.broadcastMedia?.id}" input-allowNull="${true}"/>
			<f:field bean="propertyDemand" property="specifyBroadcastMedia">
				<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="2" cols="60"/>
			</f:field>
		</div>
	</div>
</fieldset>
<script>
	function displayOrHideFields(){
		if($("#isSellDemand").is(":checked")){
			$(".buy-only").hide();
			$(".sell-only").show();
			$(".buy-demand").addClass("sell-demand");
		  	$(".buy-demand").removeClass("buy-demand");
		}else{
			$(".buy-only").show();
			$(".sell-only").hide();
			$(".sell-demand").addClass("buy-demand");
		  	$(".sell-demand").removeClass("sell-demand");
		}
	}
	
	$("#isSellDemand").change(function() {
		displayOrHideFields();
	});
	
	displayOrHideFields();

	//actualizar neighborhood drop-down al seleccionar city
	
	$(".city-selector").change(function() {
		cityChanged(this.value);
	});
	
	function cityChanged(cityId) {
		if("${session.user != null}"){
			//mainDomainType es vacio porque o sino en el selector pone address.neighborhood en lugar de solo neighborhood
    		jQuery.ajax({type:'POST',data:{mainDomainType: '', cityId:cityId, neighborhoodId:"${(propertyDemand?.neighborhood?.id ? propertyDemand?.neighborhood?.id : null)}"} , url:'/neighborhood/getNeighborhoodsByCityAJAX',success:function(data,textStatus){console.log(data);jQuery('#neighborhoodField').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){}});
		}
    }
	$(".city-selector").change();
</script>















