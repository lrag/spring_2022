package com.curso.modelo.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.persistencia.ClienteDao;

@Service
//
public class GestorClientes {

	@Autowired
	private ClienteDao clienteDao;
	
	@Transactional
	public void insertar(Cliente cliente) {
		//LN...
		clienteDao.insertar(cliente);
	}
	
	public List<Cliente> listar(){
		return clienteDao.listar();
	}
	
}
