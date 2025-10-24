package com.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.ServicioMovidas;
import com.curso.modelo.negocio.ServicioPeliculas;
import com.curso.modelo.persistencia.PeliculaRepositorio;

@SpringBootApplication
public class Aplicacion {

	public static void main(String[] args) {
		ApplicationContext appCtx = SpringApplication.run(Aplicacion.class, args);
		
		ServicioPeliculas sp = (ServicioPeliculas) appCtx.getBean("servicioPeliculas");
		PeliculaRepositorio peliculaRepo = appCtx.getBean(PeliculaRepositorio.class);
		
		System.out.println("========================================");
		Pelicula p1 = new Pelicula(null, "Die Hard", "John McTiernan", "Accion", 1989);
		//sp.insertar(p1);
		System.out.println(peliculaRepo.findAll());
		
		/*
		ServicioPeliculas sp1 = (ServicioPeliculas) appCtx.getBean("servicioPeliculas");
		ServicioPeliculas sp2 = appCtx.getBean(ServicioPeliculas.class);
		System.out.println(sp1);
		System.out.println(sp2);
		
		Pelicula p = new Pelicula();
		sp1.insertar(p);
		
		ServicioMovidas sm1 = (ServicioMovidas) appCtx.getBean("servicioMovidas1");
		ServicioMovidas sm2 = (ServicioMovidas) appCtx.getBean("servicioMovidas2");
		
		sm1.movidaGordísima();
		sm2.movidaGordísima();
		*/
		
	}

}
