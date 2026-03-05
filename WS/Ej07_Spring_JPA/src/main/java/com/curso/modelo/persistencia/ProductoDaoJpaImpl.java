package com.curso.modelo.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import com.curso.modelo.entidad.Producto;

@Repository
//Singleton
public class ProductoDaoJpaImpl extends AbstractDaoJpaImpl<Producto, Integer> implements ProductoDao {
	//Si productoDao tuviera algún método extra se implementaría aquí
	
	@PersistenceContext
	protected EntityManager em;	
	
}
