<%@ page import="crm.Zone" %>     
<%@ page import="crm.City" %>   
<g:select name="${(doty.isEmpty() ? 'zone' : 'address.zone')}.id" from="${Zone.findAllByCity(City.get(cid))}" optionKey="id" optionValue="name" value="${zid ? zid : null}" noSelection="${['null': message(code: 'default.select.one.label', default: 'Select One...')]}" class="zone-selector one-to-many"  style="margin-top: 1em;"/>
<g:link class="add" controller="zone" action="create">MI</g:link> 
<script>
    if(${upd!=null && upd.equals("T")}){
		$(".zone-selector").change(function() {
			zoneChanged(this.value);
		});
		$(".address_zone-selector").change(function() {//dos veces xq la clase del selector zone es distinto en la pagina de address/create y client/create por ej
			zoneChanged(this.value);
		});
		$(".zone-selector").change();
		$(".address_zone-selector").change();//dos veces xq la clase del selector zone es distinto en la pagina de address/create y client/create por ej
	}
</script>