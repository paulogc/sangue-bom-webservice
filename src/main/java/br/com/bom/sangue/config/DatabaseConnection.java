package br.com.bom.sangue.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection	 {
		
	private String driver = "com.mysql.jdbc.Driver";
	
	private String url = "jdbc:mysql://localhost:3306/sangue_bom_db";
	
	private String username = "root";
	
	private String password = "rafa1993";

	private static Connection CONNECTION;
	
	private static DatabaseConnection INSTANCE;
	
	private DatabaseConnection () throws SQLException, ClassNotFoundException {	
		Class.forName(driver);
	    CONNECTION = DriverManager.getConnection(url, username, password);
	}
	
	public static synchronized DatabaseConnection getInstance() throws ClassNotFoundException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new DatabaseConnection();
		}
		return INSTANCE;
	}
	
	public Connection getConnection() {
		return CONNECTION;
	}
   
}

