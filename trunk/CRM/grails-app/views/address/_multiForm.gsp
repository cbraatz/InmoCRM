<%@ page import="crm.GUtils" %>
<%@ page import="crm.Address" %>
<fieldset class="form">
	<f:field bean="${parentBean? parentBean : 'address'}" property="${parentBean? 'address.streetOne' : 'streetOne'}"/>
	<f:field bean="${parentBean? parentBean : 'address'}" property="${parentBean?  'address.streetTwo' : 'streetTwo'}"/>
	<f:field bean="${parentBean? parentBean : 'address'}" property="${parentBean? 'address.number' : 'number'}"/>
	<f:field bean="${parentBean? parentBean : 'address'}" property="${parentBean? 'address.reference' : 'reference'}"/>
	<f:field bean="${parentBean? parentBean : 'address'}" property="${parentBean? 'address.addressLine' : 'addressLine'}">
		<g:textArea name="${property}" maxlength="500" value="${it.value}" rows="3" cols="60"/>
	</f:field>
	<f:field bean="${parentBean? parentBean : 'address'}" property="${parentBean? 'address.description' : 'description'}">
		<g:textArea name="${property}" maxlength="300" value="${it.value}" rows="3" cols="60"/>
	</f:field>
	<f:field bean="${parentBean? parentBean : 'address'}" property="${parentBean? 'address.code' : 'code'}"/>
	<f:field bean="${parentBean? parentBean : 'address'}" property="${parentBean? 'address.latitude' : 'latitude'}"/>
	<f:field bean="${parentBean? parentBean : 'address'}" property="${parentBean? 'address.longitude' : 'longitude'}"/>
	<a href="#mapmodals" data-toggle="modal" role="button" id="display-map-link"><g:message code="address.display.map,label" default="Display Map"/></a>
	<f:field bean="${parentBean? parentBean : 'address'}" property="${parentBean? 'address.homePhone' : 'homePhone'}"/>
	<f:field bean="${parentBean? parentBean : 'address'}" property="${parentBean? 'address.city' : 'city'}" widget-propId="${parentBean? (parentBean=='managedProperty'? managedProperty?.address?.city?.id : (parentBean=='client'? client?.address?.city?.id : (parentBean=='partner'? partner?.address?.city?.id : 'other_parent'))) : address?.city?.id}"/>
	<span class="fieldcontain">
		<span id="zone-label" class="property-label" style="margin: 1em 0.25em 0 0;"><g:message code="zone.label" default="zone"/></span>
	</span>
	<div id="zoneField"></div>
	<br/>
	<span class="fieldcontain">
		<span id="neighborhood-label" class="property-label" style="margin: 1em 0.25em 0 0;"><g:message code="neighborhood.label" default="neighborhood"/></span>
	</span>
	<div id="neighborhoodField"></div>
</fieldset>
	<g:render template="/address/map"/>
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

	//actualizar zone drop-down al cambiar city
	
	$(".city-selector").change(function() {
		cityChanged(this.value);
	});
	$(".address_city-selector").change(function() {//dos veces xq la clase del selector city es distinto en la pagina de address/create y client/create por ej
		cityChanged(this.value);
	});
	function cityChanged(cityId) {
		//el if a continuacion es para que al acceder directamente a por ej client/create sin estar logueado no me retorne la pagina de login en lugar del selector de zone.
		if("${session.user != null}"){
			var zoid="${(params.id ? (parentBean ? Class.forName('crm.'+GUtils.getFirstCharInUpperCase(parentBean)).get(params.id)?.address?.zone?.id : Address.get(params.id).zone?.id) : '')}"
	    	jQuery.ajax({type:'POST',data:{mainDomainType: "${parentBean}", cityId:cityId, zoneId:zoid, update:"T"} , url:'/zone/getZonesByCityAJAX',success:function(data,textStatus){console.log(data);jQuery('#zoneField').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){}});
		}
    }
	$(".city-selector").change();
	$(".address_city-selector").change();//dos veces xq la clase del selector city es distinto en la pagina de address/create y client/create por ej
	
	/*actualizar neighborhood drop-down al cambiar zone... todo esto comentado se hace en _dynamicZoneSelector.gsp ya que al actualizar la zona genera un nuevo selector que de otro modo no tendria este javascript
	$(".zone-selector").change(function() {
		zoneChanged(this.value);
	});
	$(".address_zone-selector").change(function() {
		zoneChanged(this.value);
	});
	*/

	function zoneChanged(zoneId) {
		//el if a continuacion es para que al acceder directamente a por ej client/create sin estar logueado no me retorne la pagina de login en lugar del selector de neighborhood.
		if(zoneId != 'null'){
			if("${session.user != null}"){
				var neid="${(params.id ? (parentBean ? Class.forName('crm.'+GUtils.getFirstCharInUpperCase(parentBean)).get(params.id)?.address?.neighborhood?.id : Address.get(params.id).neighborhood?.id) : '')}"
		    	jQuery.ajax({type:'POST',data:{mainDomainType: "${parentBean}", zoneId:zoneId, neighborhoodId:neid} , url:'/neighborhood/getNeighborhoodsByZoneAJAX',success:function(data,textStatus){console.log(data);jQuery('#neighborhoodField').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){}});
			}
		}else{
			$("#neighborhoodField").html('');
		}
    }
	//$(".zone-selector").change();
	//$(".address_zone-selector").change();//dos veces xq la clase del selector zone es distinto en la pagina de address/create y client/create por ej
</script>
