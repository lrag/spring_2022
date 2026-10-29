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

import com.curso.endpoint.dto.Data;
import com.curso.endpoint.dto.PeliculaDTO;
import com.curso.endpoint.dto.RespuestaError;
import com.curso.endpoint.dto.RespuestaOk;
import com.curso.modelo.negocio.adapter.ServicioPeliculasAdapter;
import com.curso.modelo.negocio.excepcion.PeliculaException;
import com.curso.modelo.negocio.excepcion.RecursoNoExistenteException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

//
//
// http://localhost:8080/v3/api-docs.yaml
// http://localhost:8080/swagger-ui.html
//
//

@RequestMapping( 
		path = "/peliculas",
		produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
	)
@RestController
public class _06_PeliculasEndpoint_OpenApi {

	/*
	GET 	/peliculas		
	GET 	/peliculas/{id}
	POST 	/peliculas
	PUT 	/peliculas/{id}
	DELETE 	/peliculas/{id}
	*/
	
	private ServicioPeliculasAdapter servicioPeliculas;
	
	public _06_PeliculasEndpoint_OpenApi(ServicioPeliculasAdapter servicioPeliculas) {
		super();
		this.servicioPeliculas = servicioPeliculas;
	}

	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
		)
	@Operation(summary = "Alta de película", description = "Pues eso: alta de una pelicula")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Película creada con éxito", 
					 content = @Content(schema = @Schema(implementation = RespuestaOk.class))),
		@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content)
	})
	public ResponseEntity<?> insertar(@Valid @RequestBody PeliculaDTO pelicula) throws PeliculaException {	
		PeliculaDTO peliculaInsertada = servicioPeliculas.insertar(pelicula);
		Data data = new Data("Película insertada", peliculaInsertada);
		RespuestaOk respuesta = new RespuestaOk("201", "CREATED", data);
		return new ResponseEntity<RespuestaOk>(respuesta, HttpStatus.CREATED);
	}
	
	@PutMapping( 
			path = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
		)
	@Operation(summary = "Modificar un cliente", description = "Actualiza los datos de un cliente existente mediante su ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente actualizado con éxito"),
		@ApiResponse(responseCode = "404", description = "Cliente no encontrado")
	})
	public ResponseEntity<?> modificar(@Valid @RequestBody PeliculaDTO pelicula, @PathVariable Integer id) {
		//PeliculaDTO peliculaModificada = servicioPeliculas.modificar(pelicula);
		//Data data = new Data("Película modificada", peliculaModificada);
		//RespuestaOk respuesta = new RespuestaOk("200", "OK", data);
		//return new ResponseEntity<RespuestaOk>(respuesta, HttpStatus.OK);
		return null;
	}
	
	@DeleteMapping( path = "/{id}")
	@Operation(summary = "Baja de cliente", description = "Elimina de forma permanente un cliente por su ID")
	public void borrar(@PathVariable Integer id) {			
	}
	
	@GetMapping(
		)
	@Operation(summary = "Listar todos los clientes", description = "Obtiene una lista completa de los clientes registrados")
	public ResponseEntity<RespuestaOk> listar(@RequestParam(required = false) String genero) {	
		System.out.println("Listar peliculas. Genero="+genero);
		List<PeliculaDTO> peliculas = servicioPeliculas.listar(genero);
		Data data = new Data("Listado de películas", peliculas);
		RespuestaOk respuesta = new RespuestaOk("200", "OK", data);
		return new ResponseEntity<RespuestaOk>(respuesta, HttpStatus.OK);
	}
	
	@GetMapping(
			path = "/{id}"
		)
	@Operation(summary = "Buscar un cliente por ID", description = "Devuelve los detalles de un único cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado"),
			@ApiResponse(responseCode = "404", description = "Cliente no existe", 
			content = @Content(schema = @Schema(implementation = RespuestaError.class)))
	})
	public ResponseEntity<RespuestaOk> buscar(@PathVariable Integer id) throws RecursoNoExistenteException {	
		PeliculaDTO pelicula = servicioPeliculas.buscarPorId(id);
		Data data = new Data("Pelicula encontrada", pelicula);
		RespuestaOk respuesta = new RespuestaOk("200", "OK", data);
		return new ResponseEntity<RespuestaOk>(respuesta, HttpStatus.OK);
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
