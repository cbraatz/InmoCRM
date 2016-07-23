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
	            <h1><g:message code="reportDesigner.step1.title" default="STEP 1"/></h1>
				<fieldset class="form">
				        <dl class="feature-list">
				            <label class="bold-title-label" style="margin-bottom:10px;"><g:message code="reportDesigner.step1.secondary.title" default="Select Report Type"/></label>
						    <dd style="margin-left:15px;">
                		 		<g:link class="report-option" action="step2" params="[rt:'10']"><g:message code="reportDesigner.real.estate.type1.label"/></g:link></li>
							</dd>
						</dl>
				</fieldset>
        	</div>
        </g:form>
    </body>
</html>