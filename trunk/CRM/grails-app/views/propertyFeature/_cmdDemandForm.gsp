<%@ page import="crm.commands.PropertyFeatureByPropertyDemandCommand" %>
<fieldset class="form">
	<div class="fieldcontain">
		<g:set var="pfcommand" value="${propertyFeatureByPropertyDemandCommand}" />
        <dl class="feature-list">
	       <g:each in="${pfcommand.pfitems}" var="item1" status="i1">
		        <h4 style="padding-top:20px;">${item1.propertyFeature?.name}
		        
					<span class="feature-line">
						<span class="feature-label sell-only"><g:message code="propertyDemand.feature.value.label" default="Value"/></span>
						<g:textField name="pfitems[$i1].value" value="${(item1.value? crm.Utils.getIntegerPartIfNoDecimals(item1.value) : 0)}" class="feature-value-field sell-only"/>
						<span class="feature-label buy-only"><g:message code="propertyDemand.feature.minValue.label" default="Min Value"/></span>
						<g:textField name="pfitems[$i1].minValue" value="${(item1.minValue? crm.Utils.getIntegerPartIfNoDecimals(item1.minValue) : 0)}" class="feature-value-field buy-only"/>
						<span class="feature-label buy-only"><g:message code="propertyDemand.feature.maxValue.label" default="Max Value"/></span>
						<g:textField name="pfitems[$i1].maxValue" value="${(item1.maxValue? crm.Utils.getIntegerPartIfNoDecimals(item1.maxValue) : 0)}" class="feature-value-field buy-only"/>
						<g:hiddenField name="pfitems[$i1].propertyFeature" value="${item1.propertyFeature?.id}"/>
						<g:hiddenField name="pfitems[$i1].propertyDemand" value="${item1.propertyDemand?.id}"/>
					</span>
				<h4/>
			</g:each>
			<span class="comment_note"><g:message code="concession.features.no.value.note" default="* value = 0 means NO"/></span>
		</dl>
	</div>
</fieldset>