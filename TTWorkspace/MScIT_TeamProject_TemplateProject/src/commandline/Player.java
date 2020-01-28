package commandline;

abstract class Player {
	private int playerID;
	private int sizeOfHand;
	private Deck playersHand;
	
	public Player(int sizeOfHand, int ID) {
		this.sizeOfHand = sizeOfHand;
		this.playerID = ID;
		playersHand = new Deck(sizeOfHand);
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
	
	public void playerPrint() {
		System.out.println("\n Player is: " + playerID + "\n");
		playersHand.testPrint();
	}
	
	abstract int pickCategory(int numberOfCats, String[]cats);
}