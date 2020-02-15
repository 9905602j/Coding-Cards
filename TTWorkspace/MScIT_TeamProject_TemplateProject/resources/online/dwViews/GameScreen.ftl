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

<!-- Add your HTML Here -->


<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Top Trump Game</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="styling.css" rel="stylesheet">
</head>
<body>



<body>
	<div id="title" style="height:50px" widthclass="content">
		<h2>     Top Trumps Game</h2>
	</div>

	<div id="instruction" style="background-color:rgb(28, 161, 227);height:50px;color: white" class="content">
	</div>

	<div id="body" class="img">
		<div id="body_left" class="left_box">
			<div id="body_left" class="left_box" >
				<div>
					<p></p>
					<div id="status" class = "status">
						<h5> <br> The active player is <span id="playerName">...</span> </h5>
					</div>

					<div id="roundResult" class = "roundResult">
						<h7 id="gameState"> </h7>
					</div>

					<div id="categoryDropDown" class="categories">
						<button id = "categories" onclick="myFunction()" class="dropbtn">Select a Category</button>
						<div id="myDropdown" class="dropdown-content">
							<a onclick="chooseCategory(1);" id="categoryButton1"></a>
							<a onclick="chooseCategory(2);" id="categoryButton2"></a>
							<a onclick="chooseCategory(3);" id="categoryButton3"></a>
							<a onclick="chooseCategory(4);" id="categoryButton4"></a>
							<a onclick="chooseCategory(5);" id="categoryButton5"></a>
						</div>
					</div>
					<div id="nextTurn" class="categories" >
						<button onclick="chooseCategory(1);" class="dropbtn">Next Turn</button>
					</div>	
					<button onclick="goToMain()" class = "goToMain"> Go Back to the Main </button>

				</div>
	

			<div id="chooseCategory">
			</div>


			</div>
			<div id="body_right" class="right_box" widthclass="human">
				<div id="yourCard" class = "card">
					<div id="yourCard_header" class = "header_human">
						<h4 id="humanPlayer">You</h4>
					</div>
					<div id="card_left" class = "cardleft">
						<h5 id="card_left_title"> <span id="cardsLeft"> Loading... </span></h5>
					</div>
					<div id="card_name" class = "cardName">
						<h4 id="card_left_title"> 350r </h4>
					</div>
					<div id="cardImg" class = "cardImg">
						<img src="/assets/350r.jpg" width="190" height="70" alt="My Image">
					</div>
					<div id="categories" class = "body_card">
						<h6 id="card_left_title" >
							<ul id="card_left_body">
								<li><span id="categoryName1"></span>: <span id="categoryValue1"></span></li>
								<li><span id="categoryName2"></span>: <span id="categoryValue2"></span></li>
								<li><span id="categoryName3"></span>: <span id="categoryValue3"></span></li>
								<li><span id="categoryName4"></span>: <span id="categoryValue4"></span></li>
								<li><span id="categoryName5"></span>: <span id="categoryValue5"></span></li>
							</ul>
						</h6>
					</div>
				</div>
			</div>
		</div>


		<div id="body_center" class="center_box">
		</div>
		<div id="showresult" style ="display:none">
		
			<div id="body_right" class="right_box" widthclass="ai">
				<div id="aiResult" class="card-deck">
					<div id="body_right_top" class = "top_box">
						<div id="AIPlayer1" style="float:left" class = "card">
							<div id="AIPlayer1_header" class = "header_ai">
								<h4 id="AIPlayer1">AI Player 1</h4>
							</div>
							<div id="card_left" class = "cardleft">
								<h5 id="card_left_title"> 3 cards left </h5>
							</div>
							<div id="card_name" class = "cardName">
								<h4 id="card_left_title"> Avenger </h4>
							</div>
							<div id="cardImg" class = "cardImg">
								<img src="/assets/Avenger.jpg" width="190" height="70" alt="My Image">
							</div>
							<div id="categories" class = "body_card">
								<h6 id="card_left_title" >
									<ul id="card_left_body">
										<li>Size: 1</li>
										<li>Speed: 2</li>
										<li>Range: 8</li>
										<li>Firepower: 4</li>
										<li>Cargo: 5</li>
									</ul>
								</h6>
							</div>
						</div>

						<div id="AIPlayer2" style="float:left;" class = "card">
							<div id="AIPlayer2_header" class = "header_ai">
								<h4 id="AIPlayer2">AI Player 2</h4>
							</div>
							<div id="card_left" class = "cardleft">
								<h5 id="card_left_title"> 1 cards left </h5>
							</div>
							<div id="card_name" class = "cardName">
								<h4 id="card_left_title"> Carrack </h4>
							</div>
							<div id="cardImg" class = "cardImg" >
								<img src="/assets/Carrack.jpg" width="190" height="70" alt="My Image">
							</div>
							<div id="categories" class = "body_card">
								<h6 id="card_left_title" >
									<ul id="card_left_body">
										<li>Size: 9</li>
										<li>Speed: 3</li>
										<li>Range: 8</li>
										<li>Firepower: 9</li>
										<li>Cargo: 1</li>
									</ul>
								</h6>
							</div>
						</div>
					</div>
				</div>
				<div class="card-deck">
					<div id="body_right_bottom" class = "bottom_box">
						<div><br>
						</div>

						<div id="AIPlayer3" style="float:left;" class = "card">
							<div id="AIPlayer3_header" class = "header_ai">
								<h4 id="AIPlayer3">AI Player 3</h4>
							</div>
							<div id="card_left" class = "cardleft">
								<h5 id="card_left_title"> 7 cards left </h5>
							</div>
							<div id="card_name" class = "cardName">
								<h4 id="card_left_title"> Constellation </h4>
							</div>
							<div id="cardImg" class = "cardImg">
								<img src="/assets/Constellation.jpg" width="190" height="70" alt="My Image">
							</div>
							<div id="categories" class = "body_card">
								<h6 id="card_left_title" >
									<ul id="card_left_body">
										<li>Size: 3</li>
										<li>Speed: 2</li>
										<li>Range: 2</li>
										<li>Firepower: 7</li>
										<li>Cargo: 6</li>
									</ul>
								</h6>
							</div>
						</div>

						<div id="AIPlayer4" style="float:left;" class = "card">
							<div id="AIPlayer4_header" class = "header_ai">
								<h4 id="AIPlayer4">AI Player 4</h4>
							</div>
							<div id="card_left" class = "cardleft">
								<h5 id="card_left_title"> 4 cards left </h5>
							</div>
							<div id="card_name" class = "cardName">
								<h4 id="card_left_title"> Hawk </h4>
							</div>
							<div id="cardImg" class = "cardImg">
								<img src="/assets/Hawk.jpg" width="190" height="70" alt="My Image">
							</div>
							<div id="categories" class = "body_card">
								<h6 id="card_left_title" >
									<ul id="card_left_body">
										<li>Size: 8</li>
										<li>Speed: 5</li>
										<li>Range: 2</li>
										<li>Firepower: 8</li>
										<li>Cargo: 4</li>
									</ul>
								</h6>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
				// display the current game state
				getAndDisplayGameState();
				
			}
			
			function goToMain(){
		    	window.location.href="http://localhost:7777/toptrumps"
		    }

			function myFunction() {
		    document.getElementById("myDropdown").classList.toggle("show");
		}
			
			window.onclick = function(event) {
		  if (!event.target.matches('.dropbtn')) {

		    var dropdowns = document.getElementsByClassName("dropdown-content");
		    var i;
		    for (i = 0; i < dropdowns.length; i++) {
		      var openDropdown = dropdowns[i];
		      if (openDropdown.classList.contains('show')) {
		        openDropdown.classList.remove('show');
		      }
		    }
		  }
		}
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
		
			// Get and display the current game state
			function getAndDisplayGameState() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game_state");
				xhr.onload = function(e) {
					var responseText = xhr.response;
					displayGameState($.parseJSON(responseText));
					};
					
				xhr.send();			
			}
			
			
		
			function displayGameState(response) {
				// Unpack the array from the API
				// [0] - Current active player
				// [1] - Current game state (i.e. what just happened)
				// [2] - Number of cards left for the human player
				// [3] - Your current card name
				// [4] - Card category value for category 1
				// [5] - Card category value for category 2
				// [6] - Card category value for category 3
				// [7] - Card category value for category 4
				// [8] - Card category value for category 5
				// [9] - Card category name for category 1
				// [10] - Card category name for category 2
				// [11] - Card category name for category 3
				// [12] - Card category name for category 4
				// [13] - Card category name for category 5
				// [14] - "1" for player's turn, "0" for AI turn
				
				var activePlayer = response[0];
				var gameState = response[1];
				var cardsLeft = response[2];
				var cardname = response[3];
				var cardCategoryValue1 = response[4];
				var cardCategoryValue2 = response[5];
				var cardCategoryValue3 = response[6];
				var cardCategoryValue4 = response[7];
				var cardCategoryValue5 = response[8];
				var cardCategoryName1 = response[9];
				var cardCategoryName2 = response[10];
				var cardCategoryName3 = response[11];
				var cardCategoryName4 = response[12];
				var cardCategoryName5 = response[13];
				var isHumanTurn = response[14];

				$('#playerName').text(activePlayer);
				if (cardsLeft == "0") {
					$('#cardsLeft').text("No cards left");
				} else {
					$('#cardsLeft').text(cardsLeft + " cards left");
				}
				
				$('#gameState').text(gameState);
				
				$('#categoryValue1').text(cardCategoryValue1);
				$('#categoryValue2').text(cardCategoryValue2);
				$('#categoryValue3').text(cardCategoryValue3);
				$('#categoryValue4').text(cardCategoryValue4);
				$('#categoryValue5').text(cardCategoryValue5);
				
				$('#categoryName1').text(cardCategoryName1);
				$('#categoryName2').text(cardCategoryName2);
				$('#categoryName3').text(cardCategoryName3);
				$('#categoryName4').text(cardCategoryName4);
				$('#categoryName5').text(cardCategoryName5);
				
				$('#categoryButton1').text(cardCategoryName1);
				$('#categoryButton2').text(cardCategoryName2);
				$('#categoryButton3').text(cardCategoryName3);
				$('#categoryButton4').text(cardCategoryName4);
				$('#categoryButton5').text(cardCategoryName5);
				
				if (isHumanTurn=="2") {
					$('#categoryDropDown').hide();
					$('#nextTurn').hide();
				} else {
					if (isHumanTurn=="1") {
						$('#categoryDropDown').show();
						$('#nextTurn').hide();
					} else {
						$('#categoryDropDown').hide();
						$('#nextTurn').show();
					}
				}	
				
			}
			
			// Send a choice of category to the server
			function chooseCategory(category) {
				var xhr = createCORSRequest('POST', "http://localhost:7777/toptrumps/choose_category?category="+category);
				xhr.onload = function(e) {
					getAndDisplayGameState();
				};
					
				xhr.send();			

			}

		</script>
		<style type="text/css">

		.img{
		position: relative;
		background-image: url(/assets/bg.jpg);                                                               
		height: 100vh;
		background-size: cover;
		}

		.tab {
		background-color: #555;
		color: white;
		float: center;
		border: none;
		outline: none;
		cursor: pointer;
		padding: 14px 16px;
		font-size: 17px;
		width: 25%;
		}


		.button {
		width: 100px;
		height: 50px;
		border: 1px solid grey;
		border-radius: 5px;
		}

		.header_human {
		width: 200px;
		height: 40px;
		border: 1px;
		border-radius: 7px;
		text-align: center;
		vertical-align: text-top;
		background-color: rgb(143,183,69);
		color:white;
		}

		.header_ai {
		width: 200px;
		height: 40px;
		border: 1px;
		border-radius: 7px;
		text-align: center;
		vertical-align: text-top;
		background-color: rgb(250, 106, 23);
		color:white;
		}

		.card {
		width: 200px;
		height: 260px;
		border: 1px;
		border-radius: 7px;
		margin:3px;
		background-color: grey;


		}

		.cardImg {
		width: 202px;
		height: 150px;
		border: 1px;
		border-radius: 2px;
		margin:5px;
		background-image: "";
		background-size: cover;

		}

		.card1 {
		width: 202px;
		height: 260px;
		border: 1px;
		border-radius: 2px;
		margin:3px;

		}


		.cardleft {
		width: 200px;
		height: 25px;
		border: 1px solid grey;
		text-align: center;
		border-radius: 5px;
		vertical-align: text-bottom;
		background-color: #2c2c2c;
		color: white;
		}
		.cardName {
		width: 200px;
		height: 20px;
		border: 1px ;
		text-align: center;
		border-radius: 2px;
		vertical-align: text-bottom;
		}

		.body_card {
		width: 200px;
		height: 105px;
		vertical-align: text-top;
		color: white;
		}

		.tab:hover {
		background-color: #777;
		}

		/* default content */
		.content {
		color: white;
		display: center;
		padding: 1px;
		text-align: center;
		}

		.left_box {
		float: left;
		width: 45%;

		}

		.center_box {
		float: center;
		width: 10%;
		}
		.right_box {
		float: right;
		width: 45%;
		}

		.top_box {
		float: top;
		height: 50%;
		}
		.bottom_box {
		float: bottom;
		height: 50%;
		}

		#title {
		background-color: #2c2c2c;
		color: white;
		}

		.showResult {
		width: 170px;
		background-color: rgb(143,183,69);
		color: white;
		padding: 16px;
		font-size: 16px;
		border: none;
		cursor: pointer;
		border-radius: 10px;
		}

		.showResult:hover, .showResult:focus {
		background-color: #eb540e;
		}

		.status {
		width: 170px;
		height: 100px;
		border: 1px;
		border-radius: 7px;
		text-align: center;
		vertical-align: center;
		background-color: rgb(28, 161, 227);
		color:white;
		}

		.roundResult {
		width: 170px;
		height: 100px;
		border: 1px;
		border-radius: 7px;
		text-align: center;
		vertical-align: center;
		background-color: white;
		color:black;
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

		/* Dropdown Button */
		.dropbtn {
		width: 170px;
		background-color: rgb(143,183,69);
		color: white;
		padding: 16px;
		font-size: 16px;
		border: none;
		cursor: pointer;
		border-radius: 10px;
		}

		/* Dropdown button on hover & focus */
		.dropbtn:hover, .dropbtn:focus {
		background-color: #eb540e;
		}

		/* The container <div> - needed to position the dropdown content */
		.dropdown {
		position: relative;
		display: inline-block;
		}

		/* Dropdown Content (Hidden by Default) */
		.dropdown-content {
		display: none;
		position: absolute;
		background-color: #f9f9f9;
		min-width: 160px;
		box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		}

		/* Links inside the dropdown */
		.dropdown-content a {
		color: black;
		padding: 12px 16px;
		text-decoration: none;
		display: block;
		}

		/* Change color of dropdown links on hover */
		.dropdown-content a:hover {background-color: #f1f1f1}

		/* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
		.show {display:block;}

		</style>

		
		</body>
</html>