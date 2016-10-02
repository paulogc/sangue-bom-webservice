package br.com.bom.sangue.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bom.sangue.entities.TesteEntitie;
import br.com.bom.sangue.service.Teste;

@RestController
@RequestMapping("/sample")
public class SampleController {

	Teste teste = new Teste();

    @RequestMapping("/paulete={viado}")
    @ResponseBody
    TesteEntitie home(@PathVariable("viado") String isPauleteViado) {
    	
    	return teste.teste(isPauleteViado);
    }
    
}