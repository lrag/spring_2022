package com.curso.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.negocio.excepcion.ClienteException;
import com.curso.rest.dto.ClienteDto;

//@RestController
@RequestMapping(
		path = "/clientes",
		produces = { "application/json", "application/xml" }		
	)
public class ClientesRest_ {
	
	private GestorClientes gestorClientes;
	
	public ClientesRest_(GestorClientes gestorClientes) {
		super();
		this.gestorClientes = gestorClientes;
	}

	//POST /clientes
	@PostMapping(consumes = { "application/json", "application/xml" })
	public ResponseEntity<Object> insertar(@Valid @RequestBody ClienteDto clienteDto) throws ClienteException {
		Cliente cliente = clienteDto.asCliente();
		gestorClientes.insertar(cliente);
		return new ResponseEntity<>(new ClienteDto(cliente), HttpStatus.CREATED);
	}
	
	//PUT /clientes/id
	@PutMapping(
			path = "/{id}",
			consumes = { "application/json", "application/xml" }
		)
	public ResponseEntity<Object> modificar(@PathVariable("id") Integer id, @Valid @RequestBody ClienteDto clienteDto) throws ClienteException {
		Cliente cliente = clienteDto.asCliente();
		gestorClientes.modificar(cliente);
		return new ResponseEntity<>(new ClienteDto(cliente), HttpStatus.CREATED);
	}
	
	//DELETE /clientes/id
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> borrar(@PathVariable("id") Integer id) {	
		int a = 10 / 0;
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//GET /clientes/id
	@GetMapping(path = "/{id}")	
	public ResponseEntity<Object> buscar(@PathVariable Integer id) {
		Cliente cliente = gestorClientes.buscar(id);
		if(cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ClienteDto(cliente), HttpStatus.OK);
	}
	
	//GET /clientes
	@GetMapping	
	public List<ClienteDto> listar() {
		return gestorClientes.listar()
				.stream()
				.map( c -> new ClienteDto(c))
				.collect(Collectors.toList());
	}	
	
	/*
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) {
		Map<String, String> errores = 
				e.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(Collectors.toMap( fe -> (String) fe.getField(), 
								           fe -> (String) fe.getDefaultMessage()));
		return new ResponseEntity<Object>(errores, HttpStatus.BAD_REQUEST);		
	}
	*/	
	
}
