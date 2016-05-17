<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="search.page.title"/></title>
    </head>
    <body>
        <a href="#show-managedProperty" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="search-results" class="content scaffold-show" role="main">
			<g:if test="${resultList.getSize() > 0}">
				<h1><g:message code="search.results.title"/></h1><br>
				<g:render template="searchResultsTable" model="['list':resultList.getSearchResultItems()]"/>
            </g:if>
            <g:else>
			     <label id="empty-search-results"><g:message code="search.empty.results.list.message" args="[searchKeyWords]"/></label>
			</g:else>
        </div>
    </body>
</html>