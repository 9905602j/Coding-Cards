package commandline;

import java.util.Scanner;

import game.DBHandler;
import game.Game;
import game.GameController;

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
		
//		TopTrumpsCLIApplication start = new TopTrumpsCLIApplication();
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			GameController newGame = new GameController(writeGameLogsToFile);
			userWantsToQuit = newGame.gameOrStats(writeGameLogsToFile);
			
		}
	}


}
