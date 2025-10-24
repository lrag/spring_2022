package com.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.GestorPeliculas;
import com.otro.Calculadora;

@SpringBootApplication
//@Configuration
//@ComponentScan(basePackages = "com.curso")
public class Aplicacion {
	
	public static void main(String[] args) {
		ApplicationContext appCtx = SpringApplication.run(Aplicacion.class, args);

		System.out.println("===================================");
		
		GestorPeliculas gp =  appCtx.getBean(GestorPeliculas.class);
		Pelicula p1 = new Pelicula(null, "Titulo", "Genero");
		gp.insertar(p1);
		
		Calculadora calculadora = (Calculadora) appCtx.getBean("calculadora");
		System.out.println(calculadora.sumar(10, 20));
		
	}

}
