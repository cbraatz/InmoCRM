<%@ page import="crm.GUtils" %>
<fieldset class="form">
	<f:field bean="neighborhood" property="name"/>
	<f:field bean="neighborhood" property="city" widget-propId="${neighborhood?.city?.id}"/>
	<span class="fieldcontain">
		<span id="zone-label" class="property-label" style="margin: 1em 0.25em 0 0;"><g:message code="zone.label" default="zone"/></span>
	</span>
	<div id="zoneField"></div>
	<f:field bean="neighborhood" property="description">
		<g:textArea name="${property}" maxlength="100" value="${it.value}" rows="2" cols="50"/>
	</f:field>
</fieldset>
<script>
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
			var zoid="${(params.id ? crm.Neighborhood.get(params.id).zone?.id : '')}"
	    	jQuery.ajax({type:'POST',data:{mainDomainType: "${parentBean}", cityId:cityId, zoneId:zoid, update:"N"} , url:'/zone/getZonesByCityAJAX',success:function(data,textStatus){console.log(data);jQuery('#zoneField').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){}});
		}
    }
	$(".city-selector").change();
	$(".address_city-selector").change();//dos veces xq la clase del selector city es distinto en la pagina de address/create y client/create por ej
</script>