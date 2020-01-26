package commandline;

public class Player {
	private int sizeOfHand;
	private Deck playersHand;
	private boolean isHuman = false;
	
	public Player(int sizeOfHand) {
		this.sizeOfHand = sizeOfHand;
		playersHand = new Deck(sizeOfHand);
	}
	
	public void setHuman() {
		isHuman = true;
	}
	
	public Deck getHand() {
		return playersHand;
	}
	public void playerPrint() {
		System.out.println("Player is: " + isHuman + sizeOfHand);
		playersHand.testPrint();
	}
}
