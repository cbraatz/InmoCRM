<%@ page import="crm.enums.FilterCriteria" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="reportDesigner.step2.title"/></title>
    </head>
    <body>
        <a href="#create-buildingFeature" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <g:form action="step2">
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
											<g:select name="reportDesigner.reportDesignerColumns[$i].filterCriteria" value="${(null != item1.filterCriteria? FilterCriteria.valueOf(item1.filterCriteria) : null)}" from="${FilterCriteria.getAllFilterCriteriaList(item1.dataType)}" valueMessagePrefix="ENUM.FilterCriteria" />
											<g:textField class="filter-value1" name="reportDesigner.reportDesignerColumns[$i].primaryFilterValue" value="${item1.primaryFilterValue}"/>
											<g:textField class="filter-value2" name="reportDesigner.reportDesignerColumns[$i].secondaryFilterValue" value="${item1.secondaryFilterValue}"/>
											<br/>								
										</span>
									</dd>
								</g:if>
							</g:each>
						</dl>
					</fieldset>
				</g:if>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.save.label', default: 'Save')}" />
                </fieldset>
        	</div>
        </g:form>
    </body>
</html>