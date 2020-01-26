package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class Deck {
	
	private Card[]cards;
	
	private int nextCardPos;
	
	public Deck(int sizeOfDeck) {
		nextCardPos = 0;
		cards = new Card[sizeOfDeck];
	}
	
	public void addNewCard(Card card) {
		cards[nextCardPos] = card;
		nextCardPos++;
	}
	
	public void shuffle() {
		Card[]shuffledDeck = new Card[cards.length];
		int i = 0;
		while(i<cards.length) {
			Random card = new Random();
			int randomIndex = card.nextInt(cards.length);
			if(cards[randomIndex]==null) {
			}else {
				shuffledDeck[i] = cards[randomIndex];
				cards[randomIndex]=null;
				i++;
			}
		}
		cards = shuffledDeck;
	}
	
	public void dealCards(Player[] players) {

		for(int i = 0;i<cards.length;i = i+4) {
			for(int j = 0;j<players.length;j++) {
				players[j].getHand().addNewCard(cards[i+j]);
			}
		}
	}
	
	public void testPrint() {
		for(int i=0;i<cards.length;i++) {
			System.out.println(cards[i].toString());
		}
	}
	

}
