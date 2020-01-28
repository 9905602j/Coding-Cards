package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	private final int numberOfCategories = 5;
	private final int sizeOfDeck = 40;
	private final int numberOfPlayers = 4;
	private final int humanPlayer = 0;
	
	private Player[] players = new Player[numberOfPlayers];
	private int activePlayer;
	
	private String[] categories;
	private Deck fullDeck;
	private Deck communalPile = new Deck(sizeOfDeck);
	
	private int roundNum;
	private boolean isDraw = false;

	

	
	
	public Game() {
		
//read deck in from file and shuffle it.
		makeFullDeck();
		fullDeck.shuffle();
		
//Mick to add writting of shuffled deck to test log

//make all players and deal out they're hands from the shuffled main deck
		makePlayers();
		fullDeck.dealCards(players);
		
		pickFirstPlayer();
		roundNum = 1;
		
		}
	
	public void makeFullDeck() {
		FileReader deckReader;
		try {
			deckReader = new FileReader("StarCitizenDeck.txt");
			Scanner scanner = new Scanner(deckReader);
			scanner.useDelimiter("\\s+");
			scanner.next();
			
			categories = new String[numberOfCategories];
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

	public void makePlayers() {
		HumanPlayer h = new HumanPlayer(sizeOfDeck/numberOfPlayers, 1);
		players[humanPlayer] = h;
		for(int i = 1;i<numberOfPlayers;i++) {
			AIPlayer p = new AIPlayer(sizeOfDeck/numberOfPlayers, (i+1));
			players[i] = p;
		}
	}
	
	public void pickFirstPlayer() {
		Random r = new Random();
		activePlayer = r.nextInt(numberOfPlayers);
	}
	
	public void play() {
		System.out.println("Game Start");
		playRound();
		
	}
	
	public void playRound() {
		displayRoundStart();
		int categoryPicked = players[activePlayer].pickCategory(numberOfCategories, categories);
		System.out.println("The cat picked was: " + categoryPicked);
		Player wonRound = findWinningPlayer(categoryPicked);
		displayRoundResult(wonRound, categoryPicked);
		displayWinningCard(wonRound, categoryPicked);
		
		System.out.println("The current active player is: " + activePlayer);
		roundNum++;
		
	}
	
	public void displayRoundStart() {
		System.out.println("Round " + roundNum);
		System.out.println("Round " + roundNum + ": Players have drawn their cards");
		System.out.println("You drew '" + players[0].getTopCard().getName() + "':");
		
		for(int i=0;i<categories.length;i++) {
			System.out.println("\t> " + categories[i] + ": " + players[humanPlayer].getTopCard().getAttributeValue(i));
		}
		
		System.out.println("There are '" + players[humanPlayer].getHand().getSize() + " cards in your deck");
	}
	
	public Player findWinningPlayer(int catPicked) {
		int toBeat = 0;
		int nextActivePlayer = 0;
		Player wonRound = players[0];
		for(int i =0;i<players.length;i++) {
			int contender = players[i].getTopCard().getAttributeValue(catPicked);
			if(contender>toBeat) {
				toBeat = contender;
				wonRound = players[i];
				nextActivePlayer = i;
				isDraw = false;
			}else if(contender==toBeat) {
				isDraw = true;
			}
		}
		if(isDraw==false) {
			activePlayer= nextActivePlayer;
		}
		return wonRound;
	}
	
	public void displayRoundResult(Player wonRound, int catPicked) {
		if(isDraw==true){
			System.out.println("Round " + roundNum + ": This round is a draw");
		}else if(wonRound==players[humanPlayer]) {
			System.out.println("Round " + roundNum +": Player you won this round");
		}else {
			System.out.println("Round " + roundNum + ": Player " + wonRound.getID() + " won this round");
		}
		
	}
	
	public void displayWinningCard(Player wonRound, int catPicked) {
		System.out.println("The winning card was: '" + wonRound.getTopCard().getName() + "'");
		for(int i=0;i<categories.length;i++) {
			if(catPicked == i) {
				System.out.println("\t> " + categories[i] + ": " + wonRound.getTopCard().getAttributeValue(i) + " <--");
			}else {
				System.out.println("\t> " + categories[i] + ": " + wonRound.getTopCard().getAttributeValue(i));
			}
		}
	}
	
	
	
	

}
