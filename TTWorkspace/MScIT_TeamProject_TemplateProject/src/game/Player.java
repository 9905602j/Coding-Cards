package game;

abstract class Player {
	private int playerID;
	private int sizeOfHand;
	private Deck playersHand;
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
	
	public void playerPrint() {
		System.out.println("\n Player is: " + playerID + "\n");
		playersHand.testPrint();
	}
	
	abstract int pickCategory(int numberOfCats, String[]cats);
}