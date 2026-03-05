package com.curso.modelo.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.modelo.entidad.Pelicula;
import com.curso.persistencia.PeliculaDao;

@Service
public class GestorPeliculas {

	@Autowired
	private PeliculaDao peliculaDao;
	
	public void insertar(Pelicula pelicula) {
		//LN...
		peliculaDao.insertar(pelicula);
	}
	
	public void modificar(Pelicula pelicula) {
		//LN...
		peliculaDao.modificar(pelicula);
	}
	
	public void borrar(Pelicula pelicula) {
		//LN...
		peliculaDao.borrar(pelicula);
	}

	public Pelicula buscar(Integer id) {
		return peliculaDao.buscar(id);
	}
	
	public List<Pelicula> listar() {
		return peliculaDao.listar();
	}
	
}
