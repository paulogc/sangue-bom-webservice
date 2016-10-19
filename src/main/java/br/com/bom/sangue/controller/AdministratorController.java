package br.com.bom.sangue.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bom.sangue.entities.Administrator;
import br.com.bom.sangue.service.AdministratorService;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
	
	AdministratorService administratorService = new AdministratorService();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorController.class);
	
	@PostMapping
	@ResponseBody
	public Administrator create (@RequestBody Administrator administrator) throws ClassNotFoundException, SQLException {
		return administratorService.create(administrator);
	}
	
}
