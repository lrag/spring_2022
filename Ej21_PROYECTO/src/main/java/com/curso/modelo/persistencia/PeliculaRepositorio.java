package com.curso.modelo.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.modelo.entidad.Pelicula;

@Repository //Con spring data este estereotipo es opcional
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer>{
}

/*
@Repository
public class PeliculaRepositorio {
	  
	@PersistenceContext
	private EntityManager em;
	
	public void insertar(Pelicula pelicula) {
		//System.out.println("PeliculaRepo.insertar: "+pelicula);
		//System.out.println("EM: "+em);
		em.persist(pelicula);
	}
	
	public void modificar(Pelicula pelicula) {
		//System.out.println("PeliculaRepo.insertar: "+pelicula);
		//System.out.println("EM: "+em);
		em.merge(pelicula);
	}
	
	...
	
}
*/
