package br.com.bom.sangue.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.Administrator;
import br.com.bom.sangue.entities.User;

public class AdministratorDAO {
	
	private String insertQuery = "INSERT INTO administrator (password, user_id) VALUES (?, ?)";
	
	private String findLastInsertedQuery = "SELECT MAX(id) FROM administrator";
	
	public Administrator create (Administrator administrator, User user) throws ClassNotFoundException, SQLException {
		DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertQuery);

        statement = createStatement(statement, administrator, user);

        statement.execute();

        statement.close();

        return administrator;
	}
	
	private PreparedStatement createStatement(PreparedStatement statement, Administrator administrator, User user) throws SQLException {
        statement.setString(1, administrator.getPassword());
        statement.setLong(2, user.getId());
        
        return statement;
    }
	
}
