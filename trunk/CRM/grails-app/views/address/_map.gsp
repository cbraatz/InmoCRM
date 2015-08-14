<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<asset:stylesheet href="map.css"/>		 
<!-- Bootstrap core CSS -->
<!--<link href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.1.1/css/bootstrap.css" rel="stylesheet" media="screen"/> original remplazado por el de arreiba "map.css"-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7/html5shiv.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.3.0/respond.js"></script>
<![endif]-->


	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="mapmodals">
		    <div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title">Peggy Guggenheim Collection - Venice</h4>
					</div>
					<div class="modal-body">
				   		<div id="map-container"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="close" data-dismiss="modal">Close</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</div><!-- /container -->
		 
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.1.1/js/bootstrap.min.js"></script>
		<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
		<script>
			var var_map;
		    var var_location = new google.maps.LatLng(-27.051998971891024,-55.63157005639811);
			var saved_location;
		    function map_init() {		 	
		          var var_mapoptions = {
		              center: var_location,
		              zoom: 11,
		              mapTypeId: google.maps.MapTypeId.ROADMAP,
		              mapTypeControl: false,
		              panControl:false,
		              rotateControl:false,
		              streetViewControl: false,
		            };
		          var_map = new google.maps.Map(document.getElementById("map-container"), var_mapoptions);
		      
		          //var contentString = 
				//		'<div id="mapInfo">'+
				//		'<p><strong>Peggy Guggenheim Collection</strong><br/><br/>'+
				//		'Dorsoduro, 701-704<br/>' +
				//		'30123<br/>Venezia<br/>'+
				//		'P: (+39) 041 240 5411</p>'+
				//		'<a href="http://www.guggenheim.org/venice" target="_blank">Plan your visit</a>'+
				//		'</div>';
		 
		          //var var_infowindow = new google.maps.InfoWindow({ content: contentString });
		          
		          
		          ////////////agregado por mi//////////////
		          var addr="Fram Itapúa Paraguay"
		          var loc_lat=$('#latitude').val();
		      	  var loc_long=$('#longitude').val();
		      	  console.log(loc_lat+",, "+loc_long);
		      	  if(loc_lat && loc_long){
			      	  addr=loc_lat+", "+loc_long;
				   	  console.log(addr);
				   	  var_location = new google.maps.LatLng(loc_lat, loc_long);
			      }

			      //para obtener la dirección del nombre de la ciudad, departamento, país
		          //var geocoder = new google.maps.Geocoder();
		         // geocoder.geocode( { 'address': 'Obligado Itapúa Paraguay'}, function(results, status) {
				    //  if (status == google.maps.GeocoderStatus.OK) {
				   // 	 var_location=results[0].geometry.location;
				   // 	 console.log("1-"+var_location);
				   //   } else {
				   //      alert("Geocode was not successful for the following reason: " + status);
				   //   }
			     // });
				//////////////////

		
					
		          console.log("2-"+var_location);
		          var var_marker = new google.maps.Marker({
		            position: var_location,
		            map: var_map,
		            draggable: true,
		            animation: google.maps.Animation.DROP,//BOUNCE
		         //   title:"Click for information about the Guggenheim museum in Venice",
		                  //maxWidth: 200,
		                  //maxHeight: 200
		          });
		          google.maps.event.addListener(var_marker, 'dragend', function() {
		        	  	$('#latitude').val(var_marker.getPosition().lat());
		        	  	$('#longitude').val(var_marker.getPosition().lng());
		          });
		      }
		 
		      google.maps.event.addDomListener(window, 'load', map_init);
		      console.log("3-"+var_location);
		      
		      //start of modal google map
		      $('#mapmodals').on('shown.bs.modal', function () {
		          google.maps.event.trigger(var_map, "resize");
		          var_map.setCenter(var_location);
		      });
		</script>