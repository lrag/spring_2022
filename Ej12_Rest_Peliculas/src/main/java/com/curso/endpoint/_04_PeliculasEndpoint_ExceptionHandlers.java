package com.curso.endpoint;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.endpoint.dto.PeliculaDTO;
import com.curso.modelo.negocio.adapter.ServicioPeliculasAdapter;
import com.curso.modelo.negocio.excepcion.PeliculaException;
import com.curso.modelo.negocio.excepcion.RecursoNoExistenteException;

import jakarta.validation.Valid;

@RequestMapping( 
		path = "/peliculas",
		produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
	)
//@RestController
public class _04_PeliculasEndpoint_ExceptionHandlers {

	/*
	GET 	/peliculas		
	GET 	/peliculas/{id}
	POST 	/peliculas
	PUT 	/peliculas/{id}
	DELETE 	/peliculas/{id}
	*/
	
	private ServicioPeliculasAdapter servicioPeliculas;
	
	public _04_PeliculasEndpoint_ExceptionHandlers(ServicioPeliculasAdapter servicioPeliculas) {
		super();
		this.servicioPeliculas = servicioPeliculas;
	}

	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
		)
	public ResponseEntity<?> insertar(@Valid @RequestBody PeliculaDTO pelicula) throws PeliculaException {	
		PeliculaDTO peliculaInsertada = servicioPeliculas.insertar(pelicula);
		return new ResponseEntity<PeliculaDTO>(peliculaInsertada, HttpStatus.CREATED);
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
	public ResponseEntity<PeliculaDTO> buscar(@PathVariable Integer id) throws RecursoNoExistenteException {	
		PeliculaDTO pelicula = servicioPeliculas.buscarPorId(id);
		return new ResponseEntity<PeliculaDTO>(pelicula, HttpStatus.OK);
	}	
	
	//
	// Si en todos los RestControler vamos a controlar así
	// los errores de validación lo mejor es dar de alta
	// este exception handler en un @ControlerAdvice
	//
	/*
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public ResponseEntity<?> handleValidationException(Throwable e) {
		return new ResponseEntity<Object>("HUBO UN ERROR, ADIVINA TU CUAL ES", HttpStatus.BAD_REQUEST);		
	}
	*/
	
	
}
