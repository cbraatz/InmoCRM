<!doctype html>
<html>
    <head>
        <title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
        <g:if env="development">
            <g:if test="${Throwable.isInstance(exception)}">
                <g:renderException exception="${exception}" />
            </g:if>
            <g:elseif test="${request.getAttribute('javax.servlet.error.exception')}">
                <g:renderException exception="${request.getAttribute('javax.servlet.error.exception')}" />
            </g:elseif>
            <g:else>
                <ul class="errors">
                	<li><g:message code="error.has.occurred.label"/></li>
                	<g:if test="${exception}">
		               <li><g:message code="default.exception.label"/>: ${exception}</li>
		            </g:if>
                    <g:if test="${message}">
		               <li><g:message code="default.message.label"/>: ${message}</li>
		            </g:if>
                    <g:if test="${path}">
		               <li><g:message code="default.path.label"/>: ${path}</li>
		            </g:if>
                </ul>
            </g:else>
        </g:if>
        <g:else>
            <ul class="errors">
                <li>An error has occurred</li>
            </ul>
        </g:else>
    </body>
</html>
