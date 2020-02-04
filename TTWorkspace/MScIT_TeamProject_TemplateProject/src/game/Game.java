package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
//attributes the specification told us would always be the same	
	private final int numberOfCategories = 5;
	private final int sizeOfDeck = 40;
	private final int numberOfPlayers = 4;
//human player will always be player one and in position 0
	private final int humanPlayer = 0;
//booleans of games current running state	
	private boolean writeGameLogsToFile;
	private boolean runningOnline;
	
//ArrayList used to store all players currently in the game, players are removed once they loose
	private ArrayList <Player> players = new ArrayList<Player>(numberOfPlayers);
//Array used to store all players, used to retrieve player info at the end of the game
	private Player[] startingPlayers = new Player[numberOfPlayers];
//used to keep track of who's turn it is
	private int activePlayer;
	
	private int catPicked;
	private Player wonRound;
	private Card winner;
	private String[] categories;
	private Deck fullDeck;
	private Deck communalPile = new Deck(0);

//counter used to keep track of number of rounds played and the number of the current round
	private int roundNum;
	private boolean isDraw = false;
//counter used to keep track of the number of drawn rounds in a game
	private int numberOfDraws = 0;
	private boolean gameOver = false;
//String used to display the state of the game at any given point	
	private String gameState = "Game Start";

	

	
	
	public Game(boolean writeLog, boolean online) {
//set booleans to allow appropriate running for each mode, command line, online and writing test log
		writeGameLogsToFile = writeLog;
		runningOnline = online;
		
//read deck in from file and shuffle it.
		makeFullDeck();
		fullDeck.shuffle();
		
//Mick to add writing of shuffled deck to test log, see the note about writing in the unshuffled deck 
//below for more details, it should be pretty much the same, you may even be able to use the same write 
//method every time you need to write a deck to the log, this includes all players hands and communal 
//pile as well as the actual deck.

//make all players and deal out they're hands from the shuffled main deck
		makePlayers();
		fullDeck.dealCards(players);
		
//Mick to add writing of players hands to test log. See the note on writting in the suffled and unshuffled 
//decks above and below for the details of writting in the players hands/decks. As for determining which one
//is the human player and which are AI's you may be able to use the instanceof operator (see displayRoundStart() 
//method below for an example of it's use), also bare in mind that the human players ID should always be player 1.

//randomly pick player to take first turn and start the round counter
		pickActivePlayer();
		roundNum = 0;
		
		}

//reads the deck in from the file provided
	public void makeFullDeck() {
		FileReader deckReader;
		try {
			deckReader = new FileReader("StarCitizenDeck.txt");
			Scanner scanner = new Scanner(deckReader);
//pass by and seperate values by white space when reading from file
			scanner.useDelimiter("\\s+");
			scanner.next();
//capture what categories the deck has, eg size etc			
			categories = new String[numberOfCategories];
			for(int i=0;i<numberOfCategories;i++) {
				categories[i] = scanner.next();
			}
//make an empty deck to hold all the cards (size of deck is always the same as per the specifications)			
			fullDeck = new Deck(sizeOfDeck);
			
			scanner.nextLine();
//read each cards details from file, make card and add to the deck			
			for(int i = 0; i<sizeOfDeck; i++) {
//get each cards details as a String from the file and pass to cards constructor 
				String cardDetails = scanner.nextLine();
				Card card = new Card(cardDetails, numberOfCategories);
				fullDeck.addNewCard(card);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//Mick to add in writing full unshuffeled deck to test log. You'll need to write a method in you test
//log handler class that will take the fulldeck Deck object and then write the details to the log. There is 
//a print method (testPint()) in the Deck class you can use to help you, I've been using it to print out decks for 
//debugging as I've gone along. You should be able to pretty much copy that code into your test log class to do the 
//printing to file. Below is an suggestion/example of what I mean.
		
		//TestLog t = new TestLog();
		//writeInUnsuffledDeck(fulldeck);
		

	}
	
//makes the players and adds them to the array list players and array startingPlayers
	public void makePlayers() {
//make human player first, they will always be player 1
		HumanPlayer h = new HumanPlayer(sizeOfDeck/numberOfPlayers, 1);
		players.add(humanPlayer, h);
		startingPlayers[humanPlayer] = h;
//make the AI players
		for(int i = 1;i<numberOfPlayers;i++) {
			AIPlayer p = new AIPlayer(sizeOfDeck/numberOfPlayers, (i+1));
			players.add(i, p);
			startingPlayers[i] = p;
		}
	}

//Randomly pick the active player, used at the start of each game and if the active
//player has their hand emptied by a drawn round
	public void pickActivePlayer() {
		Random r = new Random();
		activePlayer = r.nextInt(players.size());
	}

//controls game loop for command line operation
	public void playCL() {
//print code below used for testing during dev, I'm leaving it here for use during future dev
		
//		for(int i=0; i<numberOfPlayers; i++) {
//			players.get(i).playerPrint();
//		}

		System.out.println("Game Start");
//Main game loop
		while(gameOver==false) {
			roundNum++;
//displays the information needed by the user at the start of each round
			System.out.println(displayRoundStart());
//gets the category choice from the active player
//If active player is human it will call a method in HumanPlayer to allow the user to pick
//If the active player is AI it will call a method in AIPlayer to pick at random (the AI should be really bad at this game)
			int categoryPicked = players.get(activePlayer).pickCategory(numberOfCategories, categories);
//passes the category picked by the active player to playRound() which finds the winner of the round and
//moves cards from hand to hand as required
			playRound(categoryPicked);
//once playRound() has moved all cards as required the results of the round are displayed to the user
//both displayRoundResult() and displayWinningCard() fill the String gameState with the required info
			displayRoundResult(wonRound, categoryPicked);
			displayWinningCard(winner, categoryPicked);
			System.out.println(gameState);

//print code below used for testing during dev, I'm leaving it here for use during future dev
			
//			for(int j=0; j<players.size(); j++) {
//				players.get(j).playerPrint();
//			}
//			System.out.println("\n The communal pile is \n");
//			communalPile.testPrint();
			
		}
//If the game exits the loop it is over.
//displays the results of the game to the user.
		System.out.println(displayGameEnd());
		
//Mick, to put in writing the winner of the game to the test log here. The winner will be the only 
//Player left in the players ArrayList at this point. 
		
// Maz you should be able to add writing everything that needs to be saved at the end of the 
//game to the DB in here. There should be attributes for everything that you need to pass to the 
//DB in this class. Write a method in your DBHandler class to take them and write them to the DB 
//and the call it from here. Below is a suggestion/example for you.
		
		//DBHandler d = new DBHandler();
		//d.writeToDB(numberOfDraws, players(0).getID(), roundNum);
		
//The above would write pass the number of draws, who won, and the number of rounds.
		
		//for(int i=0;i<startingPlayers.length;i++){
			//d.writeRoundsWonToDB(startingPlayers[i].getID(), startingPlayers[i].getRoundsWon());
		//}

//This second bit would pass each players ID and how many rounds they won to the DB handler class.
	}
	
//Used by the online version to start each round, builds up the gameState String with the correct 
//details for the start of the round allowing it to be passed to the API and displayed on the web page
	public void startRoundOnline() {
//first check if the game is over and display end of game info if so
		if(gameOver==true) {
			gameState = displayGameEnd();
		}else {
//if not display the start of round info and wait for response from user
			roundNum++;
			gameState = displayRoundStart(); 
			if(players.get(activePlayer)instanceof HumanPlayer) {
				gameState = gameState + "It is your turn, please pick a category.";
//
//Maybe put the categories are... part in here for online version. Would like to get them displayed on the buttons though
//
			}else {
				gameState = gameState + "It is Player " + players.get(activePlayer).getID() + "'s turn, press any button to continue.";
			}
		}
	}

//used by the online version to display the results of each round
	public void finishRoundOnline() {
		displayRoundResult(wonRound, catPicked);
		displayWinningCard(winner, catPicked);
	}

//used by both command line and web based versions to play through the details of a round once a category has been picked
	public void playRound(int categoryPicked) {
		
//Mick, to put in a call to write the top cards of each player to the test log. See the code in 
//displayStartOfRound() method below for ideas on how to do this as it prints out the players
//active card to the console. I would think you would need to pass the testlog class a copy of the 
//players decks and the categories array. E.g.
		//for(int i=0;i<players.size();i++){
			//writeActiveCardsToTL(players(i).get().getHand(), categories)
		//}
//This is just of the top of my head though so don't take that as a given...
		
		
//Mick to put in a call to write the category and it's values to the test log here. You should be able 
//to do this by passing it the above int (categoryPicked), and each players top card 
//e.g. in a for loop: players.get(i).getHand().getTopCard();
//Again this is off the top of my head, if you see a neater way to do it go ahead, alot of this seems a 
//little messy to me...
		
//when playing online if the active player is the human player input from the button = catPicked,
//if it is an AI player we need to call the AI's pick method to get catPicked
		if(runningOnline==true && players.get(activePlayer) instanceof AIPlayer) {
			catPicked = players.get(activePlayer).pickCategory(numberOfCategories, categories);
		}else {
			catPicked = categoryPicked;
		}
//find which player won the round
		wonRound = findWinningPlayer(catPicked);
//find which card they won with so we can display the details
		winner = wonRound.getHand().getTopCard();
//if the round was not a draw give all the right cards to the winner and move their winning card to the back of their hand
		if(isDraw==false) {
			giveCardsToWinner(wonRound);
			giveComPileToWinner(wonRound);
			
//Mick, to put in writing of communal pile to test log here as cards may have been removed from it 
//here. Again see the notes about writing in the big decks above. Both this and the call below 
//could also be put within the methods that add and remove cards from the pile, which may be neater,
//up to you what you think when you do it.

//if the round was a draw put the players top cards in the communal pile
		}else {
			putCardsInCommunalPile();
			
//Mick, cards will have been added to the communal pile here so add another call to write the pile to the test 
//here. Again see the notes about the full decks above.
			
		}
//check if any players have dropped out 
		checkForLosers();

		
//Mick, to put in writing the contents of each deck at the end of the round to the test log.
//Please see all the other notes about writing the various decks to the log...

//check if the game is over
		isItOver();
	}

//builds up the gameState String to display the right details at the start of each round 
	public String displayRoundStart() {
		gameState = "Round " + roundNum + "\n" + "Round " + roundNum + ": Players have drawn their cards\n";
//if the human player is still in the game display the card they drew and how many cards they have in their hand
		if(players.get(humanPlayer) instanceof HumanPlayer) {
			gameState = gameState + "You drew '" + players.get(humanPlayer).getTopCard().getName() + "':\n";
			for(int i=0;i<categories.length;i++) {
				gameState = gameState + "\t> " + categories[i] + ": " + players.get(humanPlayer).getTopCard().getAttributeValue(i) +"\n";
			}
			gameState = gameState +"There are '" + players.get(humanPlayer).getHand().getSize() + " cards in your deck\n";
		}
		return gameState;
	}

//takes the category picked by the active player and returns the player who won the round
//if the round is a draw winning player remains the same 
	public Player findWinningPlayer(int catPicked) {
//used to store the highest value found so far
		int toBeat = 0;
		int nextActivePlayer = 0;
		Player wonRound = players.get(0);
//iterates through the players top cards comparing the value of the picked category to find the highest
		for(int i =0;i<players.size();i++) {
			int contender = players.get(i).getTopCard().getAttributeValue(catPicked);
//if the current cards value is equal to the highest found so far we may have a draw
//if the current cards value is higher we may have a winner
			if(contender==toBeat) {
				isDraw = true;
			}else if(contender>toBeat) {
				toBeat = contender;
				wonRound = players.get(i);				
				nextActivePlayer = i;
				isDraw = false;
			}
		}
//if we don't have a draw change active player and increment rounds won counter for winning player
//if we do have a draw increment games draw counter
		if(isDraw==false) {
			activePlayer= nextActivePlayer;
			wonRound.incrementRoundsWon();
		}else {
			numberOfDraws++;
		}
		return wonRound;
	}

//builds up the gameState String to display the right details at the end of each round
	public void displayRoundResult(Player wonRound, int catPicked) {
//if the round was a draw tell the user and give size of communal pile
//if the user won tell them
//if an AI won tell the user which one
		if(isDraw==true){
			gameState = "Round " + roundNum + ": This round is a draw, common pile now has " + communalPile.getSize() + " cards\n" ;
		}else if(wonRound instanceof HumanPlayer) {
			gameState = "Round " + roundNum +": Player you won this round\n";
		}else {
			gameState = "Round " + roundNum + ": Player " + wonRound.getID() + " won this round\n";
		}	
	}

//Adds to gameState String to display the winning card for each round, will also display the card that drew a drawn round
	public String displayWinningCard(Card winner, int categoryPicked) {
		gameState = gameState + "The winning card was: '" + winner.getName() + "'\n";
//iterate through the attributes of the winning card
		for(int i=0;i<categories.length;i++) {
//if this was the winning attribute add visual indicator
			if(categoryPicked == i) {
				gameState = gameState + "\t> " + categories[i] + ": " + winner.getAttributeValue(i) + " <--\n";
			}else {
				gameState = gameState + "\t> " + categories[i] + ": " + winner.getAttributeValue(i) + "\n";
			}
		}
		return gameState;
	}

//builds up the gameState String to display the game info once the game is over
	public String displayGameEnd() {
		gameState = "\n\nGame End\n\n";
//display who the winner of the game was
		if(players.get(0) instanceof AIPlayer) {
			gameState = gameState + "The overall winner was AI player " + players.get(0).getID() + "\n";
		}else {
			gameState = gameState + "Congratulations you won!\n";
		}
//iterate through the players using the startingPlayers Array(So that all the players are there,
//as only one player will be left in the players arraylist at this point) showing their rounds won
		gameState = gameState + "Scores:\n";
		for(int i=0;i<startingPlayers.length;i++) {
			if(startingPlayers[i] instanceof AIPlayer) {
				gameState = gameState + "\tAI Player " + startingPlayers[i].getID() + ": " + startingPlayers[i].getRoundsWon() + "\n";
			}else {
				gameState = gameState + "\tYou: " + startingPlayers[i].getRoundsWon() + "\n";
			}
		}
		return gameState;
	}

//gives the top cards of all players to the winner of the round
//This includes the winners top card which moves it to the back of their hand
	public void giveCardsToWinner(Player winner) {
		for(int i = 0;i<players.size();i++){
				Card card = players.get(i).getHand().getTopCard();
				players.get(i).getHand().removeTopCard();
				winner.getHand().addNewCard(card);
		}
	}

//called if a round is a draw to put all players top cards into the communal pile
	public void putCardsInCommunalPile() {
		for(int i=0; i<players.size(); i++) {
			Card card = players.get(i).getHand().getTopCard();
			players.get(i).getHand().removeTopCard();
			communalPile.addNewCard(card);
		}
	}

//iterates through the communal pile adding the cards to the winning players deck
	public void giveComPileToWinner(Player winner) {
		for(int i=0; i<communalPile.getSize(); i++) {
			winner.getHand().addNewCard(communalPile.getCard(i));
		}
//once cards have been added to the winning player empty the communal pile
		communalPile.clearDeck();
	}

	public void checkForLosers() {
//iterate through the players to see if any have empty hands and should be removed from the game
		for(int i=0;i<players.size();i++) {
			if(players.get(i).getHand().getSize()==0){
				players.remove(i);
//if the active player has dropped out(this can happen if a drawn round empties their hand) 
//pick a new player at random as at the start of the game
				if(i==activePlayer) {
					pickActivePlayer();
				}
//if the active player is at a higher index than one that has left the active player counter needs to change as their index will change
				if(activePlayer>i) {
					activePlayer--;
				}
//if a player has been removed we need to go back one index to avoid skipping a player
				i--;
			}
		}
	}

//if there is only one player left the game is over
	public void isItOver() {
		if(players.size()<2) {
			gameOver = true;
		}
	}

//returns the gameState string to the API for display on the web based version
	public String toString() {
		return gameState;
	}
	
	
	
	

}
