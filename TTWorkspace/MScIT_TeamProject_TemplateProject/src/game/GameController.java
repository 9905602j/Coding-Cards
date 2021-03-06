package game;

import java.util.Scanner;

public class GameController {
	
	private Game game;
	private GameView gameView;
	private boolean writeGameLogsToFile;

//controller constructor for CL version
	public GameController(boolean writeLog) {
		writeGameLogsToFile = writeLog;
	}
//controller constructor for online version
	public GameController(Game g) {
		writeGameLogsToFile = false;
		this.game = g;
		gameView = new GameView(g);
	}
	
	// allow the UI to access the game view
	public GameView getView() {
		return gameView;
	}
	
	// CLI version: find out if the player would like to play, view stats or quit
	public boolean gameOrStats(boolean writeLog) {
	//get the players choice
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to see past results or play a game?\n\t1: Print Game Statistics\n\t2: Play Game\n\t3: Quit\nEnter the number for your selection:");
		int gameOrStats = scanner.nextInt();
//		System.out.println(gameOrStats);//print for testing
//display stats		
		if(gameOrStats==1) {
//			System.out.println("Program will now display stats");//print for testing
			DBHandler stats = new DBHandler();
			System.out.println(stats.displayGameStats());
			return false;
//start game
		}else if(gameOrStats==2) {
			game = new Game(writeLog, false);
			gameView = new GameView(game);
			playCL();
			return false;
		}
// If the user wants to quit, gameOrStats == 3 here
		return true;
			
	}
		
//controls game loop for command line operation
	public void playCL() {
//print code below used for testing during dev, I'm leaving it here for use during future dev
			
//		for(int i=0; i<numberOfPlayers; i++) {
//			players.get(i).playerPrint();
//		}

		System.out.println("Game Start");
//Main game loop
		while(game.getGameOver()==false) {
			game.incrementRoundNum();
//displays the information needed by the user at the start of each round
			System.out.println(gameView.displayRoundStart());
			System.out.println(gameView.displayCurrentCard());
//gets the category choice from the active player
//If active player is human it will call a method in HumanPlayer to allow the user to pick
//If the active player is AI it will call a method in AIPlayer to pick at random (the AI should be really bad at this game)
			int categoryPicked = game.getPlayers().get(game.getActivePlayer()).pickCategory(game.getNumberOfCategories(), game.getCategories());
//passes the category picked by the active player to playRound() which finds the winner of the round and
//moves cards from hand to hand as required
			game.playRound(categoryPicked);
//once playRound() has moved all cards as required the results of the round are displayed to the user
//both displayRoundResult() and displayWinningCard() fill the String gameState with the required info
			System.out.println(gameView.displayRoundResult(game.getWonRound(), categoryPicked));
			gameView.displayWinningCard(game.getWinner(), categoryPicked);
			System.out.println(gameView.toString());

//print code below used for testing during dev, I'm leaving it here for use during future dev
				
//			for(int j=0; j<players.size(); j++) {
//				players.get(j).playerPrint();
//			}
//			System.out.println("\n The communal pile is \n");
//			communalPile.testPrint();
				
		}
//If the game exits the loop it is over.
//displays the results of the game to the user.
		System.out.println(gameView.displayGameEnd());
		if(writeGameLogsToFile==true) {
			game.getTestLogHandler().recordWinner(game.getPlayers().get(0));
		}
	}
		
//Used by the online version to start each round, builds up the gameState String with the correct 
//details for the start of the round allowing it to be passed to the API and displayed on the web page
	public void startRoundOnline() {
//first check if the game is over and display end of game info if so
		if(game.getGameOver()==true) {
			gameView.displayGameEnd();
		}else {
//if not display the start of round info and wait for response from user
			game.incrementRoundNum();
			gameView.displayRoundStart(); 

		}
	}

//used by the online version to display the results of each round
	public void finishRoundOnline() {
		gameView.displayRoundResult(game.getWonRound(), game.getCatPicked());
		gameView.displayWinningCard(game.getWinner(), game.getCatPicked());
	}
	
	// Return the game stats as a string for display
	public String getStats() {
		DBHandler stats = new DBHandler();
		return stats.displayGameStats();
	}
	
}
