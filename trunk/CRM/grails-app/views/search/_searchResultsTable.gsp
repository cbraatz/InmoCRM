<div class="crm-table">
	<table>
	    <thead>
	         <tr>
	            <th class="sortable">Resultado</a></th>
	            <th class="sortable">Objeto</a></th>
	        </tr>
	    </thead>
	    <tbody>
	    	<g:each in="${list}" var="result">
		    	<tr class="even">
		        	<td><a href="${result.linkTo}"><span class="property-value" aria-labelledby="displayValue-label">${result.displayValue}</span></a></td>
		            <td><span class="property-value" aria-labelledby="objectName-label">${result.objectName}</span></td>
		        </tr>
	        </g:each>
	    </tbody>
	</table>
</div>