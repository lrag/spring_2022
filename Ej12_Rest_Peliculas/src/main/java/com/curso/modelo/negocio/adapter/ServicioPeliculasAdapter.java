package com.curso.modelo.negocio.adapter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.curso.endpoint.dto.PeliculaDTO;
import com.curso.modelo.negocio.ServicioPeliculas;
import com.curso.modelo.negocio.excepcion.PeliculaException;
import com.curso.modelo.negocio.excepcion.RecursoNoExistenteException;

@Service
public class ServicioPeliculasAdapter {
	
	private ServicioPeliculas servicioPeliculas;
	
	public ServicioPeliculasAdapter(ServicioPeliculas servicioPeliculas) {
		super();
		this.servicioPeliculas = servicioPeliculas;
	}

	public PeliculaDTO insertar(PeliculaDTO peliculaDTO) throws PeliculaException {
		return new PeliculaDTO(servicioPeliculas.insertar(peliculaDTO.asPelicula()));
	}
	
	public List<PeliculaDTO> listar(String genero){
		return servicioPeliculas.listar(genero)
			.stream()
			.map( pelicula -> new PeliculaDTO(pelicula))
			.toList();
	}
	
	public PeliculaDTO buscarPorId(Integer id) throws RecursoNoExistenteException {
		return new PeliculaDTO(servicioPeliculas.buscarPorId(id));
	}
	
}












