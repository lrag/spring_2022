package com.curso.modelo.persistencia_sin_spring_data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.curso.modelo.entidad.Cliente;

//@Repository
public class RepositorioClientesJPAImpl implements RepositorioClientes_{
	
	//Análogo a autowired, específico para inyectar entity managers
	@PersistenceContext
	private EntityManager em;
	
	public RepositorioClientesJPAImpl() {
		super();
		System.out.println("Creando una instancia de RepositorioClientesJPAImpl");
	}

	public List<Cliente> listar(){
		Query q = em.createQuery("select c from Cliente as c");
		return q.getResultList();
	}
	
	public Cliente buscar(Integer id) {
		return em.find(Cliente.class, id);
	}
	
	public void insertar(Cliente cliente) {
		em.persist(cliente);
	}
	
	public void modificar(Cliente cliente) {
		em.merge(cliente);
	}
	
	public void borrar(Cliente cliente) {
		cliente = em.find(Cliente.class, cliente.getId());
		em.remove(cliente);
	}
	
}
