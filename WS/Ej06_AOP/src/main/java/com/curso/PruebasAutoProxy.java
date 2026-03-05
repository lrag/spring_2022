package com.curso;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Empleado;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.negocio.GestorEmpleados;

public class PruebasAutoProxy {

	public static void main(String[] args) {
		
		//AbstractApplicationContext appCtx = new AnnotationConfigApplicationContext(ConfiguracionAutoProxy.class);
		AbstractApplicationContext appCtx = new ClassPathXmlApplicationContext("BeansAutoproxy.xml");
				
		GestorClientes gc = appCtx.getBean("gestorClientes", GestorClientes.class);
		GestorEmpleados ge = appCtx.getBean(GestorEmpleados.class);
		
		Cliente c = new Cliente("Grace Hopper");
		gc.insertar(c);
		gc.borrar(c);
		
		Empleado e = new Empleado("Terence Hill");
		ge.insertar(e);
		ge.borrar(e);
						
		appCtx.close();
	}
	
}
