 <%@ page import="crm.Income" %>
 <%@ page import="org.grails.core.DefaultGrailsDomainClass" %>
<g:set var="xx" value="${new DefaultGrailsDomainClass(bean.class).getConstrainedProperties().get(property)}"/>
<g:set var="prop" value="${bean.getProperty(property)}"/>
<g:field type="text" name="${property}" value="${prop? prop.toString().replace('.',','): ''}" pattern="[0-9]*,?[0-9]*"/>
<div>${xx}</div>
