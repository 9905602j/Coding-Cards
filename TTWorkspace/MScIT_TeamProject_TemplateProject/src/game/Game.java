package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// this is the model class for the whole game
public class Game {
//attributes the specification told us would always be the same	
	private final int numberOfCategories = 5;
	private final int sizeOfDeck = 40;
	// number of players set to 4, ideal implementation of online version would require this to be 
	//settable by the player, this function has not been implemented at this time.
	private final int numberOfPlayers = 4;
//human player will always be player one and in position 0
	final int humanPlayer = 0;
//booleans of games current running state	
	private boolean writeGameLogsToFile;
	private boolean runningOnline;
	private TestLogHandler testLog;
	
//ArrayList used to store all players currently in the game, players are removed once they loose
	ArrayList <Player> players = new ArrayList<Player>(numberOfPlayers);
//Array used to store all players, used to retrieve player info at the end of the game
	Player[] startingPlayers = new Player[numberOfPlayers];
//used to keep track of who's turn it is
	private int activePlayer;
	
	private int catPicked;
	private Player wonRound;
	private Card winner;
	String[] categories;
	private Deck fullDeck;
	Deck communalPile = new Deck(0);

//counter used to keep track of number of rounds played and the number of the current round
	int roundNum;
	boolean isDraw = false;
//counter used to keep track of the number of drawn rounds in a game
	private int numberOfDraws = 0;
	private boolean gameOver = false;

	public Game(boolean writeLog, boolean online) {
//set booleans to allow appropriate running for each mode, command line, online and writing test log
		writeGameLogsToFile = writeLog;
		runningOnline = online;
		if(writeGameLogsToFile==true) {
			testLog = new TestLogHandler();
		}
		
//read deck in from file and shuffle it.
		makeFullDeck();
		fullDeck.shuffle();
		if(writeGameLogsToFile==true) {
			testLog.shuffledDeckPrint(fullDeck);
		}

//make all players and deal out their hands from the shuffled main deck
		makePlayers();
		fullDeck.dealCards(players);
		if(writeGameLogsToFile==true) {
			testLog.playersInitialHandsPrint(players);
		}

//randomly pick player to take first turn and start the round counter
		pickActivePlayer();
		roundNum = 0;
		
		}
	
	// Getters
	
	public int getActivePlayer() {
		return activePlayer;
	}
	
	public boolean getGameOver() {
		return gameOver;
	}
	
	public ArrayList <Player> getPlayers(){
		return players;
	}
	
	public int getNumberOfCategories() {
		return numberOfCategories;
	}
	
	public String [] getCategories() {
		return categories;
	}
	
	public Player getWonRound() {
		return wonRound;
	}
	
	public Card getWinner() {
		return winner;
	}
	
	public TestLogHandler getTestLogHandler() {
		return testLog;
	}
	
	public void incrementRoundNum() {
		roundNum++;
	}
	
	public int getCatPicked() {
		return catPicked;
	}

//reads the deck in from the file provided
	public void makeFullDeck() {
		FileReader deckReader;
		try {
			deckReader = new FileReader("StarCitizenDeck.txt");
			Scanner scanner = new Scanner(deckReader);
//pass by and separate values by white space when reading from file
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
				Card card = new Card(cardDetails, numberOfCategories, categories);
				fullDeck.addNewCard(card);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(writeGameLogsToFile==true) {
			testLog.fullUnshuffledDeckPrint(fullDeck);
		}
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


//used by both command line and web based versions to play through the details of a round once a category has been picked
	public void playRound(int categoryPicked) {
		if(writeGameLogsToFile==true) {
		testLog.printTopCards(players, roundNum);
		}
		
//when playing online if the active player is the human player input from the button = catPicked,
//if it is an AI player we need to call the AI's pick method to get catPicked
		if(runningOnline==true && players.get(activePlayer) instanceof AIPlayer) {
			catPicked = players.get(activePlayer).pickCategory(numberOfCategories, categories);
		}else {
			catPicked = categoryPicked;
		}
		if(writeGameLogsToFile==true) {
			testLog.categoryAndValuesPrint(catPicked, categories, players);
		}
//find which player won the round
		wonRound = findWinningPlayer(catPicked);
//find which card they won with so we can display the details
		winner = wonRound.getHand().getTopCard();
//if the round was not a draw give all the right cards to the winner and move their winning card to the back of their hand
		if(isDraw==false) {
			giveCardsToWinner(wonRound);
			 if(!(communalPile.getSize()==0)) {
				 giveComPileToWinner(wonRound);
			 }
//if the round was a draw put the players top cards in the communal pile
		}else{
			putCardsInCommunalPile();
		}
//check if any players have dropped out 
		checkForLosers();
		if(writeGameLogsToFile==true) {
			testLog.endOfRoundDecksPrint(players, communalPile);
		}
//check if the game is over
		isItOver();
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
		if(writeGameLogsToFile==true) {
			testLog.communalPileAddedToPrint(communalPile);
		}
	}

//iterates through the communal pile adding the cards to the winning players deck
	public void giveComPileToWinner(Player winner) {
		for(int i=0; i<communalPile.getSize(); i++) {
			winner.getHand().addNewCard(communalPile.getCard(i));
		}
//once cards have been added to the winning player empty the communal pile
		communalPile.clearDeck();
		if(writeGameLogsToFile==true) {
			testLog.recordEmptyCommunalPile();
		}
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
	public boolean isItOver() {
		// Don't write to the DB twice.
		if (gameOver) return gameOver;
		
		if(players.size()<2) {
			gameOver = true;
			writeToDB();
		}
		return gameOver;
	}
	
	// Write the record of this game to the database
	public void writeToDB() {
		DBHandler insert = new DBHandler();
		boolean humanWon = false;
		if(players.get(0) instanceof HumanPlayer) {
			humanWon = true;
		}
		insert.addGameToDB(numberOfDraws, roundNum, humanWon);
	}

	// return the human player object, or null if the human
	// is already out of the game.
	public Player getHumanPlayer() {
		Player maybeHuman = players.get(0);
		if (maybeHuman instanceof HumanPlayer) {
			return maybeHuman;
		} else {
			return null;
		}
	}

	// Get the top card for a player
	public Card getPlayerTopCard(Player player) {
		return player.getTopCard();
	}

	// if there's still a human player in the game, return their top card
	// return null if there's no human player left
	public Card getHumanTopCard() {
		Player humanPlayer = getHumanPlayer();
		if (humanPlayer != null) {
			return getPlayerTopCard(humanPlayer);
		} else {
			return null;
		}
	}
}
