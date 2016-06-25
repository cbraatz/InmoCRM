<%@ page import="crm.enums.FilterCriteria" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="reportDesigner.step2.title"/></title>
    </head>
    <body>
        <a href="#create-buildingFeature" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <g:form action="step4">
	        <div class="nav" role="navigation">
	            <ul>
	                <li><g:actionSubmit action="returnToStep1" class="return" value="${message(code: 'reportDesigner.return.to.step1', default: 'Return to Step 1')}" /></li>
	                <li><g:actionSubmit action="advanceToStep3" class="advance" value="${message(code: 'reportDesigner.advance.to.step3', default: 'Advance to Step 3')}" /></li>
	            </ul>
	        </div>
	        <div id="reportDesigner-step3" class="content scaffold-create" role="main">
	        	<g:if test="${reportDesigner.hasFilter.booleanValue() == true}">
		        	<fieldset class="form">
		            	<h1><g:message code="reportDesigner.step3.filter.title"/></h1>
					    <dl class="feature-list">
							<label class="bold-title-label report-field-name column-field report-column-name-field"><g:message code="reportDesigner.step2.column.name.label" default="Column"/></label><label class="bold-title-label report-column-med-field report-field-title selected-field"><g:message code="reportDesigner.step3.filter.criteria.label" default="Selected"/></label><label class="bold-title-label report-column-med-field report-field-title filterby-label"><g:message code="reportDesigner.step3.filter.value.label" default="Filter By"/></label>
							<g:each in="${reportDesigner.reportDesignerColumns}" var="item1" status="i">
								<g:if test="${item1.selected.booleanValue() == true && item1.filterBy.booleanValue() == true}">
								 	<dd>
										<span class="">
											<label class="report-column-name-field pf-line"><g:message code="${item1.getLabelName()}"/></label>
											<g:render template="/reportDesigner/filterCriteria" model="['colid':i,'dType':item1.dataType, 'fCriteria':item1.filterCriteria, 'primValue':item1.primaryFilterValue, 'secValue':item1.secondaryFilterValue]"/>
											<br/>
										</span>
									</dd>
								</g:if>
							</g:each>
						</dl>
					</fieldset>
				</g:if>
				<g:if test="${reportDesigner.hasGroup.booleanValue() == true}">
		        	<fieldset class="form">
		            	<h1><g:message code="reportDesigner.step3.group.title"/></h1>
					    <dl class="feature-list">
							<label class="bold-title-label report-field-name column-field report-column-name-field"><g:message code="reportDesigner.step2.column.name.label" default="Column"/></label><label class="bold-title-label report-field-title selected-field"><g:message code="reportDesigner.step3.filter.criteria.label" default="Selected"/></label><label class="bold-title-label report-field-title filterby-label"><g:message code="reportDesigner.step2.column.filter.by.label" default="Filter By"/></label><label class="bold-title-label report-field-title sortby-label"><g:message code="reportDesigner.step2.column.sort.by.label" default="Sort By"/></label><label class="bold-title-label report-field-title groupby-label"><g:message code="reportDesigner.step2.column.group.by.label" default="Group By"/></label>
							<g:each in="${reportDesigner.reportDesignerColumns}" var="item1" status="i">
								<g:if test="${item1.selected.booleanValue() == true && item1.groupBy.booleanValue() == true}">
								 	<dd>
										<span class="">
											<label class="report-column-name-field pf-line"><g:message code="${item1.getLabelName()}"/></label>
											<br/>								
										</span>
									</dd>
								</g:if>
							</g:each>
						</dl>
					</fieldset>
				</g:if>
				<g:if test="${reportDesigner.hasSort.booleanValue() == true}">
		        	<fieldset class="form">
		            	<h1><g:message code="reportDesigner.step3.sort.title"/></h1>
					    <dl class="feature-list">
							<label class="bold-title-label report-field-name column-field report-column-name-field"><g:message code="reportDesigner.step2.column.name.label" default="Column"/></label><label class="bold-title-label report-field-title selected-field"><g:message code="reportDesigner.step3.filter.criteria.label" default="Selected"/></label><label class="bold-title-label report-field-title filterby-label"><g:message code="reportDesigner.step2.column.filter.by.label" default="Filter By"/></label><label class="bold-title-label report-field-title sortby-label"><g:message code="reportDesigner.step2.column.sort.by.label" default="Sort By"/></label><label class="bold-title-label report-field-title groupby-label"><g:message code="reportDesigner.step2.column.group.by.label" default="Group By"/></label>
							<g:each in="${reportDesigner.reportDesignerColumns}" var="item1" status="i">
								<g:if test="${item1.selected.booleanValue() == true && item1.sortBy.booleanValue() == true}">
								 	<dd>
										<span class="">
											<label class="report-column-name-field pf-line"><g:message code="${item1.getLabelName()}"/></label>
											<br/>								
										</span>
									</dd>
								</g:if>
							</g:each>
						</dl>
					</fieldset>
				</g:if>
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
				</g:each>
				<g:hiddenField name="reportDesigner.reportType" value="${reportDesigner?.reportType}"/>
				<g:hiddenField name="reportDesigner.hasFilter" value="${reportDesigner?.hasFilter}"/>
				<g:hiddenField name="reportDesigner.hasGroup" value="${reportDesigner?.hasGroup}"/>
				<g:hiddenField name="reportDesigner.hasSort" value="${reportDesigner?.hasSort}"/>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.save.label', default: 'Save')}" />
                </fieldset>
        	</div>
        </g:form>
    </body>
</html>