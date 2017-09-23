<fieldset class="form">
	<f:field bean="commissionByConcession" property="partner" widget-propId="${commissionByConcession?.partner?.id}"/>
	<f:field bean="commissionByConcession" property="percentage"/>
	<g:hiddenField name="amount" value="${new Float("0")}"/>
	<g:hiddenField name="concession" value="${concession?.id}"/>
 </fieldset> 
		        