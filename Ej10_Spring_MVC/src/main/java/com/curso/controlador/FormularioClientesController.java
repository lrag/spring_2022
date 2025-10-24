package com.curso.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;

//Spring creará una instancia de esta clase
//La llamará (si no indicamos lo contrario) 'clientesController'
//Le asignará el ámbito 'singleton'
@Controller
public class FormularioClientesController {
	
	@Autowired
	private GestorClientes gestorClientes;

	public FormularioClientesController() {
		super();
		//Esta traza solo saldrá una vez porque esto es un singleton
		System.out.println("Creando una instancia de FormularioClientesController");
	}	

	@GetMapping("/formularioClientes")	
	public ModelAndView verFormularioClientes() {
		System.out.println("FormularioClientesController.verFormularioClientes");
		ModelAndView mav = new ModelAndView("formularioClientes");
		mav.addObject("cliente", new Cliente());		
		return mav;
	}

	@PostMapping("/insertarCliente")
	public ModelAndView insertarCliente(@ModelAttribute Cliente cliente) {
		System.out.println("FormularioClientesController.insertarCliente:"+cliente);
		
		//llamada a la logica de negocio
		gestorClientes.insertar(cliente);
		
		ModelAndView mav = new ModelAndView("listadoClientes");
	
		return mav;
	}
	
	@PostMapping("/modificarCliente")
	public ModelAndView modificarCliente() {
		System.out.println("FormularioClientesController.modificarCliente");
		ModelAndView mav = new ModelAndView("listadoClientes");
		return mav;
	}
	
	@PostMapping("/borrarCliente")
	public ModelAndView borrarCliente() {
		System.out.println("FormularioClientesController.borrarCliente");
		ModelAndView mav = new ModelAndView("listadoClientes");
		return mav;
	}
	
}















