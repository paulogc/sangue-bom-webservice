package br.com.bom.sangue.dao;


import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.Address;
import br.com.bom.sangue.entities.Telephone;
import br.com.bom.sangue.entities.User;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UserDAO {

    private String insertQuery = "INSERT INTO user (name, email, birth_date, address_id, telephone_id)" +
            " VALUES(?, ?, ?, ?, ?)";

    private String findOneByIdQuery = "SELECT * FROM user WHERE id = ?";
    
    private String findOneByEmail = "SELECT * FROM user WHERE email = ?";

    private String updateQuery = "UPDATE user SET name = ?, email = ?, birth_date = ?," +
            " address_id = ?, telephone_id = ? WHERE id = ?";

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
        Telephone telephone = new Telephone();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(findOneByIdQuery);

        statement.setLong(1, id);

        ResultSet result  = statement.executeQuery();

        while (result.next()) {
            user.setId(result.getLong("id"));
            user.setName(result.getString("name"));
            user.setEmail(result.getString("email"));
            user.setBirthDate(result.getDate("birth_date"));

            address.setId(result.getLong("address_id"));
            telephone.setId(result.getLong("telephone_id"));

            user.setAddress(address);
            user.setTelephone(telephone);
        }

        statement.close();

        return user;
    }
    
    public User findOneByEmail(String email) throws ClassNotFoundException, SQLException {
        User user = new User();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(findOneByEmail);

        statement.setString(1, email);

        ResultSet result  = statement.executeQuery();

        while (result.next()) {
            user.setId(result.getLong("id"));
            user.setName(result.getString("name"));
            user.setEmail(result.getString("email"));
            user.setBirthDate(result.getDate("birth_date"));
        }

        statement.close();

        return user;
    }

    public User update(User user) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(updateQuery);

        statement = createStatement(statement, user);
        statement.setLong(6, user.getId());

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
    	String birthDate = dateFormat.format(user.getBirthDate());
    	    	
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, birthDate);
        statement.setLong(4, user.getAddress().getId());
        statement.setLong(5, user.getTelephone().getId());

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
