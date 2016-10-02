package br.com.bom.sangue.service;

import br.com.bom.sangue.entities.TesteEntitie;

public class Teste {
	
	public TesteEntitie teste (String string) {
		TesteEntitie teste = new TesteEntitie();
		teste.setTeste(string);
		teste.setNumero(10);
		return teste;
	}

}
