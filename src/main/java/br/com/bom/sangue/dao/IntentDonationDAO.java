package br.com.bom.sangue.dao;


import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.BloodDonator;
import br.com.bom.sangue.entities.IntentDonation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IntentDonationDAO {
    private String insertQuery = "INSERT INTO intent_donation (created_date, grant_date, active, blood_donator_id) " +
            "VALUES(?, ?, ?, ?)";

    private String findOneByIdQuery = "SELECT * FROM intent_donation WHERE id = ?";

    private String findAllIntentDonationQuery = "SELECT * FROM intent_donation";

    private String updateQuery = "UPDATE intent_donation SET created_date = ?, grant_date = ?, active = ?, " +
            "blood_donator_id = ? WHERE id = ?";

    private String deleteQuery = "DELETE FROM intent_donation WHERE id = ?";

    public IntentDonation create(IntentDonation intentDonation) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertQuery);
        statement = createStatment(statement, intentDonation);

        statement.execute();
        statement.close();

        return intentDonation;
    }

    public IntentDonation findOneById(Long id) throws ClassNotFoundException, SQLException {
        IntentDonation intentDonation = new IntentDonation();
        BloodDonator bloodDonator = new BloodDonator();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(findOneByIdQuery);

        statement.setLong(1, id);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            intentDonation.setId(result.getLong("id"));
            intentDonation.setCreatedDate(result.getDate("created_date"));
            intentDonation.setGrantDate(result.getDate("grant_date"));
            intentDonation.setActive(result.getBoolean("active"));
            bloodDonator.setId(result.getLong("blood_donator_id"));
            intentDonation.setBloodDonator(bloodDonator);
        }

        statement.close();

        return intentDonation;
    }

    public List<IntentDonation> findAllIntentDonation() throws ClassNotFoundException, SQLException {
        List<IntentDonation> intentDonationList = new ArrayList<>();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(findAllIntentDonationQuery);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            IntentDonation intentDonation = new IntentDonation();
            BloodDonator bloodDonator = new BloodDonator();

            intentDonation.setId(result.getLong("id"));
            intentDonation.setCreatedDate(result.getDate("created_date"));
            intentDonation.setGrantDate(result.getDate("grant_date"));
            intentDonation.setActive(result.getBoolean("active"));
            bloodDonator.setId(result.getLong("blood_donator_id"));
            intentDonation.setBloodDonator(bloodDonator);

            intentDonationList.add(intentDonation);
        }

        return intentDonationList;
    }

    public IntentDonation update(IntentDonation intentDonation) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(updateQuery);

        statement = createStatment(statement, intentDonation);
        statement.setLong(5, intentDonation.getId());

        statement.close();

        return intentDonation;
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(deleteQuery);

        statement.setLong(1, id);

        statement.execute();

        statement.close();
    }

    private PreparedStatement createStatment(PreparedStatement statement, IntentDonation intentDonation) throws SQLException {
        statement.setDate(1, (Date) intentDonation.getCreatedDate());
        statement.setDate(2, (Date) intentDonation.getGrantDate());
        statement.setBoolean(3, intentDonation.getActive());
        statement.setLong(4, intentDonation.getBloodDonator().getId());

        return statement;
    }
}
