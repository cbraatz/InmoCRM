<%@ page import="crm.CrmUri" %>
<html>
	 <head>
		 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		 <meta name="layout" content="main" />
		 <title>User Login</title>
	 </head>
	 <body>
		 <div id="login-body">
		 	<div id="login-box">
				 <g:form action="doLogin" method="post">
					 <div class="login-dialog">
					 	 <div id="login-title">
						 	<p id="login-title"><g:message code="login.enter.credentials.title"/></p>
						 </div>
						 <table class="loginForm">
							 <tr class='prop'>
								 <td valign='top' style='text-align:left;' width='20%'>
								 	<label id="login-name-label"for='login'><g:message code="login.name.label"/></label>
								 </td>
								 <td valign='top' style='text-align:left;' width='80%'>
								 	<input id="login-name-txt" type='text' name='login' value="" />
								 </td>
							 </tr>
							 
							 <tr class='prop'>
								 <td valign='top' style='text-align:left;' width='20%'>
								 	<label  id="login-pass-label" for='pass'><g:message code="login.pass.label"/></label>
								 </td>
								 <td valign='top' style='text-align:left;' width='80%'>
								 	<input id="login-pass-txt" type='password' name='pass' value="" />
								 </td>
							 </tr>
						 </table>
					</div>
					<g:hiddenField name="crmUri.action" value="${crmUri?.action}"/>
					<g:hiddenField name="crmUri.controller" value="${crmUri?.controller}"/>
					<g:hiddenField name="crmUri.id" value="${crmUri?.id}"/>
					<div class="buttons" id="login-buttons">
						 <span class="formButton">
						 	<input type="submit" value="${message(code: 'login.label', default: 'Log in')}" id="confirm-login-button"></input>
						 </span>
					 </div>
				 </g:form>
			 </div>
		 </div>
	 </body>
</html>
