package com.curso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;

@SpringBootApplication
//@Configuration
//@ComponentScan(desde el paquete en el que estamos)
public class Aplicacion implements CommandLineRunner{
	
	@Autowired
	private GestorClientes gestorClientes;

	public static void main(String[] args) {

		SpringApplication.run(Aplicacion.class, args);
		
		/*		
		ApplicationContext appCtx = SpringApplication.run(Aplicacion.class, args);
		
		GestorClientes gc = appCtx.getBean(GestorClientes.class);
		
		List<Cliente> clientes = gc.listar();
		clientes.forEach(c -> System.out.println(c.getNombre()));
		*/
	}

	@Override
	public void run(String... args) throws Exception {
		
		Cliente cli = new Cliente(null,"Homer","C/Evergreen Terrace","555",5000);
		gestorClientes.insertar(cli);
		
		List<Cliente> clientes = gestorClientes.listar();
		clientes.forEach(c -> System.out.println(c.getNombre()));
	}

}
