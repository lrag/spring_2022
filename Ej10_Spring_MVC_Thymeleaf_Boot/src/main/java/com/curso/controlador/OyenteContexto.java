package com.curso.controlador;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class OyenteContexto implements ServletContextListener {

    public OyenteContexto() {
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("========================================");
    	System.out.println("CONTEXTO INICIALIZADO");
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("========================================");
    	System.out.println("CONTEXTO INICIALIZADO");
    }
	
}
