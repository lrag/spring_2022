package com.curso.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.cfg.Configuracion;
import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.negocio.excepcion.ClienteException;
import com.curso.rest.dto.ClienteDto;
import com.curso.rest.dto.Mensaje;

/*
GET    /clientes
GET    /clientes/id
POST   /clientes
PUT    /clientes/id
DELETE /clietnes/id
*/

//@RestController
//@Scope("singleton")
@RequestMapping(
		path = "/clientes",
		produces = { "application/json", "application/xml" }		
	)
public class ClientesRest_Sin_Exception_Handlers_OLD {

	private GestorClientes gestorClientes;
	
	public ClientesRest_Sin_Exception_Handlers_OLD(GestorClientes gestorClientes, Configuracion configuracion) {
		super();
		this.gestorClientes = gestorClientes;
	}

	//POST /clientes
	//Content-type: application/json
	//Accept: application/xml
	//------------------------------
	//{cliente}
	/*
	@RequestMapping(
			method = RequestMethod.POST,
			path = "/clientes"
		)
	*/
	@PostMapping(
			//path = "/clientes",
			consumes = { "application/json", "application/xml" }
			//produces = { "application/json", "application/xml" }
		)
	public ResponseEntity<Object> insertar(@Valid @RequestBody ClienteDto clienteDto, BindingResult result) {
		
		if(result.hasErrors()) {
			Mensaje mensaje = new Mensaje("400", "Datos invalidos");
			return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
		}
		
		Cliente cliente = clienteDto.asCliente();
		try {
			gestorClientes.insertar(cliente);
		} catch(ClienteException e) {
			Mensaje mensaje = new Mensaje("400", e.getMessage());
			return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			Mensaje mensaje = new Mensaje("500", "Hubo un ZASCA");
			return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new ClienteDto(cliente), HttpStatus.CREATED);
		
	}
	
	//PUT /clientes/4321?id=1234
	//Content-type: app/json
	//id: 5678
	//--------------------------
	//{ cliente }
	@PutMapping(
			path = "/{id}",
			//path = "/clientes/{id}",
			consumes = { "application/json", "application/xml" } 
			//produces = { "application/json", "application/xml" }
		)
	public ResponseEntity<Object> modificar(@PathVariable("id") Integer id, @Valid @RequestBody ClienteDto clienteDto, BindingResult result) {
		
		if(result.hasErrors()) {
			Mensaje mensaje = new Mensaje("400", "Datos invalidos");
			return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
		}
		
		Cliente cliente = clienteDto.asCliente();
		try {
			gestorClientes.modificar(cliente);
		} catch(ClienteException e) {
			Mensaje mensaje = new Mensaje("400", e.getMessage());
			return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			Mensaje mensaje = new Mensaje("500", "Hubo un ZASCA");
			return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new ClienteDto(cliente), HttpStatus.OK);
		
	}
	
	//DELETE /clientes/id
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> borrar(@PathVariable("id") Integer id) {	
		int a = 10 / 0;
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//GET /clientes/id
	@GetMapping(
			path = "/{id}"
		)	
	public ResponseEntity<ClienteDto> buscar(@PathVariable("id") Integer id) {
		Cliente cliente = gestorClientes.buscar(id);
		if(cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ClienteDto(cliente), HttpStatus.OK);
	}
	
	//GET /clientes
	@GetMapping(
			//path = "/clientes"
			//produces = { "application/json", "application/xml" }					
		)	
	public List<ClienteDto> listar() {
		return gestorClientes.listar()
				.stream()
				.map( c -> new ClienteDto(c))
				.collect(Collectors.toList());
	}
	
}