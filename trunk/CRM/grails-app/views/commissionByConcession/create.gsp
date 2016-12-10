<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'commissionByConcession.label', default: 'Commission by Concession')}" />
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
			<h1><g:message code="commissionByConcession.add.commission.by.concession.title"/></h1><br>
			<g:if test="${flash.message}">
	            <div class="message" role="status">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${this.commissionByConcession}">
	            <ul class="errors" role="alert">
	                <g:eachError bean="${this.commissionByConcession}" var="error">
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
	        <h1><g:message code="upload.image.list.title"/></h1>
			<g:if test="${concession.commissionsByConcession.size() > 0}">
				<div class="list">
	                <table>
	                    <thead>
	                        <tr>
	                            <g:sortableColumn property="partner" title="${message(code: 'commissionByConcession.partner.label', default: 'Partner')}"/>
	                            <g:sortableColumn property="percentage" title="${message(code: 'commissionByConcession.percentage.label', default: 'Percentage')}"/>
	                            <g:sortableColumn property="amount" title="${message(code: 'commissionByConcession.amount.label', default: 'Amount')}"/>
	                            <g:sortableColumn property="actions" title="${message(code: 'default.actions.label', default: 'Actions')}"/>
	                       </tr>
	                    </thead>
	                    <tbody>
	                    <g:each in="${concession.commissionsByConcession}" status="i" var="item">
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
			     <label id="empty-list-results"><g:message code="commissionByConcession.empty.list.message"/></label>
			</g:else>
        </div>
    </body>
</html>
