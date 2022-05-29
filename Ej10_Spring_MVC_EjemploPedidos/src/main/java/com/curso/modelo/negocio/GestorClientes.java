package com.curso.modelo.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Producto;
import com.curso.modelo.persistencia.ClienteDao;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class GestorClientes {

	@Autowired
	private ClienteDao clienteDao;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public Cliente buscar(Integer id) {
		return clienteDao.buscar(id);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Cliente> listar() {
		return clienteDao.listar();
	}

}
