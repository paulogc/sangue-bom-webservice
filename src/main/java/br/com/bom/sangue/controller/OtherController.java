package br.com.bom.sangue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/other")
public class OtherController {
	
	@GetMapping("/teste")
    @ResponseBody
    String home2() {
    	
    	return "Dois teste";
    }

}
