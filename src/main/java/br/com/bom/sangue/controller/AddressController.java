package br.com.bom.sangue.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bom.sangue.entities.Address;
import br.com.bom.sangue.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	AddressService addressService = new AddressService();
	
	@PostMapping
	@ResponseBody
	public Address create(@RequestBody Address address) throws ClassNotFoundException, SQLException {
		return addressService.create(address);
	}
	

}
