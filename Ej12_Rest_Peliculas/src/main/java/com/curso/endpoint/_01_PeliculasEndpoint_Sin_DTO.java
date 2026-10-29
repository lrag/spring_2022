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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.endpoint.dto.PeliculaDTO;
import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.ServicioPeliculas;
import com.curso.modelo.negocio.excepcion.RecursoNoExistenteException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping( 
		path = "/peliculas",
		//No podemos poner aqui consumes porque no todas las peticiones tienen body
		//consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
		produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
	)
//@RestController
public class _01_PeliculasEndpoint_Sin_DTO {

	/*
	GET 	/peliculas		
	GET 	/peliculas/{id}
	POST 	/peliculas
	PUT 	/peliculas/{id}
	DELETE 	/peliculas/{id}
	*/
	
	private ServicioPeliculas servicioPeliculas;
	
	public _01_PeliculasEndpoint_Sin_DTO(ServicioPeliculas servicioPeliculas) {
		super();
		this.servicioPeliculas = servicioPeliculas;
	}

	/*
	@RequestMapping(
			method = RequestMethod.POST,
			path = "/padilla"
		)
	*/
	
	/*
	POST /peliculas
	CT: application/x-www-form-urlencoded
	------------------------
	titulo=a&director=b&genero=c&...
	public void insertar(@ModelAttribute Pelicula pelicula) {	

	POST /peliculas
	CT: app/json
	---------------
	{pelicula}
	
	*/
	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
		)
	public ResponseEntity<Pelicula> insertar(@RequestBody Pelicula pelicula) {	
		try {
			Pelicula peliculaInsertada = servicioPeliculas.insertar(pelicula);
			return new ResponseEntity<Pelicula>(peliculaInsertada, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Pelicula>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping( 
			path = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
		)
	//public void modificar(@PathVariable("id") Integer id) {	
	//public void modificar(@PathVariable("id") Integer tocoto) {	
	public void modificar(@PathVariable() Integer id) {	
		
	}
	
	@DeleteMapping //( path = "/peliculas")
	public void borrar() {	
		
	}
	
	@GetMapping("/saludar")
	public String saludar() {
		return "HOLA QUE TAL";
	}
	
	/*
	GET /peliculas
	
	GET /peliculas
	accept: aaplication/json
	*/
	
	
	/*
	
	GET /clientes
	GET /clientes?ciudad=chinchon
	GET /clientes?estado=activo
	
	GET /clientes?id=activo => GET /clientes/id
	*/
	
	@GetMapping(
		)
	//public List<Pelicula> listar(@RequestParam("genero") String genero) {	
	public List<Pelicula> listar(@RequestParam(required = false) String genero) {	
		System.out.println("Listar peliculas. Genero="+genero);
		List<Pelicula> peliculas = servicioPeliculas.listar(genero);
		return peliculas;
	}
	
	@GetMapping(
			path = "/{id}"
		)
	public ResponseEntity<Pelicula> buscar(
			@PathVariable() Integer id, 
			//@RequestHeader("accept") String accept
			@RequestHeader String accept,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession sesion,
			ServletContext servletContext
		) {	
		try {
			Pelicula pelicula = servicioPeliculas.buscarPorId(id);
			return new ResponseEntity<Pelicula>(pelicula, HttpStatus.OK);
		} catch (RecursoNoExistenteException e) {
			return new ResponseEntity<Pelicula>(HttpStatus.NOT_FOUND);
		}		
	}	
	
}
