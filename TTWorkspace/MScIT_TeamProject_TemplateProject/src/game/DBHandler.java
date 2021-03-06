package game;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;


public class DBHandler {
	private static String connectionString = "jdbc:postgresql://52.24.215.108/CodingCards";
	private static String dbUsername = "CodingCards";
	private static String dbPassword = "CodingCards";
// Main method used for testing
//		public static void main(String[]args) throws SQLException{
//			DBHandler insert = new DBHandler();
////			insert.addGameToDB();
//			insert.gamesPlayed();
//		}
		
		//writes all the relevant details to the DB at the end of each game
		public void addGameToDB(int numOfDraws, int numOfRounds, boolean humanWon) {
// variables used during testing
//			int numOfDraws = 17;
//			int numOfRounds = 78;
//			boolean humanWon = false;
			try {
				Connection connection = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
				PreparedStatement insert = connection.prepareStatement("Insert into persistantgamedata(totalrounds, totaldraws, winner) values (?,?,?)");
					insert.setInt(1,  numOfRounds);
					insert.setInt(2, numOfDraws);
					insert.setBoolean(3, humanWon);
					insert.executeUpdate();
					// int i = insert.executeUpdate();
//	Test code to check insert is working
//					if(i>0) {
//						System.out.println("Insert complete");
//					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//builds and returns a string for displaying game stats
		public String displayGameStats() {
			String stats = "Game Statistics:\n";
			stats = stats + "\tNumber of games: " + gamesPlayed();
			stats = stats + "\n\tNumber of human wins: " + humanWins();
			stats = stats + "\n\tNumber of AI wins: " + AIWins();
			stats = stats + "\n\tAverage number of draws per game: " + avgDraws();
			stats = stats + "\n\tLongest game: " + longestGame() + "\n";
			return stats;
		}
		
		//retrieves the number of games played from the DB
		public int gamesPlayed() {
			int allGames = 0;
			try {
				Connection connection = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
				PreparedStatement getGamesPlayed = connection.prepareStatement("SELECT COUNT(totalRounds) from persistantgamedata");
				ResultSet gamesPlayed = getGamesPlayed.executeQuery();
				if(gamesPlayed.next()) {
					allGames = gamesPlayed.getInt(1);
//					System.out.println("games played = " + allGames);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return allGames;
		}
		
		//retrieves the number of human wins from the DB
		public int humanWins() {
			int humanWins= 0;
			try {
				Connection connection = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
				PreparedStatement getHumanWins = connection.prepareStatement("SELECT COUNT (winner) from persistantgamedata WHERE winner IS true");
				ResultSet numOfHumanWins = getHumanWins.executeQuery();
				if(numOfHumanWins.next()) {
					humanWins = numOfHumanWins.getInt(1);
//					System.out.println("Number of human wins = " + humanWins);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return humanWins;
		}
		
		//retrieves the number of AI wins from the DB
		public int AIWins() {
			int AIWins = 0;
			try {
				Connection connection = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
				PreparedStatement getAIWins = connection.prepareStatement("SELECT COUNT (winner) from persistantgamedata WHERE winner IS false");
				ResultSet numOfAIWins = getAIWins.executeQuery();
				if(numOfAIWins.next()) {
					AIWins = numOfAIWins.getInt(1);
//					System.out.println("number of AI wins = " + AIWins);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return AIWins;
		}
		
		//Retrieves the number of sum of all drawn rounds and the number of games played from the DB
		//and calculates the avg number of draws per game
		public double avgDraws() {
			double avgDraws = 0;
			int totalDraws = 0;
			int totalGames = 0;
			try {
				Connection connection = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
				PreparedStatement getDraws = connection.prepareStatement("SELECT SUM(totaldraws) FROM persistantgamedata");
				ResultSet allDraws = getDraws.executeQuery();
				if(allDraws.next()) {
					totalDraws = allDraws.getInt(1);
//					System.out.println("total draws ever = " + totalDraws);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			totalGames = gamesPlayed();
			avgDraws = totalDraws/totalGames;
//			System.out.println("Average Draws = " + avgDraws);
			return avgDraws;
		}
		
		//retrieves the number of rounds in the longest game played from the DB
		public int longestGame() {
			int longestGame = 0;
			try {
				Connection connection = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
				PreparedStatement getLongestGame = connection.prepareStatement("SELECT MAX(totalRounds) FROM persistantgamedata");
				ResultSet mostRounds = getLongestGame.executeQuery();
				if(mostRounds.next()) {
					longestGame = mostRounds.getInt(1);
//					System.out.println("The longest game ever = " + longestGame);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return longestGame;
		}
}
