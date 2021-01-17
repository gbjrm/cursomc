package com.gabrielmatos.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabrielmatos.cursomc.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	//Armazendando valor da chave dentre de uma variavel
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String stratagey;
	
	@Bean
	public boolean instantiateDateBase() throws ParseException {
		
		if (! "create".equals(stratagey)) {
			return false;
		}
		
		dbService.instantiateTestDabase();
		return true;
	}
}
