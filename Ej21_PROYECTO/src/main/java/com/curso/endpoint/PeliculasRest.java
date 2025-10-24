package com.curso.endpoint;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.endpoint.dto.Mensaje;
import com.curso.endpoint.dto.PeliculaDTO;
import com.curso.modelo.negocio.ServicioPeliculas;
import com.curso.modelo.persistencia.PeliculaRepositorio;

@RestController
public class PeliculasRest {

	/*
	Método  Ruta            Body     Respuesta  Funcionalidad
	GET     /peliculas       -       [{json}]   listarPeliculas
	GET     /peliculas/{id}  -       {json}     buscar
	POST    /peliculas       {json}             insertar
	PUT     /peliculas/{id}  {json}             modificar
	DELETE  /peliculas/{id}  -                  borrar
	*/
	
	private ServicioPeliculas servicioPeliculas;
	private PeliculaRepositorio peliculaRepositorio;
	
	public PeliculasRest(ServicioPeliculas servicioPeliculas, PeliculaRepositorio peliculaRepositorio) {
		super();
		this.servicioPeliculas = servicioPeliculas;
		this.peliculaRepositorio = peliculaRepositorio;
	}

	//Content-type: 
	@PostMapping(
			path = "/peliculas",
			consumes = "application/json"
		)
	public ResponseEntity<Object> insertar(@Valid @RequestBody PeliculaDTO peliculaDto, BindingResult errores) {
		
		if(errores.hasErrors()) {
			return new ResponseEntity(new Mensaje("400", "Datos inválidos"), HttpStatus.BAD_REQUEST);
		}
		
		servicioPeliculas.insertar(peliculaDto.asPelicula());
		return new ResponseEntity(new Mensaje("201", "Pelicula creada"), HttpStatus.CREATED);
		
	}
		
	@GetMapping(
			path = "/peliculas",
			produces = "application/json"
		)
	public List<PeliculaDTO> listar() {
		return peliculaRepositorio
			.findAll()
			.stream()
			.map( p-> new PeliculaDTO(p))
			.collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(
			path = "/peliculas/{id}",
			produces = "application/json"
		)
	public ResponseEntity<Object> buscar(@PathVariable("id") Integer id) {
		return peliculaRepositorio
			.findById(id)
			.map( pelicula -> new ResponseEntity(new PeliculaDTO(pelicula), HttpStatus.OK))
			.orElse(new ResponseEntity(new Mensaje("404", "La pelicula no existe"), HttpStatus.NOT_FOUND ));
	}
	
}
