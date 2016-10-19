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

    private String insertQuery = "INSERT INTO bood_donator (blood_type, blood_factor, cpf, nick_name, user_id)" +
            " VALUES ?, ?, ?, ?, ?";

    private String findOneById = "SELECT * FROM bood_donator WHERE id = ?";

    private String updateQuery = "UPDATE bood_donator SET blood_type = ?, blood_factor = ?, cpf = ? nick_name = ?, " +
            "user_id = ?";

    private String delete = "DELETE FROM bood_donator WHERE id = ?";

    public BloodDonator create(BloodDonator bloodDonator, User user) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertQuery);

        statement = createStatement(statement, bloodDonator, user);

        statement.execute();

        statement.close();

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
            bloodDonator.setNickName(result.getString("nick_name"));
            bloodDonator.setId(result.getLong("id"));
        }

        statement.close();

        return bloodDonator;
    }

    private PreparedStatement createStatement(PreparedStatement statement, BloodDonator bloodDonator, User user) throws SQLException {
        statement.setString(1, bloodDonator.getBloodType());
        statement.setString(2, bloodDonator.getBloodFactor());
        statement.setString(3, bloodDonator.getCPF());
        statement.setString(4, bloodDonator.getNickName());
        statement.setLong(5, user.getId());

        return statement;
    }
}
