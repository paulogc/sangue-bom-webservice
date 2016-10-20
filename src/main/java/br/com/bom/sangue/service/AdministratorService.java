package br.com.bom.sangue.service;


import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bom.sangue.dao.AdministratorDAO;
import br.com.bom.sangue.entities.Address;
import br.com.bom.sangue.entities.Administrator;
import br.com.bom.sangue.entities.BloodDonator;
import br.com.bom.sangue.entities.User;

public class AdministratorService {
	
	UserService userService = new UserService();
	
	AdministratorDAO administratorDAO = new AdministratorDAO();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorService.class);
	
	public Administrator create (Administrator administrator) throws ClassNotFoundException, SQLException {	
		LOGGER.info("Saving administrator in database");
		
		LOGGER.info("> Id {}", administrator.getId());
        LOGGER.info("> Password {}", administrator.getPassword());


        User user = new User(administrator.getName(), administrator.getEmail(),
                administrator.getBirthdate(), administrator.getAddress());

        user = userService.create(user);

		return administratorDAO.create(administrator, user);
	}
	
	public Administrator findOneById (Long id) throws ClassNotFoundException, SQLException{
		LOGGER.info("Getting Administrator by id");
	    Administrator administrator;
	    User user = new User();
		
        administrator = administratorDAO.findOneById(id);
        user = userService.findOneById(id);
	    
	    administrator = new Administrator(user.getId(), 
	    		user.getName(), 
	    		user.getEmail(), 
	    		user.getBirthdate(),
	    		user.getAddress(), 
	    		administrator.getPassword()
	    		);
	    
		return administrator;
	}
	
	 public Administrator update(Administrator administrator) throws ClassNotFoundException, SQLException {
	        LOGGER.info("Updating Administrator");

	        LOGGER.info("> Password {}", administrator.getPassword());
	        
	        User user = new User(administrator.getId(), administrator.getName(), administrator.getEmail(),
	        		administrator.getBirthdate(), administrator.getAddress());

	        user = userService.update(user);

	        administrator = administratorDAO.update(administrator);

	        return administrator;
	    }

	    public void delete(Administrator administrator) throws ClassNotFoundException, SQLException {
	        LOGGER.info("Deleting Administrator");

	        User user = new User(administrator.getId(), administrator.getName(), administrator.getEmail(),
	        		administrator.getBirthdate(), administrator.getAddress());

	        administratorDAO.delete(administrator.getId());

	        userService.delete(user);
	    }
	
}
