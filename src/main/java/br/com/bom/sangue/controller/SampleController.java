package br.com.bom.sangue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import br.com.bom.sangue.service.Teste;

@RestController
@RequestMapping("/api")
@EnableAutoConfiguration
public class SampleController {
	
	//@Autowired
	Teste teste = new Teste();

    @RequestMapping("/paulete={viado}")
    @ResponseBody
    String home(@PathVariable("viado") String isPauleteViado) {
    	
    	return teste.teste(isPauleteViado);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}