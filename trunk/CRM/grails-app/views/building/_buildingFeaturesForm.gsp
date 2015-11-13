<%@ page import="crm.commands.FeatureByBuildingCommand" %>
<fieldset class="form">
	<div class="fieldcontain">
		<g:set var="bfcommand" value="${featureByBuildingCommand}" />
        <dl class="feature-list">
	       <g:each in="${bfcommand.bfitems}" var="item2" status="i2">
		        <dt class="feature-title-label"><h4>${item2.buildingFeature?.name}<h4/></dt>
		        <dd>
					<span class="feature-line">
						<span class="feature-label"><g:message code="concession.feature.value.label" default="Value"/></span>
						<g:textField name="bfitems[$i2].value" value="${crm.Utils.getIntegerPartIfNoDecimals(item2.value)}" class="feature-value-field"/>
						<span class="feature-label"><g:message code="concession.feature.description.label" default="Description"/></span>
						<g:textField name="bfitems[$i2].description" value="${item2.description}" class="feature-description-field"/>
						<g:hiddenField name="bfitems[$i2].buildingFeature" value="${item2.buildingFeature?.id}"/>
					</span>
				</dd>
			</g:each>
			<span class="comment_note"><g:message code="concession.features.no.value.note" default="* value = 0 means NO"/></span>
		</dl>
	</div>
</fieldset>