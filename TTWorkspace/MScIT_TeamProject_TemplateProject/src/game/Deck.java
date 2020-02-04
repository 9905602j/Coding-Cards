package game;

import java.util.ArrayList;
import java.util.Collections;

//Decks are used to store the fulldeck at the start of the game,
//the players hands throughout the game,
//and the communal pile throughout the game
public class Deck {
//cards that make up each deck are stored in an ArrayList	
	private ArrayList<Card> cards;
	
	public Deck(int sizeOfDeck) {
//ArrayList initated at the size required for that deck
		cards = new ArrayList<Card>(sizeOfDeck);
	}
//Adds new cards to the back of the deck
	public void addNewCard(Card card) {
		cards.add(card);
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
//Deals cards from one deck to the decks belonging to players in an ArrayList
	public void dealCards(ArrayList<Player> players) {
		
		for(int i = 0;i<cards.size();i = i+players.size()) {
			for(int j = 0;j<players.size();j++) {
				players.get(j).getHand().addNewCard(cards.get(i+j));
			}
		}
	}
//returns the top card of a deck without removing it from the deck	
	public Card getTopCard() {
		return cards.get(0);
	}
//returns the number of cards in a deck	
	public int getSize() {
		return cards.size();
	}
	
	public void removeTopCard() {
		cards.remove(0);
	}
//returns the card at position i in a decks ArrayList	
	public Card getCard(int i) {
		return cards.get(i);
	}
//empties a deck of all cards
	public void clearDeck(){
		cards.clear();
	}
//used to print out decks during dev to help with debugging
	public void testPrint() {
		for(int i=0;i<cards.size();i++) {
			System.out.println(cards.get(i).toString());
		}
	}
	

}
