<g:form class="search-form" method="post" action="searchResults" controller="search" style="display: none;">
		<g:textField id="search-field" name="searchKeyWords"/>
		<g:actionSubmit class="search" value="${message(code: 'default.button.search.label', default: 'OK')}" action="searchResults" controller="search"/>
</g:form>