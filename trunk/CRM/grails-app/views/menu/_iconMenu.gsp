
	<ul id="nav">
		<li class="imenu-item"><div id="basic-icon"></div></li>
		<li class="imenu-item"><div id="people-icon"></div></li>
		<li class="imenu-item"><div id="administrative-icon"></div></li>
		<li class="imenu-item"><div id="sales-icon"></div></li>
		<li class="imenu-item"><div id="admin-icon"></div></li>
	</ul>

<div class="nav-menu" id="basic-menu" style="display: none;">
	<div class="nav-header"><g:message code="menu.basic.title" default="Basic"/></div>
	<div id="nav-menu-content">
		<div class="nav-body"><g:render template="/menu/basic"/></div>
	</div>
</div>
<div class="nav-menu" id="people-menu" style="display: none;">
	<div class="nav-header"><g:message code="menu.people.title" default="People"/></div>
	<div id="nav-menu-content">
		<div class="nav-body"><g:render template="/menu/people"/></div>
	</div>
</div>
<div class="nav-menu" id="administrative-menu" style="display: none;">
	<div class="nav-header"><g:message code="menu.administrative.title" default="Administrative"/></div>
	<div id="nav-menu-content">
		<div class="nav-body"><g:render template="/menu/administrative"/></div>
	</div>
</div>
<div class="nav-menu" id="sales-menu" style="display: none;">
	<div class="nav-header"><g:message code="menu.sales.title" default="Sales"/></div>
	<div id="nav-menu-content">
		<div class="nav-body"><g:render template="/menu/sales"/></div>
	</div>
</div>
<div class="nav-menu" id="admin-menu" style="display: none;">
	<div class="nav-header"><g:message code="menu.admin.title" default="Admin"/></div>
	<div id="nav-menu-content">
		<div class="nav-body"><g:render template="/menu/admin"/></div>
	</div>
</div>
<script type="text/javascript">
	//$(document).ready(function() {
		//basic
		$("#basic-icon").click(function() {
			hideAllMenues();
			$("#basic-menu").show();
			setNavigationMenuOffset($("#basic-icon"),$("#basic-menu"));
		});

		//people
		$("#people-icon").click(function() {
			hideAllMenues();
			$("#people-menu").show();
			setNavigationMenuOffset($("#people-icon"),$("#people-menu"));
		});

		//administrative
		$("#administrative-icon").click(function() {
			hideAllMenues();
			$("#administrative-menu").show();
			setNavigationMenuOffset($("#administrative-icon"),$("#administrative-menu"));
		});

		//sales
		$("#sales-icon").click(function() {
			hideAllMenues();
			$("#sales-menu").show();
			setNavigationMenuOffset($("#sales-icon"),$("#sales-menu"));
		});

		//admin
		$("#admin-icon").click(function() {
			hideAllMenues();
			$("#admin-menu").show();
			setNavigationMenuOffset($("#admin-icon"),$("#admin-menu"));
		});

		$(".nav-menu").mouseleave(function() {
			hideAllMenues();
		});

		
		/*$( window ).scroll(function() {
			var menu=$('#icon-menu');
			menu.css({
				'top': 50%;
		    	'left': 50%;
		    });
		});*/
		/*$("*").click(function() {
			   alert($(this).attr("class")+'...'+$(this).attr("id"));
			});
			alert($("#basic-menu").is(':visible'));
		 if(mc!="imenu-item"){
			 hideAllMenues();
		 }*/
	//});
	
	function setNavigationMenuOffset(icon, menu) {
		menu.offset({top: icon.position().top+17});
	};
	function hideAllMenues() {
		$(".nav-menu").hide();
	};
	
</script>