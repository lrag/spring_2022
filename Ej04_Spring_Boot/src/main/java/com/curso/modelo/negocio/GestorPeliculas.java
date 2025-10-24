package com.curso.modelo.negocio;

import org.springframework.stereotype.Service;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.persistencia.PeliculaDao;
import com.curso.util.Logger;

@Service
public class GestorPeliculas {

	private PeliculaDao peliculaDao;
	private Logger logger;
	private Logger loggerError;

	public GestorPeliculas(PeliculaDao peliculaDao, Logger logger, Logger loggerError) {
		super();
		this.peliculaDao = peliculaDao;
		this.logger = logger;
		this.loggerError = loggerError;
	}

	public void insertar(Pelicula pelicula){
		//LN
		//...
		peliculaDao.insertar(pelicula);		
		logger.escribir("Pelicula insertada");
		loggerError.escribir("ZASCA TARRASCA!");
	}
	
}



