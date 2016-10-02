package br.com.bom.sangue.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="br.com.bom.sangue.controller")
public class ConfigServiceApplication {
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(ConfigServiceApplication.class, args);
    }

}
