package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	private final int numberOfCategories = 5;
	private final int sizeOfDeck = 40;
	private final int numberOfPlayers = 4;
	private final int humanPlayer = 0;
	
	private ArrayList <Player> players = new ArrayList<Player>(numberOfPlayers);
	private int activePlayer;
	
	private String[] categories;
	private Deck fullDeck;
	private Deck communalPile = new Deck(0);
	
	private int roundNum;
	private boolean isDraw = false;
	private int numberOfDraws = 0;
	private boolean gameOver = false;

	

	
	
	public Game() {
		
//read deck in from file and shuffle it.
		makeFullDeck();
		fullDeck.shuffle();
		
//Mick to add writting of shuffled deck to test log

//make all players and deal out they're hands from the shuffled main deck
		makePlayers();
		fullDeck.dealCards(players);
//Mick to add writting of players hands to test log
		
		pickActivePlayer();
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
		players.add(humanPlayer, h);
		for(int i = 1;i<numberOfPlayers;i++) {
			AIPlayer p = new AIPlayer(sizeOfDeck/numberOfPlayers, (i+1));
			players.add(i, p);
		}
	}
	
	public void pickActivePlayer() {
		Random r = new Random();
		activePlayer = r.nextInt(players.size());
	}
	
	public void play() {
		for(int i=0; i<numberOfPlayers; i++) {
			players.get(i).playerPrint();
		}
		System.out.println("Game Start");
		while(gameOver==false) {
			playRound();
			for(int j=0; j<players.size(); j++) {
				players.get(j).playerPrint();
			}
			System.out.println("\n The communal pile is \n");
			communalPile.testPrint();
		}
		System.out.println("\n\nGame End");
	}
	
	public void playRound() {
		displayRoundStart();
		int categoryPicked = players.get(activePlayer).pickCategory(numberOfCategories, categories);
		System.out.println("The cat picked was: " + categoryPicked);
		Player wonRound = findWinningPlayer(categoryPicked);
		Card winner = wonRound.getHand().getTopCard();

		if(isDraw==false) {
			giveCardsToWinner(wonRound);
			giveComPileToWinner(wonRound);
			moveWinnersTopCard(wonRound);
		}else {
			putCardsInCommunalPile();
		}
		checkForLosers();
		displayRoundResult(wonRound, categoryPicked);
		displayWinningCard(winner, categoryPicked);
		isItOver();
		roundNum++;
		
	}
	
	public void displayRoundStart() {
		System.out.println("Round " + roundNum);
		System.out.println("Round " + roundNum + ": Players have drawn their cards");
		if(players.get(humanPlayer) instanceof HumanPlayer) {
			System.out.println("You drew '" + players.get(humanPlayer).getTopCard().getName() + "':");
			for(int i=0;i<categories.length;i++) {
				System.out.println("\t> " + categories[i] + ": " + players.get(humanPlayer).getTopCard().getAttributeValue(i));
			}
			System.out.println("There are '" + players.get(humanPlayer).getHand().getSize() + " cards in your deck");
		}
	}
	
	public Player findWinningPlayer(int catPicked) {
		int toBeat = 0;
		int nextActivePlayer = 0;
		Player wonRound = players.get(0);
		for(int i =0;i<players.size();i++) {
			int contender = players.get(i).getTopCard().getAttributeValue(catPicked);
			if(contender==toBeat) {
				isDraw = true;
			}else if(contender>toBeat) {
				toBeat = contender;
				wonRound = players.get(i);				
				nextActivePlayer = i;
				isDraw = false;
			}
		}
		if(isDraw==false) {
			activePlayer= nextActivePlayer;
		}else {
			numberOfDraws++;
		}
		return wonRound;
	}
	
	public void displayRoundResult(Player wonRound, int catPicked) {
		if(isDraw==true){
			System.out.println("Round " + roundNum + ": This round is a draw, common pile now has " + communalPile.getSize() + " cards" );
		}else if(wonRound instanceof HumanPlayer) {
			System.out.println("Round " + roundNum +": Player you won this round");
		}else {
			System.out.println("Round " + roundNum + ": Player " + wonRound.getID() + " won this round");
		}
		
	}
	
	public void displayWinningCard(Card winner, int catPicked) {
		System.out.println("The winning card was: '" + winner.getName() + "'");
		for(int i=0;i<categories.length;i++) {
			if(catPicked == i) {
				System.out.println("\t> " + categories[i] + ": " + winner.getAttributeValue(i) + " <--");
			}else {
				System.out.println("\t> " + categories[i] + ": " + winner.getAttributeValue(i));
			}
		}
	}
	
	public void giveCardsToWinner(Player winner) {
		for(int i = 0;i<players.size();i++) {
			if(!(players.get(i)==winner)) {
				Card card = players.get(i).getHand().getTopCard();
				players.get(i).getHand().removeTopCard();
				winner.getHand().addNewCard(card);
			}
		}
	}
	
	public void putCardsInCommunalPile() {
		for(int i=0; i<players.size(); i++) {
			Card card = players.get(i).getHand().getTopCard();
			players.get(i).getHand().removeTopCard();
			communalPile.addNewCard(card);
		}
	}
	
	public void giveComPileToWinner(Player winner) {
		for(int i=0; i<communalPile.getSize(); i++) {
			winner.getHand().addNewCard(communalPile.getCard(i));
		}
		communalPile.clearDeck();
	}
	
	
	public void moveWinnersTopCard(Player winner) {
		Card toMove = winner.getTopCard();
		winner.getHand().removeTopCard();
		winner.getHand().addNewCard(toMove);
	}
	
	public void checkForLosers() {
		for(int i=0;i<players.size();i++) {
			if(players.get(i).getHand().getSize()==0){
				players.remove(i);
				i--;
			}
		}
	}
	
	public void isItOver() {
		if(players.size()<2) {
			gameOver = true;
		}
	}
	
	
	
	

}
