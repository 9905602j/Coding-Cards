package commandline;

import java.util.Scanner;

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

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			TopTrumpsCLIApplication start = new TopTrumpsCLIApplication();
			start.gameOrStats(start);
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}
	}
	
	public void gameOrStats(TopTrumpsCLIApplication start) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to see past results or play a game?\n	1: Print Game Statistics\n	2: Play Game\nEnter the number for your selection:");
		int gameOrStats = scanner.nextInt();
		System.out.println(gameOrStats);//print for testing
		
		if(gameOrStats==1) {
			System.out.println("Program will now display stats");//print for testing
//Maz to add call to data base here
			start.gameOrStats(start);
		}else if(gameOrStats==2) {
			System.out.println("A new game will now start");//print for testing
			Game game = new Game();
		}
	}

}
