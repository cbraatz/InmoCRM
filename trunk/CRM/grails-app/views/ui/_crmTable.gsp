not used at the moment, call this template using something like:
<g:render template="/ui/crmTable" model="['tableList':list, 'properties':['prop1','prop2']]"/>

<%@ page import="crm.GUtils" %>
<g:if test="${tableList}">
	<g:if test="${tableList.size > 0}">
		<g:set var="className" value="${GUtils.getLowerClassNameFromInstance(tableList.first())}" />

		<div class="crm-table">
			<table>
			    <thead>
			         <tr>
			         	<g:each in="${properties}" var="prop">
				            <th class="sortable"><a href="/search/show?sort=${prop}&amp;order=asc"><g:message code="${className}.${prop}.label" default="${prop}"/></a></th>
			            </g:each>
			        </tr>
			    </thead>
			    <tbody>
			    	<g:each in="${tableList}" var="item">
				    <tr class="even">
				    	<g:each in="${properties}" var="prop" status="i">
				    		<g:if test="${i==0}">
				        		<td><a href="/${className}/show/${item.id}"><span class="property-value" aria-labelledby="displayValue-label">valor del item</span></a></td>
				            </g:if>
				            <g:else>
				            	<td><span class="property-value" aria-labelledby="objectName-label">valor del item</span></td>
				            </g:else>
				        </g:each>
				    </tr>
			        </g:each>
			    </tbody>
			</table>
		</div>
	</g:if>
</g:if>
<g:else>
	<p><g:message code="default.empty.list.label"/></p>
</g:else>