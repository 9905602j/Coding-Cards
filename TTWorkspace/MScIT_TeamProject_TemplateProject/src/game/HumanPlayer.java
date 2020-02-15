package game;

import java.util.Scanner;

public class HumanPlayer extends Player {
	
	public HumanPlayer(int sizeOfHand, int ID) {
		super(sizeOfHand, ID);
	}
	
//used by commandLine mode to allow the user to pick the category when they are the active player
//category picked returned as an int representing that categories place in the cards categories array
	public int pickCategory(int numberOfCats, String[]cats ) {
//display the options to the player
		System.out.println("It is your turn to select a category, the categories are:");
		for(int i = 0; i<numberOfCats;i++) {
			System.out.println("\t" + (i+1) + ":\t" + cats[i]);
		}
//get the players choice
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number for your attribute:");
		int catPicked = scanner.nextInt()-1;
		return catPicked;
	}

}
