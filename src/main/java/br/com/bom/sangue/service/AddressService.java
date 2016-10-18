package br.com.bom.sangue.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bom.sangue.dao.AddressDAO;
import br.com.bom.sangue.entities.Address;

/**
 * Created by paulo on 02/10/16.
 */
public class AddressService {
	
	AddressDAO addressDAO = new AddressDAO();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

	
	public Address create(Address address) throws ClassNotFoundException, SQLException {
		LOGGER.info("Saving address in database");
		
		LOGGER.info("> Street {}", address.getStreet());
		LOGGER.info("> Number {}", address.getNumber());
		LOGGER.info("> Neighborhood {}", address.getNeighborhood());
		LOGGER.info("> Cep {}", address.getCep());
		LOGGER.info("> Complement {}", address.getComplement());
		LOGGER.info("> City {}", address.getCity());
		LOGGER.info("> State {}", address.getState());
		LOGGER.info("> Latitude {}", address.getLatitude());
		LOGGER.info("> Longitude {}", address.getLongitude());
		
		return addressDAO.create(address);
	}
	
}
