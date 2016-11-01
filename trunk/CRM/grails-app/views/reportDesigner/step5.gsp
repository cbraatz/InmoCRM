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
	        <g:each in="${reportDesignerColumnsCommand.columnList}" var="item1" status="i">
	            <g:hasErrors bean="${item1}">
	            <ul class="errors" role="alert">
	                <g:eachError bean="${item1}" var="error">
	                	<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
	                </g:eachError>
	            </ul>
	            </g:hasErrors>
            </g:each>
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
	         	
				<g:each in="${reportDesignerColumnsCommand.columnList}" var="item1" status="i">
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].selected" value="${item1?.selected}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].filterBy" value="${item1?.filterBy}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].sortBy" value="${item1?.sortBy}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].groupBy" value="${item1?.groupBy}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].sortPosition" value="${(true == item1?.sortBy ? item1?.sortPosition : null)}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].dataType" value="${item1?.dataType}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].groupPosition" value="${(true == item1?.groupBy ? item1?.groupPosition : null)}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].sortOrder" value="${(true == item1?.sortBy ? item1?.sortOrder : null)}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].tableName" value="${item1?.tableName}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].displayName" value="${item1?.displayName}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].propertyName" value="${item1?.propertyName}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].foreignTableName" value="${item1?.foreignTableName}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].foreignTableDisplay" value="${item1?.foreignTableDisplay}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].parentTableName" value="${item1?.parentTableName}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].columnWidth" value="${item1?.columnWidth}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].filterCriteria" value="${item1?.filterCriteria}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].primaryFilterValue" value="${item1?.primaryFilterValue}"/>
					<g:hiddenField name="reportDesignerColumnsCommand.columnList[$i].secondaryFilterValue" value="${item1?.secondaryFilterValue}"/>
				</g:each>
				
                <fieldset class="buttons">
                	<g:actionSubmit action="step4" class="return" value="${message(code: 'reportDesigner.previous.step.button', default: 'Previous')}" />
                    <g:actionSubmit action="save" class="save" value="${message(code: 'default.button.save.label', default: 'Save Report')}"/>
                </fieldset>
        	</div>
        </g:form>
    </body>
</html>