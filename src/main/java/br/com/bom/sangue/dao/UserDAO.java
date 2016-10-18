package br.com.bom.sangue.dao;


import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.Address;
import br.com.bom.sangue.entities.Telephone;
import br.com.bom.sangue.entities.User;

import java.sql.*;

public class UserDAO {
    private String insertQuery = "INSERT INTO user (name, email, birthdate, address_id) VALUES(?, ?, ?, ?)";
    private String findOneByIdQuery = "SELECT * FROM user WHERE id = ?";
    private String updateQuery = "UPDATE user SET name = ?, email = ?, birthdate = ?, address_is = ? WHERE id = ?";
    private String deleteQuery = "DELETE FROM user WHERE id = ?";

    public User creste(User user) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertQuery);

        statement = createStatement(statement, user);

        statement.execute();

        statement.close();

        return user;
    }

    public User findOneById(Long id) throws ClassNotFoundException, SQLException {
        User user = new User();
        Address address = new Address();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statment = connection.prepareStatement(findOneByIdQuery);

        statment.setLong(1, id);

        ResultSet result  = statment.executeQuery();

        while (result.next()) {
            user.setName(result.getString("name"));
            user.setEmail(result.getString("email"));
            user.setBirthdate(result.getDate("birthdate"));
            address.setId(result.getLong("address_id"));
            user.setAddress(address);
        }

        statment.close();

        return user;
    }

    private User update(User user) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(updateQuery);

        statement = createStatement(statement, user);
        statement.setLong(5, user.getId());

        statement.executeUpdate();

        statement.close();

        return user;
    }

    private void delete(Long id) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(deleteQuery);

        statement.setLong(1, id);

        statement.execute();
    }

    private PreparedStatement createStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setDate(3, (Date) user.getBirthdate());
        statement.setLong(4, user.getAddress().getId());

        return statement;
    }

}
