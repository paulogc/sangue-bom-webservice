package br.com.bom.sangue.dao;

import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.Telephone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelephoneDAO {

    private String insertQuery = "INSERT INTO telephone (number, ddi, ddd, type) VALUES (?, ?, ?, ?)";
    
    private String findOneByIdQuery = "SELECT * FROM Telephone WHERE id = ?";
    
    private String updateQuery = "UPDATE telephone SET number = ?, ddi = ?, ddd = ?, type = ? WHERE id = ?";
    
    private String deleteQuery = "DELETE FROM telephone WHERE id = ?";

    public Telephone create(Telephone telephone) throws ClassNotFoundException, SQLException {

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertQuery);

        statement = createStatment(statement, telephone);

        statement.execute();

        statement.close();

        return telephone;
    }

    public Telephone findOneById(Long id) throws  ClassNotFoundException, SQLException {
        Telephone telephone = new Telephone();

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statment = connection.prepareStatement(findOneByIdQuery);

        statment.setLong(1, id);

        ResultSet result  = statment.executeQuery();

        while (result.next()){
            telephone.setId(result.getLong("id"));
            telephone.setNumber(result.getString("number"));
            telephone.setDdi(result.getInt("ddi"));
            telephone.setDdd(result.getInt("ddd"));
            telephone.setType(result.getString("type"));
        }

        statment.close();

        return telephone;
    }

    public Telephone update(Telephone telephone) throws ClassNotFoundException, SQLException {

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(updateQuery);

        statement = createStatment(statement, telephone);
        statement.setLong(5, telephone.getId());

        statement.executeUpdate();

        statement.close();

        return telephone;
    }

    public void delete(long id) throws ClassNotFoundException, SQLException {

        DatabaseConnection dataBase = DatabaseConnection.getInstance();
        Connection connection = dataBase.getConnection();

        PreparedStatement statement = connection.prepareStatement(deleteQuery);

        statement.setLong(1, id);

        statement.execute();

        statement.close();
    }

    private PreparedStatement createStatment(PreparedStatement statement, Telephone telephone) throws  SQLException {
        statement.setString(1, telephone.getNumber());
        statement.setInt(2, telephone.getDdi());
        statement.setInt(3, telephone.getDdd());
        statement.setString(4, telephone.getType());

        return statement;
    }
}
