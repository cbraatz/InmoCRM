<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'commissionByProperty.label', default: 'Commission by Property')}" />
        <title><g:message code="default.create.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-commissionByproperty" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <g:if test="${commissionByProperty}">
                	<li><g:link class="return" action="show" controller="managedProperty" id="${commissionByProperty?.managedProperty?.id}"><g:message code="managedProperty.label" default="Managed Property" /></g:link></li>
                </g:if>
            </ul>
        </div>
        <div id="createCommissions" class="content scaffold-create" role="main">
			<h1><g:message code="commissionByProperty.add.commission.by.managedProperty.title"/></h1><br>
			<g:if test="${flash.message}">
	            <div class="message" role="status">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${this.commissionByProperty}">
	            <ul class="errors" role="alert">
	                <g:eachError bean="${this.commissionByProperty}" var="error">
	                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
	                </g:eachError>
	            </ul>
            </g:hasErrors>
			<g:form>
			
				<g:render template="addForm"/>
				
		        <div class="buttons">
		            <span class="button"><g:actionSubmit class="save" value="${message(code: 'default.button.save.label', default: 'Save')}" action="save"/></span>
		        </div> 
	        </g:form>
	        <h1><g:message code="commissionByProperty.commission.list.title"/></h1>
			<g:if test="${commissionByProperty.managedProperty.commissionsByProperty.size() > 0}">
				<div class="list">
	                <table>
	                    <thead>
	                        <tr>
	                            <g:sortableColumn property="partner" title="${message(code: 'commissionByProperty.partner.label', default: 'Partner')}"/>
	                            <g:sortableColumn property="percentage" title="${message(code: 'commissionByProperty.percentage.label', default: 'Percentage')}"/>
	                            <g:sortableColumn property="amount" title="${message(code: 'commissionByProperty.amount.label', default: 'Amount')}"/>
	                            <g:sortableColumn property="actions" title="${message(code: 'default.actions.label', default: 'Actions')}"/>
	                       </tr>
	                    </thead>
	                    <tbody>
	                    <g:each in="${commissionByProperty.managedProperty.commissionsByProperty}" status="i" var="item">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                            <td>${item.partner.name}</td>
	                            <td>${item.percentage}</td>
	                            <td>${item.amount}</td>
	                            <td>
	                            	<g:link action="edit" id="${item.id}"><g:message code="default.button.edit.label"/></g:link>
	                            	<g:link action="delete" id="${item.id}" onclick="return confirm('Are you sure?');"><g:message code="default.button.delete.label"/></g:link>
	                            </td>
	                        </tr>
	                    </g:each>
	                    </tbody>
	                </table>
	            </div>
            </g:if>
            <g:else>
			     <label id="empty-list-results"><g:message code="commissionByProperty.empty.list.message"/></label>
			</g:else>
        </div>
    </body>
</html>
