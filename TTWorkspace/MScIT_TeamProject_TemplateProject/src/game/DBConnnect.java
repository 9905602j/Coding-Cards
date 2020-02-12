//package game;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConnnect {
//
//	public static Connection connectToDB(){
//		Connection connection = null;
//		
//		try {
//			Class.forName("org.postgresql.Driver");
//			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432","m_19_2454015h", "2454015h");
//			connection.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return connection;
//	}
//}
