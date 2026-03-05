package com.curso.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.curso.modelo.entidad.DetallePedido;
import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.negocio.GestorPedidos;
import com.curso.modelo.negocio.GestorProductos;

@Controller
@Scope("singleton")
public class ControladorFormularioPedidos {

	@Autowired private GestorPedidos gestorPedidos;
	@Autowired private GestorClientes gestorClientes;
	@Autowired private GestorProductos gestorProductos;

	//@RequestMapping(method = RequestMethod.POST,
	//				path = "insertarPedido")
	@PostMapping("/insertarPedido")
	public ModelAndView insertarPedido(@Valid @ModelAttribute("pedido") Pedido pedido, BindingResult bindingResult) {
		
		//Para validar 'a mano'
		//ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		//Validator v = vf.getValidator();
		//Set<ConstraintViolation<Pedido>> cv = v.validate(pedido);
		
		//No podemos colocar en Cliente.id @NotNull
		//Tampoco podemos (al menos en esta ocasión) hacer una validación en profundidad
		if(pedido.getCliente().getId() == null) {
			bindingResult.rejectValue("cliente.id", null, "El cliente es obligatorio");
			ModelAndView mav = new ModelAndView("formularioPedidos");
			mav.addObject("detalle", new DetallePedido());
			mav.addObject("listaClientes", gestorClientes.listar());
			//mav.addObject("errores", bindingResult);
			return mav;
		}
		
		if(bindingResult.hasErrors()) {			
			ModelAndView mav = new ModelAndView("formularioPedidos");
			//Todo lo que se recibe con @ModelAtribute se añade automáticamente al ModelAndView que devolvamos:
			//mav.addObject("pedido", pedido);
			mav.addObject("detalle", new DetallePedido());
			mav.addObject("listaClientes", gestorClientes.listar());
			//mav.addObject("errores", bindingResult);
			
			return mav;
		} 
		
		gestorPedidos.insertar(pedido);
		
		//Esto está mal: debemos hacer un redirect!
		//ModelAndView mav = new ModelAndView("listadoPedidos");
		//mav.addObject("listaPedidos", gestorPedidos.listar());		
		//return mav;
	
		//Los redirect nunca son a la vista, son al controlador que nos devolverá la vista
		return new ModelAndView("redirect:verListadoPedidos?mensaje=Pedido insertado");
	}

	@PostMapping("/modificarPedido")
	public ModelAndView modificarPedido(@ModelAttribute("pedido") Pedido pedido) {
		gestorPedidos.modificar(pedido);
		return new ModelAndView("redirect:verListadoPedidos?mensaje=Pedido modificado");
	}
	
	@PostMapping("/borrarPedido")
	public ModelAndView borrarPedido(@RequestParam("id") Integer idPedido) {
		gestorPedidos.borrar(idPedido);
		return new ModelAndView("redirect:verListadoPedidos?mensaje=Pedido borrado");
	}
	
	@PostMapping("/aceptarPedido")
	public ModelAndView aceptarPedido(@ModelAttribute("pedido") Pedido pedido) throws Exception {
		gestorPedidos.aceptar(pedido);
		return null;
	}

	@GetMapping("/verFormularioPedidos")
	public ModelAndView verFormularioPedidos() {
		ModelAndView mav = new ModelAndView("formularioPedidos");
		mav.addObject("pedido", new Pedido());
		mav.addObject("detalle", new DetallePedido());
		mav.addObject("listaClientes", gestorClientes.listar());
		mav.addObject("listaProductos", gestorProductos.listar());
		return mav;
	}
	
}
