package com.curso;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.GestorPeliculas;

public class Pruebas {

	public static void main(String[] args) {
		
		AbstractApplicationContext appCtx = new ClassPathXmlApplicationContext("Beans.xml");

		GestorPeliculas gp1 = (GestorPeliculas) appCtx.getBean("gestorPeliculas");
		GestorPeliculas gp2 = (GestorPeliculas) appCtx.getBean("gestorPeliculas");
		GestorPeliculas gp3 = (GestorPeliculas) appCtx.getBean("gestorPeliculas");
		
		System.out.println(gp1);
		System.out.println(gp2);
		System.out.println(gp3);
		
		
		/*
		Pelicula p1 = new Pelicula(null, "Alien", "Ci-Fi");
		GestorPeliculas gp = appCtx.getBean("gestorPeliculas", GestorPeliculas.class);
		gp.insertar(p1);
		*/
		
		appCtx.close();
	} 
	
	
	
}
