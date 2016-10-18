package br.com.bom.sangue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.bom.sangue.config.DatabaseConnection;
import br.com.bom.sangue.entities.Address;

public class AddressDAO {
	
	private String updateQuery = "INSERT INTO address (street, number, neighborhood, cep, complement, city, state, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public Address create(Address address) throws ClassNotFoundException, SQLException{
		
		DatabaseConnection database = DatabaseConnection.getInstance();
		Connection connection = database.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(updateQuery);
		
		statement.setString(1, address.getStreet());
		statement.setInt(2, address.getNumber());
		statement.setString(3, address.getNeighborhood());
		statement.setString(4, address.getCep());
		statement.setString(5, address.getComplement());
		statement.setString(6, address.getCity());
		statement.setString(7, address.getState());
		statement.setDouble(8, address.getLatitude());
		statement.setDouble(9, address.getLongitude());
		
		statement.execute();
		
		return address;
	}

}
