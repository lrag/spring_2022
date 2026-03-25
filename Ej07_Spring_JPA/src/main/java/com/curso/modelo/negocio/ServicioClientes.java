package com.curso.modelo.negocio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.persistencia.ClienteDao;


/*
<bean id="servicioClientes" class="com.curso.SPM.ServicioClientes" scope="singletón">
	<contructor-arg ref="clienteDao"/>
</bean>
*/

@Service
public class ServicioClientes {

	private ClienteDao clienteDao;
	
	public ServicioClientes(ClienteDao clienteDao) {
		super();
		this.clienteDao = clienteDao;
	}

	@Transactional
	public void insertar(Cliente cliente) {
		//LN...
		clienteDao.insertar(cliente);
	}
	
	public List<Cliente> listar(){
		return clienteDao.listar();
	}	

	public Cliente buscar(Integer id){
		return clienteDao.buscar(id);
	}	
	
}
