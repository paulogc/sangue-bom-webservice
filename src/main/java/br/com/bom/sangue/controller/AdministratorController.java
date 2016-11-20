 package br.com.bom.sangue.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bom.sangue.entities.Administrator;
import br.com.bom.sangue.entities.BloodDonator;
import br.com.bom.sangue.service.AdministratorService;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
	
	AdministratorService administratorService = new AdministratorService();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorController.class);
	
	@RequestMapping
    @ResponseBody
    public Administrator create (@RequestBody Administrator administrator) throws ClassNotFoundException, SQLException {
        return (administratorService.create(administrator));
    }

    @GetMapping(value="/{id}")
    @ResponseBody
    public Administrator finOneById (@PathVariable("id") long id)
            throws ClassNotFoundException, SQLException {
        return (administratorService.findOneById(id));
    }
	
    @PutMapping("/{id}")
    @ResponseBody
    public Administrator update (@PathVariable("id") Long id, @RequestBody Administrator administrator) throws
            ClassNotFoundException, SQLException {
        return (administratorService.update(administrator));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String delete (@PathVariable("id") Long id, @RequestBody Administrator administrator) throws ClassNotFoundException, SQLException {
        administratorService.delete(administrator);

        return("Successfuly deleted");
    }
    
    @GetMapping(value = "/login/{email}&{password}")
    public Boolean login (@PathVariable("email") String email, @PathVariable("password") String password) throws ClassNotFoundException, SQLException {
    	LOGGER.info("Email: {}", email);
    	LOGGER.info("Senha: {}", password);
    	
    	return administratorService.login(email, password);
    }
    
}
