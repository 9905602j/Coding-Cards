<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">


			<!-- Add your HTML Here --><body>
			<div id="title" style="height:50px" widthclass="content">
			<h2>     Top Trumps Game</h2>
		</div>


	<div id="body" class="img">


	<div style="float: left; width: 15%;">
		<p></p>
	</div>

		<div style=" float: left; width: 70%;">
	<p></p>
			<div style=" float: left; width: 45%;">

				<p></p>
				<div id="status" class = "status">
					<a href="#" onclick="newGame()" class="btn btn-primary selectButton"> New Game </a>
				</div>
				<div id="description" class = "description">
					<h7> <br> Start a new Top Trumps Game </h7>
				</div>
			</div>
			<div style=" float: left; width: 10%;">
				<p></p>
			</div>
			<div style="; float: left; width: 45%;">
				<p></p>
				<div id="status" class = "status">
					<a href="/toptrumps/stats/" class="btn btn-primary selectButton"> Game Statistics </a>
					<div id="description" class = "description">
					<h7> <br> See the Game Statistics </h7>
				</div>
				</div>
			</div>
		</div>

	<div style="float: left; width: 15%;">
		<p></p>
	</div>
	</div>
	
	<style>

	.selectButton {
	width: 350px;
	height: 30px;
	border: 1px;
	border-radius: 2px;
	text-align: center;
	vertical-align: center;
	background-color: grey;
	color:white;
	}

	.selectButton:hover {
	background-color: #777;
	}

	.description {
	width: 350px;
	height: 100px;
	border: 1px;
	border-radius: 1px;
	text-align: center;
	vertical-align: center;
	background-color: white;
	color:black;
	}


	.img{
	position: relative;
	background-image: url(/assets/bg.jpg);                                                               
	height: 100vh;
	background-size: cover;
	}

	#title {
	background-color: #2c2c2c;
	color: white;
	text-align: center;
	}
	</style>

	</body>
	
		<script type="text/javascript">

						// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		

			// Tell the server to start a new game
			function newGame() {
				var xhr = createCORSRequest('POST', "http://localhost:7777/toptrumps/new_game");
				xhr.onload = function(e) {
					window.location.href="/toptrumps/game/";
				};
					
				xhr.send();			
			}
			
		</script>
		
</html>