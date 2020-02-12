package game;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TestLogHandler{
	
	private String fileName = "toptrumps.log";
	private PrintWriter writer;
	
	public TestLogHandler() {
		try {
			writer = new PrintWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}


}
			
	public void fullUnshuffledDeckPrint(Deck fullDeck) {
		String deck = "Full Deck, before suffle:\n\n";
		deck = deck + deckPrint(fullDeck);
		writer.print(deck);
		writer.flush();
	}
	
	public void shuffledDeckPrint(Deck shuffled) {
		String deck = "Full shuffled deck:\n\n";
		deck = deck + deckPrint(shuffled);
		writer.print(deck);
		writer.flush();
	}
	
	public void playersInitialHandsPrint(ArrayList <Player> players) {
		String playersHand = "Players hands at the start of the game:\n\n";
		playersHand = playersHand +  playersHandPrint(players);
		writer.print(playersHand);
		writer.flush();
	}
	
	public void communalPileAddedToPrint(Deck communalPile) {
		String communalDeck = "The communal pile has been added to this round, it now contains these cards: \n";
		communalDeck = communalDeck +  deckPrint(communalPile);
		writer.print(communalDeck);
		writer.flush();
		}
	
	public void recordEmptyCommunalPile() {
		String communalPile = "The communal pile has been emptied and now contains no cards.\n\n";
		writer.print(communalPile);
		writer.flush();
	}
	
	public void categoryAndValuesPrint(int cat, String [] categories, ArrayList <Player> players) {
		String category = "The category picked this round was: " + categories[cat] +"\n\nThe players values for this category are:\n";
		for(int i=0;i<players.size();i++) {
			if(players.get(i) instanceof HumanPlayer) {
				category = category + "Player " + players.get(i).getID() + "'s (human player): " + players.get(i).getTopCard().getAttributeValue(cat) + "\n";
			}else {
				category = category + "Player " + players.get(i).getID() + "'s (AI player): " + players.get(i).getTopCard().getAttributeValue(cat) + "\n";
			}
		}
		category = category + "\n\n";
		writer.print(category);
		writer.flush();
	}
	
	public void endOfRoundDecksPrint(ArrayList <Player> players, Deck communalPile) {
		String endOfRoundDecks = "The decks in play at the end of the round are:\n\n";
		endOfRoundDecks = endOfRoundDecks + playersHandPrint(players);
		if(communalPile.getDeck().size()==0) {
			endOfRoundDecks = endOfRoundDecks + "The communal Pile is currently empty.\n\n";
		}else{
			endOfRoundDecks = endOfRoundDecks + "The communal pile:\n\n" + deckPrint(communalPile);
		}
		writer.print(endOfRoundDecks);
		writer.flush();
	}
	
	public void recordWinner(Player winner) {
		String gameEnd = "";
		if(winner instanceof HumanPlayer) {
			gameEnd = "The winner of this game was:\n\nPlayer " + winner.getID() + " (human player)";
		}else {
			gameEnd = "The winner of this game was:\n\nPlayer " + winner.getID() + " (AI player)";
		}
		writer.print(gameEnd);
		writer.flush();
	}
	
	public String playersHandPrint(ArrayList <Player> players) {
		String playersHand = "";
		for(int i=0;i<players.size();i++) {
			playersHand = playersHand + "Player " +  players.get(i).getID() + "'s (";
			if(players.get(i) instanceof HumanPlayer) {
				playersHand = playersHand + "human player) hand:\n";
			}else {
				playersHand = playersHand + "AI player) hand:\n";
			}
			playersHand = playersHand + deckPrint(players.get(i).getHand());
		}
		return playersHand;
	}
	
	public String deckPrint(Deck toPrint) {
		String deck = "";
		for(int i=0;i<toPrint.getDeck().size();i++) {
			deck = deck + toPrint.getDeck().get(i).toString() + "\n";
		}
		deck = deck + "\n";
		return deck;
	}
	
	public void printTopCards(ArrayList <Player> players, int round){
		String topCards = "Round " + round + ":\n\nCurrent Players Top Cards:\n";
		for(int i = 0;i<players.size();i++) {
			if(players.get(i) instanceof HumanPlayer) {
				topCards = topCards + "Player " + players.get(i).getID() + "'s (human player) current card is:\n";
			}else {
				topCards = topCards + "Player " + players.get(i).getID() + "'s (AI player) current card is:\n";	
			}
			topCards = topCards + players.get(i).getTopCard().allDetailsPrint() + "\n";
		}
		topCards = topCards + "\n";
		writer.print(topCards);
		writer.flush();
	}

}