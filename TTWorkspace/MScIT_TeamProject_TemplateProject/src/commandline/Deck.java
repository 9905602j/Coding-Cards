package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Deck {
	
	private Card[]cards;
	private String[] categories;
	
	private int nextCardPos;
	
	public Deck(int sizeOfDeck, String[] categories) {
		nextCardPos = 0;
		cards = new Card[sizeOfDeck];
		this.categories = categories;
	}
	
	public void addNewCard(Card card) {
		cards[nextCardPos] = card;
		nextCardPos++;
	}
	
	public void testPrint() {
		for(int i=0;i<categories.length;i++) {
			System.out.println(categories[i]);;
		}
		for(int i=0;i<cards.length;i++) {
			System.out.println(cards[i].toString());
		}
	}
	

}
