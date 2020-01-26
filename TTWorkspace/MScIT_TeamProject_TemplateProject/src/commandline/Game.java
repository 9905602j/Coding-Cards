package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Game {
	
	private final int numberOfCategories = 5;
	private final int sizeOfDeck = 40;
	
	private Deck fullDeck;
	private Deck communalPile;
	private Player humanPlayer;
	private Player AIone;
	private Player AItwo;
	private Player AIthree;
	private Player AIfour;
	

	
	
	public Game() {
		makeFullDeck();
		
	}
	
	public void makeFullDeck() {
		FileReader deckReader;
		try {
			deckReader = new FileReader("StarCitizenDeck.txt");
			Scanner scanner = new Scanner(deckReader);
			scanner.useDelimiter("\\s+");
			scanner.next();
			
			String[] categories = new String[numberOfCategories];
			for(int i=0;i<numberOfCategories;i++) {
				categories[i] = scanner.next();
			}
			
			fullDeck = new Deck(sizeOfDeck, categories);
			
			scanner.nextLine();
			
			for(int i = 0; i<sizeOfDeck; i++) {
				String cardDetails = scanner.nextLine();
				Card card = new Card(cardDetails);
				fullDeck.addNewCard(card);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		fullDeck.testPrint();

		
	}
	
	

}
