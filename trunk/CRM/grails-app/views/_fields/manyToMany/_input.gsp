<!-- esta es la linia a agregar en el gsp _form para llamar a este input que está a continuación pero el problema es que en el edit no desselecciona opciones, solo permite agregar pero no quitar-->
<!-- <f:field bean="task" property="notificationMethods" widget-propId="${task?.notificationMethods*.id}" input-className="crm.NotificationMethod"/>  -->

ManyToMany input component is deprecated and not correctly implemented. If you see this message, please contact your administrator.


<!--<g:select name="${property}" 
from="${grailsApplication.getDomainClass(className).clazz.list()}"  
value="${propIds}"
optionKey="id" 
optionValue="${displayedProp? displayedProp : 'name'}"
multiple="true"
class="many-to-many-field"/>
-->	