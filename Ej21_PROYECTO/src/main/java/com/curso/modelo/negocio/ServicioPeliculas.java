package com.curso.modelo.negocio;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.persistencia.PeliculaRepositorio;

/*
Estereotipos de Spring:

@Service
@Repository
@Component

Si tenemos Spring MVC:

@Controller
@RestController

Ámbitos:

singleton (por defecto)
prototype

Si tenemos Spring web:

@Request
@Session


*/

/*
Receta: servicioPeliculas
crear un objeto del tipo ServicioPeliculas
*/

@Service
//@Scope("singleton")
@Transactional
public class ServicioPeliculas {

	//@Autowired
	private PeliculaRepositorio peliculaRepo;

	//@Autowired //Autowired en el constructor es opcional
	public ServicioPeliculas(PeliculaRepositorio peliculaRepo) {
		super();
		this.peliculaRepo = peliculaRepo;
	}

	//@Autowired
	//public void setPeliculaRepo(PeliculaRepositorio peliculaRepo) {
	//	this.peliculaRepo = peliculaRepo;
	//}

	public void insertar(Pelicula pelicula) {
		//LN...
		System.out.println("ServicioPeliculas.insertar: "+pelicula);
		peliculaRepo.save(pelicula);
	}
		
	public void modificar(Pelicula pelicula) {
		//LN...
		System.out.println("ServicioPeliculas.modificar: "+pelicula);
		peliculaRepo.save(pelicula);
	}
	
	public void borrar(Pelicula pelicula) {
		System.out.println("ServicioPeliculas.borrar: "+pelicula);
		//peliculaRepo.deleteById(pelicula.getId());
		peliculaRepo.delete(pelicula);
	}
	
}
