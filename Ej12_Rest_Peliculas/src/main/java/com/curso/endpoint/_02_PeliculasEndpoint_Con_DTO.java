package com.curso.endpoint;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.endpoint.dto.PeliculaDTO;
import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.adapter.ServicioPeliculasAdapter;
import com.curso.modelo.negocio.excepcion.RecursoNoExistenteException;

@RequestMapping( 
		path = "/peliculas",
		produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
	)
//@RestController
public class _02_PeliculasEndpoint_Con_DTO {

	/*
	GET 	/peliculas		
	GET 	/peliculas/{id}
	POST 	/peliculas
	PUT 	/peliculas/{id}
	DELETE 	/peliculas/{id}
	*/
	
	private ServicioPeliculasAdapter servicioPeliculas;
	
	public _02_PeliculasEndpoint_Con_DTO(ServicioPeliculasAdapter servicioPeliculas) {
		super();
		this.servicioPeliculas = servicioPeliculas;
	}

	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
		)
	public ResponseEntity<PeliculaDTO> insertar(@RequestBody PeliculaDTO pelicula) {	
		try {
			PeliculaDTO peliculaInsertada = servicioPeliculas.insertar(pelicula);
			return new ResponseEntity<PeliculaDTO>(peliculaInsertada, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<PeliculaDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping( 
			path = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
		)
	public void modificar(@PathVariable() Integer id) {	
		
	}
	
	@DeleteMapping
	public void borrar() {	
		
	}
	
	@GetMapping(
		)
	public List<PeliculaDTO> listar(@RequestParam(required = false) String genero) {	
		System.out.println("Listar peliculas. Genero="+genero);
		List<PeliculaDTO> peliculas = servicioPeliculas.listar(genero);
		return peliculas;
	}
	
	@GetMapping(
			path = "/{id}"
		)
	public ResponseEntity<PeliculaDTO> buscar(@PathVariable() Integer id) {	
		try {
			PeliculaDTO pelicula = servicioPeliculas.buscarPorId(id);
			return new ResponseEntity<PeliculaDTO>(pelicula, HttpStatus.OK);
		} catch (RecursoNoExistenteException e) {
			return new ResponseEntity<PeliculaDTO>(HttpStatus.NOT_FOUND);
		}		
	}	
	
}
