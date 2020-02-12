package game;

abstract class Player {
	private int playerID;
	private int sizeOfHand;
	private Deck playersHand;
//counter to keep track of number of rounds won by an individual player
	private int roundsWon;
	
	public Player(int sizeOfHand, int ID) {
		this.sizeOfHand = sizeOfHand;
		this.playerID = ID;
		playersHand = new Deck(sizeOfHand);
		roundsWon = 0;
	}
	
	public Deck getHand() {
		return playersHand;
	}
	
	public Card getTopCard() {
		return playersHand.getTopCard();
	}
	
	public int getID() {
		return playerID;
	}
	
	public void incrementRoundsWon() {
		roundsWon++;
	}
	
	public int getRoundsWon() {
		return roundsWon;
	}
//used to print the details of each player and their hands for debugging during dev	
	public void playerPrint() {
		System.out.println("\n Player is: " + playerID + "\n");
		playersHand.testPrint();
	}
//all types of player must be able to pick a category when it is their turn,
//but this will be done differently for different types of player
	abstract int pickCategory(int numberOfCats, String[]cats);
}