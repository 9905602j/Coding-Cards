package commandline;

import java.util.Scanner;

import game.DBHandler;
import game.Game;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

//Mick, we should only write to the test log when this boolean is true (the setting of this is already 
//handled for us in the code provided). At the moment I'm thinking we'll pass this to Game and then put 
//put all your method calls in if() statements, this feels a bit hacky though, so I'll get some advice 
//and come back to you.

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		TopTrumpsCLIApplication start = new TopTrumpsCLIApplication();
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			userWantsToQuit = start.gameOrStats(writeGameLogsToFile);
			
		}
	}
//find out if the player would like to play, view stats or quit
	public boolean gameOrStats(boolean writeLog) {
//get the players choice
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to see past results or play a game?\n\t1: Print Game Statistics\n\t2: Play Game\n\t3: Quit\nEnter the number for your selection:");
		int gameOrStats = scanner.nextInt();
		System.out.println(gameOrStats);//print for testing
//display stats		
		if(gameOrStats==1) {
//			System.out.println("Program will now display stats");//print for testing
			DBHandler stats = new DBHandler();
			System.out.println(stats.displayGameStats());
			return false;
//start game
		}else if(gameOrStats==2) {
			Game game = new Game(writeLog, false);
			game.playCL();
			return false;
		}
// If the user wants to quit, gameOrStats == 3 here
		return true;
		
	}

}
