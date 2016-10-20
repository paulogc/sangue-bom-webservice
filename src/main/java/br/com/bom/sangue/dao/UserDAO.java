package br.com.bom.sangue.dao;


import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.Address;
import br.com.bom.sangue.entities.User;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.flywaydb.core.internal.util.DateUtils;

public class UserDAO {

    private String insertQuery = "INSERT INTO user (name, email, birthdate, address_id) VALUES(?, ?, ?, ?)";

    private String findOneByIdQuery = "SELECT * FROM user WHERE id = ?";

    private String updateQuery = "UPDATE user SET name = ?, email = ?, birthdate = ?, address_id = ? WHERE id = ?";

    private String deleteQuery = "DELETE FROM user WHERE id = ?";
    
    private String findLastInsertedQuery = "SELECT MAX(id) AS id FROM user";

    public User create(User user) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertQuery);

        statement = createStatement(statement, user);

        statement.execute();

        statement.close();
        
        user.setId(findLastInserted());

        return user;
    }

    public User findOneById(Long id) throws ClassNotFoundException, SQLException {
        User user = new User();
        Address address = new Address();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(findOneByIdQuery);

        statement.setLong(1, id);

        ResultSet result  = statement.executeQuery();

        while (result.next()) {
            user.setId(result.getLong("id"));
            user.setName(result.getString("name"));
            user.setEmail(result.getString("email"));
            user.setBirthdate(result.getDate("birthdate"));
            address.setId(result.getLong("address_id"));
            user.setAddress(address);
        }

        statement.close();

        return user;
    }

    public User update(User user) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(updateQuery);

        statement = createStatement(statement, user);
        statement.setLong(5, user.getId());

        statement.executeUpdate();

        statement.close();

        return user;
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(deleteQuery);

        statement.setLong(1, id);

        statement.execute();
    }

    private PreparedStatement createStatement(PreparedStatement statement, User user) throws SQLException {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String birthdate = dateFormat.format(user.getBirthdate());
    	    	
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, birthdate);
        statement.setLong(4, user.getAddress().getId());

        return statement;
    }
    
    private Long findLastInserted () throws ClassNotFoundException, SQLException {
    	User user = new User();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statment = connection.prepareStatement(findLastInsertedQuery);

        ResultSet result  = statment.executeQuery();

        result.next();
        
        user.setId(result.getLong("id"));

         statment.close();

         return user.getId();
    	
    }

}
