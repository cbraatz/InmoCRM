<%@ page import="crm.Neighborhood" %>     
<%@ page import="crm.City" %>   
<g:select name="${(doty.isEmpty() ? 'neighborhood' : 'address.neighborhood')}.id" from="${Neighborhood.findAllByCity(City.get(cid))}" optionKey="id" optionValue="name" value="${nid ? nid : null}" noSelection="${['null': message(code: 'default.select.one.label', default: 'Select One...')]}" class="neighborhood-selector one-to-many"  style="margin-top: 1em;"/>
<g:link class="add" controller="neighborhood" action="create">MI</g:link> 