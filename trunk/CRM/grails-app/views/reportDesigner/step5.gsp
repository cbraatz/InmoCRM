<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="reportDesigner.step5.title"/></title>
    </head>
    <body>
        <a href="#create-buildingFeature" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <g:form>
	        <div class="nav" role="navigation">
	            <ul>
	                <li><g:actionSubmit action="step4" class="return" value="${message(code: 'reportDesigner.previous.step.button', default: 'Previous')}" /></li>
	            </ul>
	        </div>
	        <g:hasErrors bean="${reportDesigner}">
	            <ul class="errors" role="alert">
	                <g:eachError bean="${reportDesigner}" var="error">
	                	<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
	                </g:eachError>
	            </ul>
	        </g:hasErrors>
	        <div id="reportDesigner-step3" class="content scaffold-create" role="main">
	         	<h1><g:message code="reportDesigner.step5.title" default="STEP 5"/></h1> 
	         	<f:field bean="reportDesigner" property="name"/>
	         	<f:field bean="reportDesigner" property="reportFolder" input-propId="${reportDesigner?.reportFolder?.id}"/>
				<g:each in="${reportDesigner.reportDesignerColumns}" var="item1" status="i">
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].selected" value="${item1?.selected}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].filterBy" value="${item1?.filterBy}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].sortBy" value="${item1?.sortBy}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].groupBy" value="${item1?.groupBy}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].sortOrder" value="${item1?.sortOrder}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].dataType" value="${item1?.dataType}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].groupOrder" value="${item1?.groupOrder}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].tableName" value="${item1?.tableName}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].propertyName" value="${item1?.propertyName}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].foreignTableName" value="${item1?.foreignTableName}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].foreignTableDisplay" value="${item1?.foreignTableDisplay}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].columnWidth" value="${item1?.columnWidth}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].filterCriteria" value="${item1?.filterCriteria}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].primaryFilterValue" value="${item1?.primaryFilterValue}"/>
					<g:hiddenField name="reportDesigner.reportDesignerColumns[$i].secondaryFilterValue" value="${item1?.secondaryFilterValue}"/>
				</g:each>
				<g:hiddenField name="reportDesigner.reportType" value="${reportDesigner?.reportType}"/>
				<g:hiddenField name="reportDesigner.hasFilter" value="${reportDesigner?.hasFilter}"/>
				<g:hiddenField name="reportDesigner.hasGroup" value="${reportDesigner?.hasGroup}"/>
				<g:hiddenField name="reportDesigner.hasSort" value="${reportDesigner?.hasSort}"/>
                <fieldset class="buttons">
                	<g:actionSubmit action="step4" class="return" value="${message(code: 'reportDesigner.previous.step.button', default: 'Previous')}" />
                    <g:actionSubmit action="save" class="save" value="${message(code: 'default.button.save.label', default: 'Save Report')}"/>
                </fieldset>
        	</div>
        </g:form>
    </body>
</html>