package game;

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
	
	public void dealCards(ArrayList<Player> players) {
		
		for(int i = 0;i<cards.size();i = i+4) {
			for(int j = 0;j<players.size();j++) {
				players.get(j).getHand().addNewCard(cards.get(i+j));
			}
		}
	}
	
	public Card getTopCard() {
		return cards.get(0);
	}
	
	public int getSize() {
		return cards.size();
	}
	
	public void removeTopCard() {
		cards.remove(0);
	}
	
	public Card getCard(int i) {
		return cards.get(i);
	}
	
	public void clearDeck(){
		cards.clear();
	}
	
	public void testPrint() {
		for(int i=0;i<cards.size();i++) {
			System.out.println(cards.get(i).toString());
		}
	}
	

}
