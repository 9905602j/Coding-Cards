package game;

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
	private Player[] startingPlayers = new Player[numberOfPlayers];
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
		
//Mick to add writing of shuffled deck to test log, see the note about writing in the unshuffled deck 
//below for more details, it should be pretty much the same, you may even be able to use the same write 
//method every time you need to write a deck to the log, this includes all players hands and communal 
//pile as well as the actual deck.

//make all players and deal out they're hands from the shuffled main deck
		makePlayers();
		fullDeck.dealCards(players);
		
//Mick to add writing of players hands to test log. See the note on writting in the suffled and unshuffled 
//decks above and below for the details of writting in the players hands/decks. As for determining which one
//is the human player and which are AI's you may be able to use the instanceof operator (see displayRoundStart() 
//method below for an example of it's use), also bare in mind that the human players ID should always be player 1.
		
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
//Mick to add in writting full unshuffeled deck to test log. You'll need to write a method in you test
//log handler class that will take the fulldeck Deck object and then write the details to the log. There is 
//a print method (testPint()) in the Deck class you can use to help you, I've been using it to print out decks for 
//debugging as I've gone along. You should be able to pretty much copy that code into your test log class to do the 
//printing to file. Below is an suggestion/example of what I mean.
		
		//TestLog t = new TestLog();
		//writeInUnsuffledDeck(fulldeck);
		

	}

	public void makePlayers() {
		HumanPlayer h = new HumanPlayer(sizeOfDeck/numberOfPlayers, 1);
		players.add(humanPlayer, h);
		startingPlayers[humanPlayer] = h;
		for(int i = 1;i<numberOfPlayers;i++) {
			AIPlayer p = new AIPlayer(sizeOfDeck/numberOfPlayers, (i+1));
			players.add(i, p);
			startingPlayers[i] = p;
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
			displayRoundStart();
			int categoryPicked = players.get(activePlayer).pickCategory(numberOfCategories, categories);
			playRound(categoryPicked);
			for(int j=0; j<players.size(); j++) {
				players.get(j).playerPrint();
			}
			System.out.println("\n The communal pile is \n");
			communalPile.testPrint();
		}
		System.out.println("\n\nGame End");
		
//Mick, to put in writing the winner of the game to the test log here. The winner will be the only 
//Player left in the players ArrayList at this point. 
		
// Maz you should be able to add writing everything that needs to be saved at the end of the 
//game to the DB in here. There should be attributes for everything that you need to pass to the 
//DB in this class. Write a method in your DBHandler class to take them and write them to the DB 
//and the call it from here. Below is a suggestion/example for you.
		
		//DBHandler d = new DBHandler();
		//d.writeToDB(numberOfDraws, players(0).getID(), roundNum);
		
//The above would write pass the number of draws, who won, and the number of rounds.
		
		//for(int i=0;i<startingPlayers.length;i++){
			//d.writeRoundsWonToDB(startingPlayers[i].getID(), startingPlayers[i].getRoundsWon());
		//}

//This second bit would pass each players ID and how many rounds they won to the DB handler class.
	}
	
	public void playRound(int categoryPicked) {
		
//Mick, to put in a call to write the top cards of each player to the test log. See the code in 
//displayStartOfRound() method below for ideas on how to do this as it prints out the players
//active card to the console. I would think you would need to pass the testlog class a copy of the 
//players decks and the categories array. E.g.
		//for(int i=0;i<players.size();i++){
			//writeActiveCardsToTL(players(i).get().getHand(), categories)
		//}
//This is just of the top of my head though so don't take that as a given...
		
		
//Mick to put in a call to write the category and it's values to the test log here. You should be able 
//to do this by passing it the above int (categoryPicked), and each players top card 
//e.g. in a for loop: players.get(i).getHand().getTopCard();
//Again this is off the top of my head, if you see a neater way to do it go ahead, alot of this seems a 
//little messy to me...
		
		System.out.println("The cat picked was: " + categoryPicked);
		Player wonRound = findWinningPlayer(categoryPicked);
		Card winner = wonRound.getHand().getTopCard();

		if(isDraw==false) {
			giveCardsToWinner(wonRound);
			giveComPileToWinner(wonRound);
			moveWinnersTopCard(wonRound);
			
//Mick, to put in writing of communal pile to test log here as cards may have been removed from it 
//here. Again see the notes about writing in the big decks above. Both this and the call below 
//could also be put within the methods that add and remove cards from the pile, which may be neater,
//up to you what you think when you do it.
			
		}else {
			putCardsInCommunalPile();
			
//Mick, cards will have been added to the communal pile here so add another call to write the pile to the test 
//here. Again see the notes about the full decks above.
			
		}
		checkForLosers();
		displayRoundResult(wonRound, categoryPicked);
		displayWinningCard(winner, categoryPicked);
		
//Mick, to put in writing the contents of each deck at the end of the round to the test log.
//Please see all the other notes about writting the various decks to the log...
		
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
			wonRound.incrementRoundsWon();
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
				if(i==activePlayer) {
					pickActivePlayer();
				}
				if(activePlayer>i) {
					activePlayer--;
				}
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
