package br.com.bom.sangue.dao;

import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.Administrator;
import br.com.bom.sangue.entities.BloodDonator;
import br.com.bom.sangue.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BloodDonatorDAO {

    private String insertQuery = "INSERT INTO blood_donator (blood_type, blood_factor, cpf, nickname, user_id)" +
            " VALUES (?, ?, ?, ?, ?)";

    private String findOneById = "SELECT * FROM bood_donator WHERE id = ?";

    private String updateQuery = "UPDATE bood_donator SET blood_type = ?, blood_factor = ?, cpf = ? nickname = ?, " +
            "user_id = ? WHERE id = ?";

    private String deleteQuery = "DELETE FROM bood_donator WHERE id = ?";

    private String findLastInsertedQuery = "SELECT MAX(user_id) AS id FROM blood_donator";

    public BloodDonator create(BloodDonator bloodDonator, User user) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertQuery);

        statement = createStatement(statement, bloodDonator, user);

        statement.execute();

        statement.close();

        bloodDonator.setId(findLastInserted());

        return bloodDonator;
    }

    public BloodDonator findOneById(Long id) throws ClassNotFoundException, SQLException {
        BloodDonator bloodDonator = new BloodDonator();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(findOneById);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            bloodDonator.setBloodType(result.getString("blood_type"));
            bloodDonator.setBloodFactor(result.getString("blood_factor"));
            bloodDonator.setCPF(result.getString("cpf"));
            bloodDonator.setNickname(result.getString("nickname"));
            bloodDonator.setId(result.getLong("user_id"));
        }

        statement.close();

        return bloodDonator;
    }

    public BloodDonator update(BloodDonator bloodDonator) throws ClassNotFoundException, SQLException {

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(updateQuery);

        statement = createStatement(statement, bloodDonator, bloodDonator);

        statement.executeUpdate();

        statement.close();

        return bloodDonator;
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(deleteQuery);

        statement.setLong(1, id);

        statement.execute();

        statement.close();
    }

    private Long findLastInserted () throws ClassNotFoundException, SQLException {
        BloodDonator bloodDonator = new BloodDonator();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statment = connection.prepareStatement(findLastInsertedQuery);

        ResultSet result  = statment.executeQuery();

        while (result.next()) {
            bloodDonator.setId(result.getLong("id"));
        }
        statment.close();

        return bloodDonator.getId();

    }

    private PreparedStatement createStatement(PreparedStatement statement, BloodDonator bloodDonator, User user) throws SQLException {
        statement.setString(1, bloodDonator.getBloodType());
        statement.setString(2, bloodDonator.getBloodFactor());
        statement.setString(3, bloodDonator.getCPF());
        statement.setString(4, bloodDonator.getNickname());
        statement.setLong(5, user.getId());

        return statement;
    }
}
