<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="application.css"/>
		<asset:javascript src="application.js"/>
		<g:layoutHead/>
		
	</head>
	<body>
		<div id="grailsLogo" role="banner" style="height: 80px;">
			<span id="logo" style="float:left;">
				<asset:image src="grails_logo.png" alt="Grails"/>
			</span>
			<div id="banner-right">
				<div id="search-div"><g:render template="/search/quickSearch"/></div>
				<if test="${session.user}">
					<div id="login-info"><g:link class="user-login-link" action="show" controller="crmUser" params="['id':"${session?.login?.id}"]">${session?.user?.login}</g:link><g:link class="logout-link" action="doLogout" controller="login"><g:message code="logout.label" default="Log out" /></g:link></div>
				</if>
			</div>
		</div>
			<div id="icon-menu" style="display: none;"><g:render template="/menu/iconMenu"/></div>
		<div id="body-layout"><g:layoutBody/></div>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
	</body>
	
</html>
<script type="text/javascript">
	$(document).ready(function() {
		if(!($('#login-body').is(':visible') || $('#body-layout .error-details').is(':visible'))){
			$('.logout-link').show();
			$('#icon-menu').show();
			$('.search-form').show();
		}else{
			if($('#login-body').is(':visible')){
				var box=$('#login-box');
				box.css('margin-left',($('#body-layout').width()-box.width())/2+'px');//centra el panel de login
			}
		}
	});
</script>
