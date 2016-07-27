<%@ page import="crm.enums.FilterCriteria" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="reportDesigner.step4.title"/></title>
    </head>
    <body>
        <a href="#create-buildingFeature" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <g:form>
        	<div class="nav" role="navigation">
	            <ul>
	                <li><g:actionSubmit action="step3" class="return" value="${message(code: 'reportDesigner.previous.step.button', default: 'Previous')}" /></li>
	                <li><g:actionSubmit action="step5" class="advance" value="${message(code: 'reportDesigner.next.step.button', default: 'Next')}" /></li>
	            </ul>
	        </div>
	        <div id="reportDesigner-step4" class="content scaffold-create" role="main">
	            <h1><g:message code="reportDesigner.step4.title" default="STEP 4"/></h1>
			   	<fieldset class="form">
					<dl class="feature-list">
						<label class="bold-title-label report-field-name column-field report-column-name-field"><g:message code="reportDesigner.step2.column.name.label" default="Column"/></label><label class="bold-title-label report-field-title selected-field"><g:message code="reportDesigner.step4.column.width.label" default="Width"/></label>
						<g:each in="${reportDesignerColumnsCommand.columnList}" var="item1" status="i">
							<g:if test="${item1?.selected}">
								<dd>
									<span class="">
										<label class="report-column-name-field pf-line"><g:message code="${item1.getLabelName()}"/></label>
										<g:field type="number" name="reportDesignerColumnsCommand.columnList[$i].columnWidth" value="${item1?.columnWidth}" class="pf-name-field pf-line"/>
										<br/>
									</span>
								</dd>
							</g:if>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].selected" value="${(item1?.selected)}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].filterBy" value="${(item1?.filterBy)}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].sortBy" value="${(item1?.sortBy)}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].groupBy" value="${(item1?.groupBy)}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].sortOrder" value="${(true == item1?.sortBy ? item1?.sortOrder : null)}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].dataType" value="${item1?.dataType}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].groupOrder" value="${(true == item1?.groupBy ? item1?.groupOrder : null)}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].tableName" value="${item1?.tableName}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].propertyName" value="${item1?.propertyName}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].foreignTableName" value="${item1?.foreignTableName}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].foreignTableDisplay" value="${item1?.foreignTableDisplay}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].filterCriteria" value="${item1?.filterCriteria}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].primaryFilterValue" value="${item1?.primaryFilterValue}"/>
							<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].secondaryFilterValue" value="${item1?.secondaryFilterValue}"/>
						</g:each>
					</dl>
					<g:hiddenField name="reportDesigner.reportType" value="${reportDesigner?.reportType}"/>
					<g:hiddenField name="reportDesigner.name" value="${reportDesigner?.name}"/>
					<g:hiddenField name="reportDesigner.description" value="${reportDesigner?.description}"/>
					<g:hiddenField name="reportDesigner.hasFilter" value="${reportDesigner?.hasFilter}"/>
					<g:hiddenField name="reportDesigner.hasGroup" value="${reportDesigner?.hasGroup}"/>
					<g:hiddenField name="reportDesigner.hasSort" value="${reportDesigner?.hasSort}"/>
					<g:hiddenField name="reportDesigner.reportFolder" value="${item1?.reportFolder?.id}"/>
					<g:hiddenField name="reportDesigner.crmUser" value="${session?.user?.id}"/>
					<g:hiddenField name="cameFromStep" value="4"/>
				</fieldset>
						
		  	    <fieldset class="buttons">
		  			<g:actionSubmit action="step3" class="return" value="${message(code: 'reportDesigner.previous.step.button', default: 'Previous')}" />
	                <g:actionSubmit action="step5" class="advance" value="${message(code: 'reportDesigner.next.step.button', default: 'Next')}"/>
		  		</fieldset>
		  	</div>
        </g:form>
    </body>
</html>