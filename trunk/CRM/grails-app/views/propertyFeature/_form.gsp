<%@ page import="crm.commands.FeatureByPropertyCommand" %>
<fieldset class="form">
	<div class="fieldcontain">
		<g:set var="pfcommand" value="${featureByPropertyCommand}" />
        <dl class="feature-list">
	       <g:each in="${pfcommand.pfitems}" var="item1" status="i1">
		        <dt class="feature-title-label"><h4>${item1.propertyFeature?.name}<h4/></dt>
		        <dd>
					<span class="feature-line">
						<span class="feature-label"><g:message code="concession.feature.value.label" default="Value"/></span>
						<g:textField name="pfitems[$i1].value" value="${(item1.value? crm.Utils.getIntegerPartIfNoDecimals(item1.value) : 0)}" class="feature-value-field"/>
						<span class="feature-label"><g:message code="concession.feature.description.label" default="Description"/></span>
						<g:textField name="pfitems[$i1].description" value="${item1.description}" class="feature-description-field"/>
						<g:hiddenField name="pfitems[$i1].propertyFeature" value="${item1.propertyFeature?.id}"/>
					</span>
				</dd>
			</g:each>
			<span class="comment_note"><g:message code="concession.features.no.value.note" default="* value = 0 means NO"/></span>
		</dl>
	</div>
</fieldset>