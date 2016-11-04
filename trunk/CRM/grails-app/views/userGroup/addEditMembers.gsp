<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userGroup.label', default: 'Commission by Concession')}" />
        <title><g:message code="default.create.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-managedProperty" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <g:if test="${concession}">
                	<li><g:link class="return" action="show" controller="concession" id="${concession?.id}"><g:message code="concession.label" default="Concession" /></g:link></li>
                </g:if>
            </ul>
        </div>
        <div id="createCommissions" class="content scaffold-create" role="main">
			<h1><g:message code="userGroup.addEdit.member.title"/></h1><br>
			<g:if test="${flash.message}">
	            <div class="message" role="status">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${this.userGroup}">
	            <ul class="errors" role="alert">
	                <g:eachError bean="${this.userGroup}" var="error">
	                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
	                </g:eachError>
	            </ul>
            </g:hasErrors>
			<g:form>
			
				 <fieldset class="form">
					<f:field bean="someObject" property="crmUser"/><!-- uso cualquier domain object que tenga CrmUser, en este caso reportFolder -->
					<g:hiddenField name="userGroup.id" value="${userGroup?.id}"/>
				 </fieldset> 
				
		        <div class="buttons">
		            <span class="button"><g:actionSubmit class="save" value="${message(code: 'default.button.save.label', default: 'Save')}" action="saveMember"/></span>
		        </div> 
	        </g:form>
	        <h1><g:message code="userGroup.crmUsers.label"/></h1>
			<g:if test="${userGroup.crmUsers.size() > 0}">
				<div class="list">
	                <table>
	                    <thead>
	                        <tr>
	                            <g:sortableColumn property="crmUser" title="${message(code: 'crmUser.label', default: 'User')}"/>
	                            <g:sortableColumn property="emailAddress" title="${message(code: 'crmUser.emailAddress.label', default: 'Email')}"/>
	                            <g:sortableColumn property="actions" title="${message(code: 'default.actions.label', default: 'Actions')}"/>
	                       </tr>
	                    </thead>
	                    <tbody>
	                    <g:each in="${userGroup.crmUsers}" status="i" var="item">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                            <td>${item.name}</td>
	                            <td>${item.emailAddress}</td>
	                            <td>
	                            	<g:link action="deleteMember" controller="userGroup" id="${userGroup.id}" params="[uid:"${item.id}"]" onclick="return confirm('Are you sure?');"><g:message code="default.button.delete.label"/></g:link>
	                            </td>
	                        </tr>
	                    </g:each>
	                    </tbody>
	                </table>
	            </div>
            </g:if>
            <g:else>
			     <label id="empty-list-results"><g:message code="userGroup.members.empty.list.message"/></label>
			</g:else>
			
        </div>
    </body>
</html>