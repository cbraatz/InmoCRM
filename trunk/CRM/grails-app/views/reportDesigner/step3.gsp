<%@ page import="crm.enums.FilterCriteria" %>
<%@ page import="crm.enums.SortOrder" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="reportDesigner.step3.title"/></title>
    </head>
    <body>
        <a href="#create-buildingFeature" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <g:form>
	        <div class="nav" role="navigation">
	            <ul>
	                <li><g:actionSubmit action="step2" class="return" value="${message(code: 'reportDesigner.previous.step.button', default: 'Previous')}" /></li>
	                <li><g:actionSubmit action="step4" class="advance" value="${message(code: 'reportDesigner.next.step.button', default: 'Next')}" /></li>
	            </ul>
	        </div>
	        <g:hasErrors bean="${reportDesigner}">
	            <ul class="errors" role="alert">
	                <g:eachError bean="${reportDesigner}" var="error">
	                	<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
	                </g:eachError>
	            </ul>
	        </g:hasErrors>
	        <g:each in="${reportDesignerColumnsCommand.columnList}" var="item1" status="i">
	            <g:hasErrors bean="${item1}">
	            <ul class="errors" role="alert">
	                <g:eachError bean="${item1}" var="error">
	                	<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
	                </g:eachError>
	            </ul>
	            </g:hasErrors>
            </g:each>
	        <div id="reportDesigner-step3" class="content scaffold-create" role="main">
	        	<h1><g:message code="reportDesigner.step3.title" default="STEP 3"/></h1> 
	        	<g:if test="${reportDesigner.hasFilter.booleanValue() == true}">
		        	<fieldset class="form">
		            	<h3><g:message code="reportDesigner.step3.filter.title"/></h3>
					    <dl class="feature-list">
							<label class="bold-title-label report-field-name column-field report-column-name-field"><g:message code="reportDesigner.step2.column.name.label" default="Column"/></label><label class="bold-title-label report-column-med-field report-field-title selected-field"><g:message code="reportDesigner.step3.filter.criteria.label" default="Selected"/></label><label class="bold-title-label report-column-med-field report-field-title filterby-label"><g:message code="reportDesigner.step3.filter.value.label" default="Filter By"/></label>
							<g:each in="${reportDesignerColumnsCommand.columnList}" var="item1" status="i">
								<g:if test="${item1.selected.booleanValue() == true && item1.filterBy.booleanValue() == true}">
								 	<dd>
										<span class="">
											<label class="report-column-name-field pf-line"><g:message code="${item1.getLabelName()}"/></label>
											<g:render template="/reportDesigner/filterCriteria" model="['colid':i,'dType':item1.dataType, 'fCriteria':item1.filterCriteria, 'primValue':item1.primaryFilterValue, 'secValue':item1.secondaryFilterValue, 'domType':'crm.'+item1.foreignTableName]"/>
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
		            	<h3><g:message code="reportDesigner.step3.group.title"/></h3>
					    <dl class="feature-list">
							<label class="bold-title-label report-field-name column-field report-column-name-field"><g:message code="reportDesigner.step2.column.name.label" default="Column"/></label><label class="bold-title-label selected-field"><g:message code="reportDesigner.step3.column.group.order.label" default="Selected"/></label>
							<g:each in="${reportDesignerColumnsCommand.columnList}" var="item1" status="i">
								<g:if test="${item1.selected.booleanValue() == true && item1.groupBy.booleanValue() == true}">
								 	<dd>
										<span class="">
											<label class="report-column-name-field pf-line"><g:message code="${item1.getLabelName()}"/></label>
											<g:field type="number" name="reportDesignerColumnsCommand.columnList[$i].groupPosition" min="1" required="" value="${(true == item1?.groupBy ? item1?.groupPosition : null)}"/>
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
		            	<h3><g:message code="reportDesigner.step3.sort.title"/></h3>
					    <dl class="feature-list">
							<label class="bold-title-label report-field-name column-field report-column-name-field"><g:message code="reportDesigner.step2.column.name.label" default="Column"/></label><label class="bold-title-label selected-field"><g:message code="reportDesigner.step3.column.sort.order.label" default="Selected"/></label>
							<g:each in="${reportDesignerColumnsCommand.columnList}" var="item1" status="i">
								<g:if test="${item1.selected.booleanValue() == true && item1.sortBy.booleanValue() == true}">
								 	<dd>
										<span class="">
											<label class="report-column-name-field pf-line"><g:message code="${item1.getLabelName()}"/></label>
											<g:field type="number" name="reportDesignerColumnsCommand.columnList[$i].sortPosition" min="1" required="" value="${(true == item1?.sortBy ? item1?.sortPosition : null)}"/>
											<g:select id="SortPosition-filter-select-[$i]" name="reportDesignerColumnsCommand.columnList[$i].sortOrder" value="${(null != item1.sortOrder? SortOrder.valueOf(item1.sortOrder) : null)}" from="${SortOrder.getAllSortOrders()}" valueMessagePrefix="ENUM.SortOrder"/>
											<br/>
										</span>
									</dd>
								</g:if>
							</g:each>
						</dl>
					</fieldset>
				</g:if>
				<g:each in="${reportDesignerColumnsCommand.columnList}" var="item1" status="i">
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].selected" value="${item1?.selected}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].filterBy" value="${item1?.filterBy}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].sortBy" value="${item1?.sortBy}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].groupBy" value="${item1?.groupBy}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].dataType" value="${item1?.dataType}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].tableName" value="${item1?.tableName}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].propertyName" value="${item1?.propertyName}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].displayName" value="${item1?.displayName}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].foreignTableName" value="${item1?.foreignTableName}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].foreignTableDisplay" value="${item1?.foreignTableDisplay}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].parentTableName" value="${item1?.parentTableName}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].columnWidth" value="${item1?.columnWidth}"/>
				</g:each>
				<g:hiddenField name="reportDesigner.reportType" value="${reportDesigner?.reportType}"/>
				<g:hiddenField name="reportDesigner.name" value="${reportDesigner?.name}"/>
				<g:hiddenField name="reportDesigner.description" value="${reportDesigner?.description}"/>
				<g:hiddenField name="reportDesigner.hasFilter" value="${reportDesigner?.hasFilter}"/>
				<g:hiddenField name="reportDesigner.hasGroup" value="${reportDesigner?.hasGroup}"/>
				<g:hiddenField name="reportDesigner.hasSort" value="${reportDesigner?.hasSort}"/>
				<g:hiddenField name="reportDesigner.reportFolder" value="${item1?.reportFolder?.id}"/>
				<g:hiddenField name="reportDesigner.crmUser" value="${session?.user?.id}"/>
                <fieldset class="buttons">
                	<g:actionSubmit action="step2" class="return" value="${message(code: 'reportDesigner.previous.step.button', default: 'Previous')}" />
                    <g:actionSubmit action="step4" class="advance" value="${message(code: 'reportDesigner.next.step.button', default: 'Next')}"/>
                </fieldset>
        	</div>
        </g:form>
    </body>
</html>