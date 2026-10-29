package com.curso.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.excepcion.PeliculaException;
import com.curso.modelo.negocio.excepcion.RecursoNoExistenteException;

@Service
public class ServicioPeliculas {

	public static List<Pelicula> peliculas;
	
	static {
		peliculas = new ArrayList<>();

		peliculas.add(new Pelicula(1,"2001","Stanley Kubrik","Ci-Fi",1968));
		peliculas.add(new Pelicula(2,"Alien","Ridley Scott","Ci-Fi",1979));
		peliculas.add(new Pelicula(3,"Die Hard","John McTiernan","Accion",1988));
		peliculas.add(new Pelicula(4,"Young Frankenstein","Mel Brooks","Comedia",1974));
		peliculas.add(new Pelicula(5,"Moon","Duncan Jones","Ci-Fi",2009));
		peliculas.add(new Pelicula(6,"El bueno, el feo y el malo","Sergio Leone","Western",1968));
	}
	
	public Pelicula insertar(Pelicula pelicula) throws PeliculaException {
		
		
		//int a = 0;
		//int b = 10 / a;
		
		
		//Esto es para el ejemplo de los exception handlers
		if(pelicula.getTitulo().trim().equals("")) {
			throw new PeliculaException("El título es obligatorio");
		}
		
		pelicula.setId((int) Math.round(Math.random()*1_000_000));
		peliculas.add(pelicula);
		return pelicula;
	}
	
	//Ignoramos el filtrado...
	public List<Pelicula> listar(String genero){
		return peliculas;
	}
	
	public Pelicula buscarPorId(Integer id) throws RecursoNoExistenteException {
		return peliculas.stream()
			.filter( p -> p.getId()==id)
			.findFirst()
			.orElseThrow(() -> new RecursoNoExistenteException("La película "+id+" no existe."));
	}
	
}












