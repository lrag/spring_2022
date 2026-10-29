package com.curso.endpoint;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import com.curso.modelo.negocio.adapter.ServicioPeliculasAdapter;
import com.curso.modelo.negocio.excepcion.RecursoNoExistenteException;

import jakarta.validation.Valid;

@RequestMapping( 
		path = "/peliculas",
		produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
	)
//@RestController
public class _03_PeliculasEndpoint_Validacion {

	/*
	GET 	/peliculas		
	GET 	/peliculas/{id}
	POST 	/peliculas
	PUT 	/peliculas/{id}
	DELETE 	/peliculas/{id}
	*/
	
	private ServicioPeliculasAdapter servicioPeliculas;
	
	public _03_PeliculasEndpoint_Validacion(ServicioPeliculasAdapter servicioPeliculas) {
		super();
		this.servicioPeliculas = servicioPeliculas;
	}

	/*

	PETICIÓN:

	POST /peliculas
	CT: app/json
	-------------------
	{ pelicula }
	
	RESPUESTAS:
	
	201 CREATED
	CT: app/json
	-------------------
	{peliculaInsertada}
	
	400 BAD REQUEST
	CT: app/json
	-------------------
	{
		titulo: obligatorio,
		year: posterior a 1900
	}

	Esta es la que se da cuando el fallo viene del servicio de películas
	400 BAD REQUEST
	
	*/
	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
		)
	public ResponseEntity<?> insertar(@Valid @RequestBody PeliculaDTO pelicula, BindingResult br) {	
		
		if(br.hasErrors()) {
			Map<String, String> errores = 
				br.getFieldErrors()
					.stream()
						.collect(Collectors.toMap( fe -> (String) fe.getField(), 
								                   fe -> (String) fe.getDefaultMessage()));
			return new ResponseEntity<Object>(errores, HttpStatus.BAD_REQUEST);		
		}		
		
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
	public ResponseEntity<PeliculaDTO> buscar(@PathVariable Integer id) {	
		try {
			PeliculaDTO pelicula = servicioPeliculas.buscarPorId(id);
			return new ResponseEntity<PeliculaDTO>(pelicula, HttpStatus.OK);
		} catch (RecursoNoExistenteException e) {
			return new ResponseEntity<PeliculaDTO>(HttpStatus.NOT_FOUND);
		}
	}	
	
}
