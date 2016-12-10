<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'contextPermissionCategory.label', default: 'ContextPermissionCategory')}" />
        <title><g:message code="contextPermissionCategory.define.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#define-contextPermissionCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="define-contextPermissionCategory" class="content scaffold-create" role="main">
            <h1><g:message code="contextPermissionCategory.define.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.contextPermissionCategory}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.contextPermissionCategory}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="saveDefine">
            
				<fieldset class="form">
					<g:hiddenField name="contextPermissionCategory.id" value="${contextPermissionCategory?.id}"/>
					<dl class="feature-list">
						<g:each in="${contextCrmActionsByCategoryCommand.actionsByController}" var="asbc" status="i">
							<dd>
								<h4 style="margin-top:20px;"><g:message code="ENUM.CrmController.${asbc?.getController().name()}"/></h4>
								<g:hiddenField name="contextCrmActionsByCategoryCommand.actionsByController[$i].controller" value="${asbc?.getController().name()}"/>
								
								<g:each in="${asbc.getActions()}" var="act" status="i2">
									<div class="category-action">
										<label class=""><g:message code="ENUM.CrmAction.${act.getAction().name()}"/></label>
										<g:checkBox name="contextCrmActionsByCategoryCommand.actionsByController[$i].actions[$i2].selected" value="${act?.selected}" class="pf-name-field pf-line selectCheckBox"/>
										<g:hiddenField name="contextCrmActionsByCategoryCommand.actionsByController[$i].actions[$i2].action" value="${act?.action?.name()}"/>
										<g:hiddenField name="contextCrmActionsByCategoryCommand.actionsByController[$i].actions[$i2].oldSelected" value="${act?.oldSelected}"/>
										<br/>
									</div>
								</g:each>
							</dd>
						</g:each>
					</dl>
				</fieldset>
                <g:if test="${!(contextPermissionCategory.isAll==true || contextPermissionCategory.isNone==true)}">
                <fieldset class="buttons">
                    <g:submitButton name="edit" class="save" resource="${this.contextPermissionCategory}" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
                </g:if>
            </g:form>
        </div>
    </body>
</html>
