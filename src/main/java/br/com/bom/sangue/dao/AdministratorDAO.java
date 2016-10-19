package br.com.bom.sangue.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.Address;
import br.com.bom.sangue.entities.Administrator;
import br.com.bom.sangue.entities.User;

public class AdministratorDAO {
	
	private String insertQuery = "INSERT INTO administrator (password, user_id) VALUES (?, ?)";
	
	private String findOneByIdQuery = "SELECT * FROM administrator WHERE id = ?";
	
	private String updateQuery = "UPDATE administrator SET password = ? WHERE id = ?";

    private String deleteQuery = "DELETE FROM administrator WHERE id = ?";
    
    private String findLastInsertedQuery = "SELECT MAX(id) FROM administrator";
	
	public Administrator create (Administrator administrator, User user) throws ClassNotFoundException, SQLException {
		DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertQuery);

        statement = createStatement(statement, administrator, user);

        statement.execute();

        statement.close();
        
        administrator.setId(findLastInserted());
                
        return administrator;
	}
	
	public Administrator findOneById(Long id) throws ClassNotFoundException, SQLException {
		Administrator administrator = new Administrator();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(findOneByIdQuery);

        statement.setLong(1, id);

        ResultSet result  = statement.executeQuery();

        while (result.next()) {
        	administrator.setPassword(result.getString("name"));
        	administrator.setId(result.getLong("id"));
        }

        statement.close();

        return administrator;
    } 
	
	public Administrator update(Administrator administrator) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();
        
        //tem cagada
        User user = new User();

        PreparedStatement statement = connection.prepareStatement(updateQuery);
        
        //tem cagada
        statement = createStatement(statement, administrator, user);
        statement.setLong(2, administrator.getId());

        statement.executeUpdate();

        statement.close();

        return administrator;
    }
	
    public void delete(Long id) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(deleteQuery);

        statement.setLong(1, id);

        statement.execute();
    }
	
	private PreparedStatement createStatement(PreparedStatement statement, Administrator administrator, User user) throws SQLException {
        statement.setString(1, administrator.getPassword());
        statement.setLong(2, user.getId());
        
        return statement;
    }
	
	private Long findLastInserted () throws ClassNotFoundException, SQLException {
    	Administrator administrator = new Administrator();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statment = connection.prepareStatement(findLastInsertedQuery);

        ResultSet result  = statment.executeQuery();

        administrator.setId(result.getLong("id"));

         statment.close();

         return administrator.getId();
    	
    }
	
}
