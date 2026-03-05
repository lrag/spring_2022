package com.curso.modelo.persistencia_sin_spring_data;

import java.util.List;

import com.curso.modelo.entidad.Cliente;

public interface RepositorioClientes_ {

	List<Cliente> listar();	
	Cliente buscar(Integer id);	
	void insertar(Cliente cliente);	
	void modificar(Cliente cliente);	
	void borrar(Cliente cliente);
	
}
