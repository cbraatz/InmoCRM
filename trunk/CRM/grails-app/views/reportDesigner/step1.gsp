<%@ page import="crm.commands.ReportMainCommand" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="reportDesigner.step2.title"/></title>
    </head>
    <body>
        <a href="#create-buildingFeature" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <g:form>
	        <div class="nav" role="navigation">
	            <ul>
	                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
	            </ul>
	        </div>
	        <div id="create-buildingFeature" class="content scaffold-create" role="main">
	            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
				<fieldset class="form">
				        <dl class="feature-list">
				            <label class="bold-title-label pf-language-field"><g:message code="propertyFeature.language.label" default="Language"/></label><label class="bold-title-label pf-name-field"><g:message code="propertyFeature.name.label" default="Name"/></label><label class="bold-title-label pf-plural-label"><g:message code="propertyFeature.plural.label" default="Name in Plural"/></label>
						    <dd>
                		 		<g:link class="report-option" action="step2" params="[rt:'10']"><g:message code="reportDesigner.real.estate.type1.label"/></g:link></li>
							</dd>
						</dl>
				</fieldset>
        	</div>
        </g:form>
    </body>
</html>