<fieldset class="form">
	  <g:each in="${notificationMethodsCommand.items}" var="item1" status="i">
			<g:checkBox id="${item1?.item?.name}" name="items[$i].selected" value="${(item1?.selected)}"/>
			<span for="${item1?.item?.name}" class="">${item1?.item?.name}</span>
			<br/>
	  </g:each>
</fieldset>