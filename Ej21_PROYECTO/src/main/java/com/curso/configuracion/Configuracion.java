package com.curso.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.modelo.negocio.ServicioMovidas;

@Configuration
public class Configuracion {

	@Bean
	ServicioMovidas servicioMovidas1() {
		return new ServicioMovidas("FISTRO");
	}
	
	@Bean
	ServicioMovidas servicioMovidas2() {
		return new ServicioMovidas("AAA", "TORPEDO");
	}
	
}






