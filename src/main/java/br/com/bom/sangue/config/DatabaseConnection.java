package br.com.bom.sangue.config;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {
	
	@Value("${jdbc.driver}")
	private String driver;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
		
	public static DatabaseConnection DATABASE_CONNECTION;
	
	private DatabaseConnection () throws SQLException, ClassNotFoundException {	
		Class.forName(driver);
	    DATABASE_CONNECTION = (DatabaseConnection) DriverManager.getConnection(url, username, password);
	}
	
	public static synchronized DatabaseConnection getConnection() throws ClassNotFoundException, SQLException {
		if (DATABASE_CONNECTION == null) {
			DATABASE_CONNECTION = new DatabaseConnection();
		}
		return DATABASE_CONNECTION;
	}	

   /*public void connection () throws ClassNotFoundException {
   
	   try{
		 //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      con = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = (Statement) con.createStatement();
	      String sql;
	      sql = "SELECT id, first, last, age FROM Employees";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("id");
	         int age = rs.getInt("age");
	         String first = rs.getString("first");
	         String last = rs.getString("last");

	         //Display values
	         System.out.print("ID: " + id);
	         System.out.print(", Age: " + age);
	         System.out.print(", First: " + first);
	         System.out.println(", Last: " + last);
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      con.close();
		   
	   } catch (SQLException e) {
		   System.out.println(e);
	   } catch (Exception e) {
		   System.out.println(e);
	   }
   }*/
   
}

