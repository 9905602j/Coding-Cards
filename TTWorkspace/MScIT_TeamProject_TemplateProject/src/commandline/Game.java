package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Game {
	
	private final int numberOfCategories = 5;
	private final int sizeOfDeck = 40;
	private final int numberOfPlayers = 4;
	
	private Player[] players = new Player[numberOfPlayers];
	
	private Deck fullDeck;
	private Deck communalPile;

	

	
	
	public Game() {
		
//read deck in from file and shuffle it.
		makeFullDeck();
		fullDeck.shuffle();
		
//Mick to add writting of shuffled deck to test log

//make all players and deal out they're hands from the shuffled main deck
		makePlayers();
		fullDeck.dealCards(players);
		}
		
		
	
	public void makePlayers() {
		for(int i = 0;i<numberOfPlayers;i++) {
			Player p = new Player(sizeOfDeck/numberOfPlayers);
			players[i] = p;
		}
//player 0 is always human, all other players are AI.
		players[0].setHuman();
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
			
			fullDeck = new Deck(sizeOfDeck);
			
			scanner.nextLine();
			
			for(int i = 0; i<sizeOfDeck; i++) {
				String cardDetails = scanner.nextLine();
				Card card = new Card(cardDetails);
				fullDeck.addNewCard(card);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//Mick to add in writting full unshuffeled deck to test log.	


		
	}
	
	
	
	

}
