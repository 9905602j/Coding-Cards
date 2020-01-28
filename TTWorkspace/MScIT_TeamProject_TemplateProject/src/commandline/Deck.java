package commandline;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
	
	private ArrayList<Card> cards;
	
	public Deck(int sizeOfDeck) {
		cards = new ArrayList<Card>(sizeOfDeck);
	}
	
	public void addNewCard(Card card) {
		cards.add(card);
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public void dealCards(Player[] players) {
		
		for(int i = 0;i<cards.size();i = i+4) {
			for(int j = 0;j<players.length;j++) {
				players[j].getHand().addNewCard(cards.get(i+j));
			}
		}
	}
	
	public Card getTopCard() {
		return cards.get(0);
	}
	
	public int getSize() {
		return cards.size();
	}
	
	public void testPrint() {
		for(int i=0;i<cards.size();i++) {
			System.out.println(cards.get(i).toString());
		}
	}
	

}
