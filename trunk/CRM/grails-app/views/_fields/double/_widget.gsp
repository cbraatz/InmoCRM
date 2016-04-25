<g:set var="prop" value="${bean.getProperty(property)}"/>
<g:field type="text" name="${property}" value="${prop ? crm.Utils.formatDecimalsForInput(prop) : '0,0'}" pattern="[0-9]*,?[0-9]*"/>