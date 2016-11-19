package br.com.bom.sangue.dao;


import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.BloodDonator;
import br.com.bom.sangue.entities.IntentDonation;
import br.com.bom.sangue.entities.News;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class IntentDonationDAO {
    private String insertQuery = "INSERT INTO intent_donation (created_at, status, grant_date , blood_donator_id) " +
            "VALUES(?, ?, ?, ?)";

    private String findAllByUserId = "SELECT * FROM intent_donation WHERE blood_donator_id = ?";

    private String findAllIntentDonationQuery = "SELECT * FROM intent_donation";
    
    private String findByNeighborhoodQuery = "SELECT i.id, i.created_at, i.status, i.grant_date, i.blood_donator_id "
    		+ "FROM intent_donation i INNER JOIN blood_donator bd ON i.blood_donator_id = bd.user_id INNER JOIN user u ON "
    		+ "bd.user_id = u.id INNER JOIN address ad ON u.address_id = ad.id AND ad.neighborhood = ?";

    private String updateQuery = "UPDATE intent_donation SET status = ?, grant_date = ? WHERE id = ?";

    private String deleteQuery = "DELETE FROM intent_donation WHERE id = ?";

    private String findLastInsertedQuery = "SELECT MAX(id) AS id FROM intent_donation";

    public IntentDonation create(IntentDonation intentDonation) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertQuery);
        statement = createStatment(statement, intentDonation);

        statement.execute();
        statement.close();

        intentDonation.setId(findLastInserted());

        return intentDonation;
    }

    public List<IntentDonation> findAllByBloodDonatorId(Long id) throws ClassNotFoundException, SQLException {
        List<IntentDonation> intentDonations = new ArrayList<>();
        IntentDonation intentDonation;
        BloodDonator bloodDonator;

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(findAllByUserId);

        statement.setLong(1, id);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            intentDonation = new IntentDonation();
            bloodDonator = new BloodDonator();

            intentDonation.setId(result.getLong("id"));
            intentDonation.setCreatedAt(result.getDate("created_at"));
            intentDonation.setStatus(result.getInt("status"));
            intentDonation.setGrantDate(result.getDate("grant_date"));
            bloodDonator.setId(result.getLong("blood_donator_id"));
            intentDonation.setBloodDonator(bloodDonator);

            intentDonations.add(intentDonation);
        }

        statement.close();

        return intentDonations;
    }

    public List<IntentDonation> findAllIntentDonation() throws ClassNotFoundException, SQLException {
        List<IntentDonation> intentDonationList = new ArrayList<>();
        IntentDonation intentDonation;
        BloodDonator bloodDonator;

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(findAllIntentDonationQuery);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            intentDonation = new IntentDonation();
            bloodDonator = new BloodDonator();

            intentDonation.setId(result.getLong("id"));
            intentDonation.setCreatedAt(result.getDate("created_at"));
            intentDonation.setGrantDate(result.getDate("grant_date"));
            intentDonation.setStatus(result.getInt("status"));
            bloodDonator.setId(result.getLong("blood_donator_id"));
            intentDonation.setBloodDonator(bloodDonator);

            intentDonationList.add(intentDonation);
        }

        return intentDonationList;
    }

    public List<IntentDonation> findByNeighborhood(String neighborhood) throws ClassNotFoundException, SQLException {
        List<IntentDonation> intentDonationList = new ArrayList<>();
        IntentDonation intentDonation;
        BloodDonator bloodDonator;

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(findByNeighborhoodQuery);
        
        statement.setString(1, neighborhood);
        
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            intentDonation = new IntentDonation();
            bloodDonator = new BloodDonator();

            intentDonation.setId(result.getLong("id"));
            intentDonation.setCreatedAt(result.getDate("created_at"));
            intentDonation.setGrantDate(result.getDate("grant_date"));
            intentDonation.setStatus(result.getInt("status"));
            bloodDonator.setId(result.getLong("blood_donator_id"));
            intentDonation.setBloodDonator(bloodDonator);

            intentDonationList.add(intentDonation);
        }

        return intentDonationList;
    }
    
    public IntentDonation update(IntentDonation intentDonation) throws ClassNotFoundException, SQLException {
        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String grantDate = dateFormat.format(intentDonation.getGrantDate());

        PreparedStatement statement = connection.prepareStatement(updateQuery);

        statement.setInt(1, intentDonation.getStatus());
        statement.setString(2, grantDate);
        statement.setLong(3, intentDonation.getId());

        statement.executeUpdate();

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

    private PreparedStatement createStatment(PreparedStatement statement, IntentDonation intentDonation)
            throws SQLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String createdAt = dateFormat.format(intentDonation.getCreatedAt());
        
        if (intentDonation.getGrantDate() != null) {
        	String grantDate = dateFormat.format(intentDonation.getGrantDate());	
        	statement.setString(3, grantDate);
        } else {
        	statement.setString(3, null);
        }
  
        statement.setString(1, createdAt);
        statement.setInt(2, intentDonation.getStatus());
        
        statement.setLong(4, intentDonation.getBloodDonator().getId());

        return statement;
    }

    private Long findLastInserted () throws ClassNotFoundException, SQLException {
        IntentDonation intentDonation = new IntentDonation();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(findLastInsertedQuery);

        ResultSet result  = statement.executeQuery();

        result.next();

        intentDonation.setId(result.getLong("id"));

        statement.close();

        return intentDonation.getId();

    }
}
