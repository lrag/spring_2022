package com.curso.modelo.negocio;

import org.springframework.context.annotation.Scope;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.persistencia.RepositorioClientes;


/*
<bean scope="singleton" id="gestorClientes" class="com.curso.modelo.negocio.GestorClientes">
	<property name="repositorioClientes" ref="repositorioClientes"/>
</bean>
*/

@Service
public class GestorClientes {

	//@Autowired
	private RepositorioClientes repositorioClientes;
	
	//@Autowired Esta ya es opcional en el constructor
	public GestorClientes(RepositorioClientes repositorioClientes) {
		super();
		this.repositorioClientes = repositorioClientes;
	}

	public GestorClientes() {
		super();
		System.out.println("Creando una istancia de GestorClientes");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void insertar(Cliente cliente) {
		//comprobar la direccion del cliente
		//asociarle una sucursal
		//asociarle un comercial que tenga pocos clientes
		//enviar un correo electrónico de bievenida
		//insertar el cliente
		repositorioClientes.save(cliente);
	}
	
	@Transactional
	public void modificar(Cliente cliente) {
		//LN...
		repositorioClientes.save(cliente);
	}
	
	@Transactional
	public void borrar(Cliente cliente) {
		//LN...
		repositorioClientes.delete(cliente);
	}		
	
}








