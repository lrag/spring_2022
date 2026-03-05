package com.curso.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.curso.controlador") //Y sub paquetes
@EnableWebMvc
public class ConfiguracionMVC {

	public ConfiguracionMVC() {
		super();
		System.out.println("Creando una instancia de ConfiguracionMVC");
	}
	
	@Bean
	public ViewResolver viewResolver() {
		System.out.println("Instanciando el ViewResolver");
		//Cuando las vistas son JSPs debemos utilizar el InternalResourceViewResolver
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/paginas/");
		vr.setSuffix(".jsp");
		return vr;
	}
	
}





