<%@ page import="crm.enums.report.FilterCriteria" %>
<%@ page import="crm.enums.data.BooleanDataValue" %>
<%@ page import="crm.enums.data.DataType" %>
<%@ page import="crm.Utils" %>
<g:select id="fc-select-${colid}" onchange="filterChanged(this.value,$colid);" name="reportDesignerColumnsCommand.columnList[$colid].filterCriteria" value="${(null != fCriteria? FilterCriteria.valueOf(fCriteria) : null)}" from="${FilterCriteria.getAllFilterCriteriaList(dType)}" valueMessagePrefix="ENUM.FilterCriteria" />
<span id="hidenFldNumber-${colid}">${(null == fCriteria ? FilterCriteria.getAllFilterCriteriaList(dType).first().numberOfFields : FilterCriteria.valueOf(fCriteria).numberOfFields) }</span><!-- span con id hidenFldNumber- seguido del colid pasado como parametro -->
<g:if test="${dType.equals(DataType.DOMAIN.toString())}">
	<g:select id="CrmDomain-filter-select-${colid}" optionKey="id" name="reportDesignerColumnsCommand.columnList[$colid].primaryFilterValue" optionValue="${Class.forName(domType).getDefaultPropertyName()}" value="${(null != primValue? Class.forName(domType).get(new Long(primValue)) : null)}" from="${Class.forName(domType).getAll()}"/>
</g:if>
<g:else>
	<g:if test="${dType.equals(DataType.BOOLEAN.toString())}">
		<g:select class="first-filter-criteria-field-${colid}" name="reportDesignerColumnsCommand.columnList[$colid].primaryFilterValue" value="${(null != primValue? BooleanDataValue.valueOf(primValue) : true)}" from="${BooleanDataValue.getAllBooleanValues()}" valueMessagePrefix="ENUM.BooleanDataValue"/>
		<g:select class="second-filter-criteria-field-${colid}" name="reportDesignerColumnsCommand.columnList[$colid].secondaryFilterValue" value="${(null != secValue? BooleanDataValue.valueOf(secValue) : true)}" from="${BooleanDataValue.getAllBooleanValues()}" valueMessagePrefix="ENUM.BooleanDataValue"/>
	</g:if>
	<g:else>
		<g:textField class="first-filter-criteria-field-${colid}" name="reportDesignerColumnsCommand.columnList[$colid].primaryFilterValue" value="${primValue}"/>
		<g:textField class="second-filter-criteria-field-${colid}" name="reportDesignerColumnsCommand.columnList[$colid].secondaryFilterValue" value="${secValue}"/>
	</g:else>
</g:else>
<script>
	function filterChanged(filCriteria,coid) {
    	jQuery.ajax({type:'POST',data:'filterCriteriaName='+filCriteria, url:'/reportDesigner/getCategoryFieldNumberAJAX',success:function(data,textStatus){jQuery('#hidenFldNumber-'+coid).html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){}});
    	updateFields(coid);
    }
	function updateFields(cid) {
		var va=$("#hidenFldNumber-"+cid).html();
		console.log("updateFields="+va+" cid="+cid+"colid="+${colid});
		switch(va) {
	    	case '1':
	    		$(".first-filter-criteria-field-"+cid).show();
				$(".second-filter-criteria-field-"+cid).hide();
				console.log("Case 1");
	        	break;
	    	case '2':
	    		$(".first-filter-criteria-field-"+cid).show();
				$(".second-filter-criteria-field-"+cid).show();
				console.log("Case 2");
	        	break;
	    	default:
	    		$(".first-filter-criteria-field-"+cid).hide();
				$(".second-filter-criteria-field-"+cid).hide();  
				console.log("Case def");	
    	}
		
	}

	$(document).ready(function() {
		updateFields(${colid});//muestra el o los textfields de acuerdo al valor inicial del select
		$("#hidenFldNumber-"+${colid}).bind("DOMSubtreeModified",function() {//agrega el listener al span oculto que se actualiza luego de que el un valor del select sea seleccionado
			updateFields(${colid});//muestra el o los textfields de acuerdo al valor seleccionado en el select
		});
		$("#hidenFldNumber-"+${colid}).hide();//oculta el span al iniciar
	});
</script>