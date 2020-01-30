package commandline;

import java.util.Scanner;

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
			
			userWantsToQuit = start.gameOrStats();
			
		}
	}
	
	public boolean gameOrStats() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to see past results or play a game?\n\t1: Print Game Statistics\n\t2: Play Game\n\t3: Quit\nEnter the number for your selection:");
		int gameOrStats = scanner.nextInt();
		System.out.println(gameOrStats);//print for testing
		
		if(gameOrStats==1) {
			System.out.println("Program will now display stats");//print for testing
			
//Maz to add call to data base here. You'll need to write a method in your DBHandler class that will call
//the DB and then display the info to the console. 
			return false;
		}else if(gameOrStats==2) {
			Game game = new Game();
			game.play();
			return false;
		}
		// If the user wants to quit, gameOrStats == 3 here
		return true;
		
	}

}
