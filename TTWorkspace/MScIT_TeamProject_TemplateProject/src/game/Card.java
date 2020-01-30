package game;

public class Card {
	private String name;
	private final int numOfAttributes = 5;
	private int [] attributeValues = new int[numOfAttributes];

	public Card(String cardDetails) {
		String [] details = cardDetails.split("\\s+");
		name = details[0];
		for(int i = 0;i<numOfAttributes;i++) {
			attributeValues[i] = Integer.parseInt(details[i+1]);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getAttributeValue(int i) {
		return attributeValues[i];
	}
	
	public String toString() {
		String card = name;
		for(int i=0; i<attributeValues.length;i++) {
			card = card + " " + attributeValues[i];
		}
		return card;
	}
}
