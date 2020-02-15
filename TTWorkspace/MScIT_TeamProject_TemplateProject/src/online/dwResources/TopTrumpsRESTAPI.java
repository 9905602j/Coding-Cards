package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import game.Card;
import game.Game;
import game.GameController;
import game.GameView;
import game.HumanPlayer;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	
	Game game;
	GameController controller;
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf, Game game) {
		this.game = game;
		controller = new GameController(game);
	}
	
	@GET
	@Path("/game_state")
	public String gameState() throws IOException {
// RUN THE GAME UNTIL WE NEED SOME MORE INPUT HERE,
// AND RETURN THE PROGRESS ON THE GAME AS A STRING
//So right now it runs the start of the round and displays the appropriate info to the user
//then the program waits for input, when it gets it it calls choose category below
		controller.startRoundOnline();
		ArrayList<String> jsData = new ArrayList<String>();
		// We produce a list containing all the data the javascript needs
		// This contains, always in this order:
		// [0] - Current active player
		// [1] - Current game state (i.e. what just happened)
		// [2] - Number of cards left if the human is still in the game, "0" if not
		// [3] - Your current card name
		// [4] - Card category value for category 1
		// [5] - Card category value for category 2
		// [6] - Card category value for category 3
		// [7] - Card category value for category 4
		// [8] - Card category value for category 5
		// [9] - Card category name for category 1
		// [10] - Card category name for category 2
		// [11] - Card category name for category 3
		// [12] - Card category name for category 4
		// [13] - Card category name for category 5
		// [14] - "2" for game over, "1" for the human player's turn next, "0" for an AI player next.
		GameView view = controller.getView();
		
		jsData.add(view.getActivePlayerString());
		jsData.add(view.toString());
		Card ourCard = game.getHumanTopCard();
		if (ourCard != null) {
			jsData.add(Integer.toString(view.getHumanCardsLeft())); // 2 - They're still in the game
			jsData.add(ourCard.getName()); // 3
			jsData.add(Integer.toString(ourCard.getAttributeValue(0))); // 4
			jsData.add(Integer.toString(ourCard.getAttributeValue(1))); // 5
			jsData.add(Integer.toString(ourCard.getAttributeValue(2))); // 6
			jsData.add(Integer.toString(ourCard.getAttributeValue(3))); // 7
			jsData.add(Integer.toString(ourCard.getAttributeValue(4))); // 8
		} else {
			// Card name
			jsData.add("0"); // 2
			jsData.add("You're out!"); // 3
			jsData.add(""); // 4
			jsData.add(""); // 5
			jsData.add(""); // 6
			jsData.add(""); // 7
			jsData.add(""); // 8
		}
		
		// Add in the category names
		String[] categories = game.getCategories();
		jsData.add(categories[0]); // 9
		jsData.add(categories[1]); // 10
		jsData.add(categories[2]); // 11
		jsData.add(categories[3]); // 12
		jsData.add(categories[4]); // 13
		
		// Is it the human player's turn?
		if (game.isItOver()) {
			jsData.add("2");
		} else {
			if (game.getPlayers().get(game.getActivePlayer()) instanceof HumanPlayer) {
				jsData.add("1");
			} else {
				jsData.add("0");
			}
		}
		return oWriter.writeValueAsString(jsData);
	}
	

	@POST
	@Path("/new_game")
	public String new_game() throws IOException {
		game = new Game(false, true);
		controller = new GameController(game);
		return oWriter.writeValueAsString("OK");
	}
	
	
	@POST
	@Path("/choose_category")
	public String chooseCategory(@QueryParam("category") int category) throws IOException {
//once input has been given by the user it is passed to the game and processed
		game.playRound(category - 1);
//gets the gameState to update and then passes the new String back to the API for display
		controller.finishRoundOnline();
		return oWriter.writeValueAsString("OK");
	}
	
	@GET
	@Path("/stats")
	public String getStats() throws IOException {
// RUN THE GAME UNTIL WE NEED SOME MORE INPUT HERE,
// AND RETURN THE PROGRESS ON THE GAME AS A STRING
//So right now it runs the start of the round and displays the appropriate info to the user
//then the program waits for input, when it gets it it calls choose category below
		return oWriter.writeValueAsString(controller.getStats());
	}
	
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}
	
}
