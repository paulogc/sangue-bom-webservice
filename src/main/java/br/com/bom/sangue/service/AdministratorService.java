package br.com.bom.sangue.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bom.sangue.dao.AdministratorDAO;
import br.com.bom.sangue.entities.Administrator;
import br.com.bom.sangue.entities.User;

public class AdministratorService {
	
	AdministratorDAO administratorDAO = new AdministratorDAO();
	
	UserService userService = new UserService();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorService.class);
	
	public Administrator create (Administrator administrator) throws ClassNotFoundException, SQLException {	
		LOGGER.info("Saving administrator in database");
		
		User user = new User(administrator.getName(), administrator.getEmail(), administrator.getBirthdate());
		
		user = userService.create(user);
		
		return administratorDAO.create(administrator, user);
	}
	
}
