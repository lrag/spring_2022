package com.curso.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.curso.modelo.persistencia.RepositorioClientes;

//Spring creará una instancia de esta clase
//La llamará (si no indicamos lo contrario) 'clientesController'
//Le asignará el ámbito 'singleton'
@Controller
public class ListadoClientesController {

	@Autowired
	private RepositorioClientes repositorioClientes;
	
	public ListadoClientesController() {
		super();
		//Esta traza solo saldrá una vez porque esto es un singleton
		System.out.println("Creando una instancia de ListadoClientesController");
	}	
	
	//GET /listadoClientes
	@GetMapping(path = "/listadoClientes")
	public ModelAndView verListadoClientes() {
		//Model and view junta dos cosas:
		//-El nombre de una vista
		//-Los datos que esa vista necesita para generar el HTML
		System.out.println("ListadoClientesController.verListadoClientes");
		ModelAndView mav = new ModelAndView("listadoClientes"); //Sin la extensión!
		mav.addObject("listaClientes", repositorioClientes.findAll());
		return mav;		
	}	
	
}



/*

public class ServicioComerciales {

	asignarCliente(comercial, cliente)

}

public class ServicioClientes {

	altaCliente(Cliente)

}

@Controller
public class ControladorClientes {

	@Autowired
	private ServicioClientes scli;

	@Autowired
	private ServicioComerciales scom;

	@PostMapping("/cliente")
	public void altaCliente(){
	
		scom.asignarCliente(dsafsdf);
		scli.altaCliente(vbjksvsh);
	
	}


}







*/












