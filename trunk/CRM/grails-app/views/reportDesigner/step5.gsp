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
	        <div id="reportDesigner-step5" class="content scaffold-create" role="main">
	         	<h1><g:message code="reportDesigner.step5.title" default="STEP 5"/></h1> 
	         	<f:field bean="reportDesigner" property="name"/>
	         	<f:field bean="reportDesigner" property="description">
					<g:textArea name="${property}" maxlength="200" value="${it.value}" rows="4" cols="50"/>
				</f:field>
	         	<f:field bean="reportDesigner" property="reportFolder" input-propId="${reportDesigner?.reportFolder?.id}"/>
	         	
	         	
	         	<!-- no se xq al name del hiddenField no le puedo poner en frente reportDesigner. como en los otros steps xq sino deja null el contenido de los demás fields-->
	         	<g:hiddenField name="reportType" value="${reportDesigner?.reportType}"/>
				<g:hiddenField name="hasFilter" value="${reportDesigner?.hasFilter}"/>
				<g:hiddenField name="hasGroup" value="${reportDesigner?.hasGroup}"/>
				<g:hiddenField name="hasSort" value="${reportDesigner?.hasSort}"/>
				<g:hiddenField name="crmUser" value="${session?.user?.id}"/>
	         	
				<g:each in="${reportDesigner.reportDesignerColumns}" var="item1" status="i">
					<g:hiddenField name="reportDesignerColumns[$i].selected" value="${item1?.selected}"/>
					<g:hiddenField name="reportDesignerColumns[$i].filterBy" value="${item1?.filterBy}"/>
					<g:hiddenField name="reportDesignerColumns[$i].sortBy" value="${item1?.sortBy}"/>
					<g:hiddenField name="reportDesignerColumns[$i].groupBy" value="${item1?.groupBy}"/>
					<g:hiddenField name="reportDesignerColumns[$i].sortOrder" value="${(true == item1?.sortBy ? item1?.sortOrder : null)}"/>
					<g:hiddenField name="reportDesignerColumns[$i].dataType" value="${item1?.dataType}"/>
					<g:hiddenField name="reportDesignerColumns[$i].groupOrder" value="${(true == item1?.groupBy ? item1?.groupOrder : null)}"/>
					<g:hiddenField name="reportDesignerColumns[$i].tableName" value="${item1?.tableName}"/>
					<g:hiddenField name="reportDesignerColumns[$i].propertyName" value="${item1?.propertyName}"/>
					<g:hiddenField name="reportDesignerColumns[$i].foreignTableName" value="${item1?.foreignTableName}"/>
					<g:hiddenField name="reportDesignerColumns[$i].foreignTableDisplay" value="${item1?.foreignTableDisplay}"/>
					<g:hiddenField name="reportDesignerColumns[$i].columnWidth" value="${item1?.columnWidth}"/>
					<g:hiddenField name="reportDesignerColumns[$i].filterCriteria" value="${item1?.filterCriteria}"/>
					<g:hiddenField name="reportDesignerColumns[$i].primaryFilterValue" value="${item1?.primaryFilterValue}"/>
					<g:hiddenField name="reportDesignerColumns[$i].secondaryFilterValue" value="${item1?.secondaryFilterValue}"/>
				</g:each>
				
                <fieldset class="buttons">
                	<g:actionSubmit action="step4" class="return" value="${message(code: 'reportDesigner.previous.step.button', default: 'Previous')}" />
                    <g:actionSubmit action="save" class="save" value="${message(code: 'default.button.save.label', default: 'Save Report')}"/>
                </fieldset>
        	</div>
        </g:form>
    </body>
</html>