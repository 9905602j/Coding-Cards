package game;

import java.util.Random;

public class AIPlayer extends Player{
	private int playerID;
	private int sizeOfHand;
	private Deck playersHand;
	
	public AIPlayer(int sizeOfHand, int ID) {
		super(sizeOfHand, ID);
	}
	
	public int pickCategory(int numberOfCats, String[]cats) {
		Random r = new Random();
		int catPicked = r.nextInt(numberOfCats);
		return catPicked;
	}

}
