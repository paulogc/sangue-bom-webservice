package br.com.bom.sangue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.Address;

public class AddressDAO {
	
	private String insertQuery = "INSERT INTO address (street, number, neighborhood, cep, complement, city, state," +
			" latitude, longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private String updateQuery = "UPDATE address SET street = ?, number = ?, neighborhood = ?, cep = ?," +
			" complement = ?, city = ?, state = ?, latitude = ?, longitude = ? WHERE id = ?";
	
	private String findOneByIdQuery = "SELECT * FROM address WHERE id = ?";
	
	private String deleteQuery = "DELETE FROM address WHERE id = ?";

	private String getLastInsertId = "SELECT MAX(id) AS id FROM address";
	
	public Address create(Address address) throws ClassNotFoundException, SQLException{
		DatabaseConnection database = DatabaseConnection.getInstance();
		Connection connection = database.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(insertQuery);
		
		statement = createStatement(statement, address);
		
		statement.execute();

		statement.close();

		address.setId(findLastInsert());
		
		return address;
	}
	
	public Address update (Address address) throws ClassNotFoundException, SQLException {		
		DatabaseConnection database = DatabaseConnection.getInstance();
		Connection connection = database.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(updateQuery);
		
		statement = createStatement(statement, address);
		
		statement.setLong(10, address.getId());
		
		statement.executeUpdate();

		statement.close();
		
		return address;
	}
	
	public Address findOneById(Long id) throws ClassNotFoundException, SQLException {
		Address address = new Address();
		
		DatabaseConnection database = DatabaseConnection.getInstance();
		Connection connection = database.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(findOneByIdQuery);
		
		statement.setLong(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			address.setId(resultSet.getLong("id"));
			address.setStreet(resultSet.getString("street"));
			address.setNumber(resultSet.getInt("number"));
			address.setNeighborhood(resultSet.getString("neighborhood"));
			address.setCep(resultSet.getString("cep"));
			address.setComplement(resultSet.getString("complement"));
			address.setCity(resultSet.getString("city"));
			address.setState(resultSet.getString("state"));
			address.setLatitude(resultSet.getDouble("latitude"));
			address.setLongitude(resultSet.getDouble("longitude"));;
		}
		
		statement.close();
		
		return address;		
	}
	
	public void delete (Long id) throws ClassNotFoundException, SQLException {
		DatabaseConnection database = DatabaseConnection.getInstance();
		Connection connection = database.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(deleteQuery);
		
		statement.setLong(1, id);
		
		statement.execute();
		
		statement.close();	
	}
	
	private PreparedStatement createStatement (PreparedStatement statement, Address address) throws SQLException {
		statement.setString(1, address.getStreet());
		statement.setInt(2, address.getNumber());
		statement.setString(3, address.getNeighborhood());
		statement.setString(4, address.getCep());
		statement.setString(5, address.getComplement());
		statement.setString(6, address.getCity());
		statement.setString(7, address.getState());
		statement.setDouble(8, address.getLatitude());
		statement.setDouble(9, address.getLongitude());
		
		return statement;
	}

	private Long findLastInsert() throws ClassNotFoundException, SQLException {
		Address address = new Address();
		
		DatabaseConnection database = DatabaseConnection.getInstance();
		Connection connection = database.getConnection();

		PreparedStatement statement = connection.prepareStatement(getLastInsertId);

		ResultSet result = statement.executeQuery();

		result.next();
		
		address.setId(result.getLong("id"));
		
		statement.close();

		return address.getId();
	}

}
