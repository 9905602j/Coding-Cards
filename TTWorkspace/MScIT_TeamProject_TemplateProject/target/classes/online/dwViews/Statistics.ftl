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
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Top Trump Game</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="styling.css" rel="stylesheet">
<body>
	<div id="title" style="height:50px" widthclass="content">
		<h2>     Top Trumps Game</h2>
	</div>


<div id="body" class="img">


<div style="float: left; width: 15%;">
	<p></p>
</div>

	<div style=" float: left; width: 70%;">
<p></p>
			<p></p>
			<div id="description" class = "head_stats">
				<h3> Game Statistics </h3>
			</div>
			<div id="description" class = "description_stats">
				<h7> <ul><li> Number of Games: 3 </li>
					<p><li> Number of Human Wins: 5 </li>
					<p><li> Number of AI Wins: 2 </li>
					<p><li> Average Draws per Game: 0 </li>
					<p><li> Longest Game: 72 </li>
				</ul>

				 </h7>
			</div>
			<div>
			<br><br><br><br>
			<button onclick="goToMain()" class = "goToMain"> Go Back to the Main </button>
			</div>
			</div>
<div style="float: left; width: 15%;">
	<p></p>
</div>
</div>
</div>

</body>

		<style type="text/css">


		.head_stats {

		height: 30px;
		border: 1px;
		border-radius: 2px;
		text-align: center;
		vertical-align: center;
		background-color: grey;
		color:black;
		}

		.selectButton:hover {
		background-color: #777;
		}


		.description_stats {

		height: 250px;
		border: 1px solid: grey;
		border-radius: 1px;
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
		
		.goToMain {
		width: 170px;
		border: 1px;
		border-radius: 7px;
		text-align: center;
		vertical-align: center;
		background-color: #2c2c2c;
		color:white;
		}



		</style>

			<!-- Add your HTML Here -->
		
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
			}
				function goToMain(){
		    	window.location.href="http://localhost:7777/toptrumps/"
		    	}
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
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
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>