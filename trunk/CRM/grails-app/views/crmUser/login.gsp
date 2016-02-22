<html>
	 <head>
		 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		 <meta name="layout" content="main" />
		 <title>User Login</title>
	 </head>
	 <body>
		 <div class="body">
			 <g:form action="doLogin" method="post">
				 <div class="dialog">
					 <p>Enter your login details below:</p>
					 <table class="userForm">
						 <tr class='prop'>
							 <td valign='top' style='text-align:left;' width='20%'>
							 	<label for='login'>Login:</label>
							 </td>
							 <td valign='top' style='text-align:left;' width='80%'>
							 	<input id="login" type='text' name='login' value='${user?.login}' />
							 </td>
						 </tr>
						 <tr class='prop'>
							 <td valign='top' style='text-align:left;' width='20%'>
							 	<label for='pass'>Password:</label>
							 </td>
							 <td valign='top' style='text-align:left;' width='80%'>
							 	<input id="pass" type='password' name='pass' value='${user?.pass}' />
							 </td>
						 </tr>
					 </table>
				</div>
				<g:hiddenField name="redirectToUri" value="${params.uri}"/>
				<div class="buttons">
					 <span class="formButton">
					 	<input type="submit" value="Login"></input>
					 </span>
				 </div>
			 </g:form>
		 </div>
	 </body>
</html>
