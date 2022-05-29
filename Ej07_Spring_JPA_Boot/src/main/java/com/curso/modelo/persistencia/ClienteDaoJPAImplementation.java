package com.curso.modelo.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.curso.modelo.entidad.Cliente;

@Repository
public class ClienteDaoJPAImplementation implements ClienteDao {

	@PersistenceContext
	private EntityManager em;	
	
	@Override
	public void insertar(Cliente obj) {
		em.persist(obj);
	}

	@Override
	public void modificar(Cliente obj) {
		em.merge(obj);
	}

	@Override
	public void borrar(Cliente obj) {
		//Y esto fallará
		em.remove(obj);
	}

	@Override
	public Cliente buscar(Integer id) {
		return em.find(Cliente.class, id);
	}

	@Override
	public List<Cliente> listar() {
		TypedQuery<Cliente> q = em.createQuery("select c from Cliente c", Cliente.class);
		return q.getResultList();
	}

}
