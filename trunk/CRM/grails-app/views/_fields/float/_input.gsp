<g:set var="prop" value="${bean.getProperty(property)}"/>
<g:field type="text" name="${property}" value="${prop? prop.toString().replace('.',','): ''}" pattern="[0-9]*,?[0-9]*"/>
