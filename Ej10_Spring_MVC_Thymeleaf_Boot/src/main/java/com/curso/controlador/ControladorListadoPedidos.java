package com.curso.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.curso.modelo.entidad.DetallePedido;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.negocio.GestorPedidos;

@Controller
@Scope("singleton")
public class ControladorListadoPedidos {

	@Autowired private GestorPedidos gestorPedidos;
	@Autowired private GestorClientes gestorClientes;

	@GetMapping("/verListadoPedidos")
	public ModelAndView verListadoPedidos(@RequestParam(name = "mensaje", required = false) String mensaje) {
		
		System.out.println("MENSAJE:"+mensaje);
		
		ModelAndView mav = new ModelAndView("listadoPedidos");
		mav.addObject("listaPedidos", gestorPedidos.listar());
		mav.addObject("mensaje", mensaje);
		return mav;
	}
	
	//GET /seleccionarPedido?id=XXX
	@GetMapping("/seleccionarPedido")
	public ModelAndView seleccionarPedido(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView("formularioPedidos");
		mav.addObject("pedido", gestorPedidos.buscarConDetalles(id));
		mav.addObject("detalle", new DetallePedido());
		mav.addObject("listaClientes", gestorClientes.listar());
		return mav;
	}	

}
