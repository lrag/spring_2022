package com.curso.cfg;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Applicacion {

	public static void main(String[] args) {
		
		ApplicationContext appCtx = new AnnotationConfigApplicationContext(Configuracion.class);		
		Emisor e = (Emisor) appCtx.getBean("emisor");
		e.send("cola1", "Hola Radiola");	
		
		JOptionPane.showMessageDialog(null, "Hola");
		
	}
	
}
