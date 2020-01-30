package game;

import java.util.Scanner;

public class HumanPlayer extends Player {
	private int playerID;
	private int sizeOfHand;
	private Deck playersHand;
	
	public HumanPlayer(int sizeOfHand, int ID) {
		super(sizeOfHand, ID);
	}
	
	public int pickCategory(int numberOfCats, String[]cats ) {
		System.out.println("It is your turn to select a category, the categories are:");
		for(int i = 0; i<numberOfCats;i++) {
			System.out.println("\t" + (i+1) + ":\t" + cats[i]);
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number for your attribute:");
		int catPicked = scanner.nextInt()-1;
		return catPicked;
	}

}
