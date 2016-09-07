<fieldset class="form">
	<f:field bean="commissionByConcession" property="percentage"/>
	<f:field bean="commissionByConcession" property="amount"/>
	<g:hiddenField name="concession" value="${commissionByConcession?.concession?.id}"/>
	<g:hiddenField name="commissionByConcession" value="${partner?.id}"/>
 </fieldset> 
		        