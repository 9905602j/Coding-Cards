package game;

public class GameDisplay {

	//String used to display the state of the game at any given point	
	private String gameState = "Game Start";
	
	public void UpDateRoundStartDisplayForOnline(Game game) {
		if(game.players.get(game.getActivePlayer())instanceof HumanPlayer) {
			gameState = gameState + "It is your turn, please pick a category.";
//
//Maybe put the categories are... part in here for online version. Would like to get them displayed on the buttons though
//
		}else {
			gameState = gameState + "It is Player " + game.players.get(game.getActivePlayer()).getID() + "'s turn, press any button to continue.";
		}
	}

	public String displayRoundStart(Game game) {
		gameState = "Round " + game.roundNum + "\n" + "Round " + game.roundNum + ": Players have drawn their cards\n";
	//if the human player is still in the game display the card they drew and how many cards they have in their hand
		if(game.players.get(game.humanPlayer) instanceof HumanPlayer) {
			gameState = gameState + "You drew '" + game.players.get(game.humanPlayer).getTopCard().getName() + "':\n";
			for(int i=0;i<game.categories.length;i++) {
				gameState = gameState + "\t> " + game.categories[i] + ": " + game.players.get(game.humanPlayer).getTopCard().getAttributeValue(i) +"\n";
			}
			gameState = gameState +"There are '" + game.players.get(game.humanPlayer).getHand().getSize() + " cards in your deck\n";
		}
		return gameState;
	}

	public void displayRoundResult(Game game, Player wonRound, int catPicked) {
	//if the round was a draw tell the user and give size of communal pile
	//if the user won tell them
	//if an AI won tell the user which one
		if(game.isDraw==true){
			gameState = "Round " + game.roundNum + ": This round is a draw, common pile now has " + game.communalPile.getSize() + " cards\n" ;
		}else if(wonRound instanceof HumanPlayer) {
			gameState = "Round " + game.roundNum +": Player you won this round\n";
		}else {
			gameState = "Round " + game.roundNum + ": Player " + wonRound.getID() + " won this round\n";
		}	
	}

	public String displayWinningCard(Game game, Card winner, int categoryPicked) {
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

	public String displayGameEnd(Game game) {
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
