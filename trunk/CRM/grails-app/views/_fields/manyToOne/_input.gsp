<g:select name="${property}.id" from="${type.list()}" optionKey="id" optionValue="${displayedProp? displayedProp : 'name'}" value="${propId? type.get(propId).id : ''}" class="form-control one-to-many"/>
<g:link class="add" controller="${propName? propName : property}" action="create">MI</g:link>
