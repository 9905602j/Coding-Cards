package game;

public class GameView {

	private Game game;
	
	public GameView(Game game) {
		this.game = game;
	}
	
	//String used to display the state of the game at any given point	
	private String gameState = "Game Start";
	
	// Return either "You" or "AI Player X" for the current player.
	// Used by the web interface
	public String getActivePlayerString() {
		int playerNumber = game.getActivePlayer();
		Player player = game.getPlayers().get(playerNumber);
		if (player instanceof HumanPlayer) {
			return "You";
		} else {
			return "AI Player "+player.getID();
		}
	}
	
	// Return how many cards left the human player has,
	// if they're still in the game, else zero.
	public int getHumanCardsLeft() {
		Player human = game.getHumanPlayer();
		if (human != null) {
			return human.getHand().getSize();
		} else {
			return 0;
		}
	}

	// Return the start-of-round message text
	public String displayRoundStart() {
		gameState = "Round " + game.roundNum + ": Players have drawn their cards\n";
		return gameState;
	}
	
	// Return a string showing the player's current card and the categories on it.
	public String displayCurrentCard() {
		String currentCard = "";
		if(game.players.get(game.humanPlayer) instanceof HumanPlayer) {
			currentCard = currentCard + "You drew '" + game.players.get(game.humanPlayer).getTopCard().getName() + "':\n";
			for(int i=0;i<game.categories.length;i++) {
				currentCard = currentCard + "\t> " + game.categories[i] + ": " + game.players.get(game.humanPlayer).getTopCard().getAttributeValue(i) +"\n";
			}
			currentCard = currentCard +"There are '" + game.players.get(game.humanPlayer).getHand().getSize() + " cards in your deck\n";
		}
		return currentCard;
	}

	public String displayRoundResult(Player wonRound, int catPicked) {
	//if the round was a draw tell the user and give size of communal pile
	//if the user won tell them
	//if an AI won tell the user which one
		if(game.isDraw==true){
			return "Round " + game.roundNum + ": This round is a draw, common pile now has " + game.communalPile.getSize() + " cards\n" ;
		}else if(wonRound instanceof HumanPlayer) {
			return "Round " + game.roundNum +": Player you won this round\n";
		}else {
			return "Round " + game.roundNum + ": Player " + wonRound.getID() + " won this round\n";
		}	
	}

	public String displayWinningCard(Card winner, int categoryPicked) {
		gameState = gameState + "The winning card was: '" + winner.getName() + "'\n";
	//iterate through the attributes of the winning card
		for(int i=0;i<game.categories.length;i++) {
	//if this was the winning attribute add visual indicator
			if(categoryPicked == i) {
				gameState = gameState + "\t> " + game.categories[i] + ": " + winner.getAttributeValue(i) + " <--\n";
			}else {
				gameState = gameState + "\t> " + game.categories[i] + ": " + winner.getAttributeValue(i) + "\n";
			}
		}
		return gameState;
	}

	public String displayGameEnd() {
		gameState = "\n\nGame End\n\n";
	//display who the winner of the game was
		if(game.players.get(0) instanceof AIPlayer) {
			gameState = gameState + "The overall winner was AI player " + game.players.get(0).getID() + "\n";
		}else {
			gameState = gameState + "Congratulations you won!\n";
		}
	//iterate through the players using the startingPlayers Array(So that all the players are there,
	//as only one player will be left in the players arraylist at this point) showing their rounds won
		gameState = gameState + "Scores:\n";
		for(int i=0;i<game.startingPlayers.length;i++) {
			if(game.startingPlayers[i] instanceof AIPlayer) {
				gameState = gameState + "\tAI Player " + game.startingPlayers[i].getID() + ": " + game.startingPlayers[i].getRoundsWon() + "\n";
			}else {
				gameState = gameState + "\tYou: " + game.startingPlayers[i].getRoundsWon() + "\n";
			}
		}
		return gameState;
	}

	public String toString() {
		return gameState;
	}
}
