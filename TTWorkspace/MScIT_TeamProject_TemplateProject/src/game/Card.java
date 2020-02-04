package game;
//makes the cards that make up decks
public class Card {
	private String name;
	private final int numOfAttributes;
	private int [] attributeValues;

	public Card(String cardDetails, int numberOfCats) {
		numOfAttributes = numberOfCats;
//make the array to store the values of the cards attributes
		attributeValues = new int [numOfAttributes];
//split the String of details by the white spaces
		String [] details = cardDetails.split("\\s+");
//the cards name will be the first element in the details array
		name = details[0];
//iterate through the attributeValues array and fill with values following in the details array
		for(int i = 0;i<numOfAttributes;i++) {
			attributeValues[i] = Integer.parseInt(details[i+1]);
		}
	}
//returns the name of a card	
	public String getName() {
		return name;
	}
//returns the value of the attribute at a given index in the cards attributeValues array	
	public int getAttributeValue(int i) {
		return attributeValues[i];
	}
//returns a string containnig all the deatils of a card
//used for debugging and for display to the user
	public String toString() {
		String card = name;
		for(int i=0; i<attributeValues.length;i++) {
			card = card + " " + attributeValues[i];
		}
		return card;
	}
}
