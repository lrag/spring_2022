package com.curso.controlador;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebListener
public class OyenteContexto implements ServletContextListener, ServletContextAttributeListener {

	private AbstractApplicationContext appCtx;
	
    public OyenteContexto() {
    }

    public void contextInitialized(ServletContextEvent sce)  { 

    	System.out.println("Contexto inicializado");
    	
    	//appCtx = new ClassPathXmlApplicationContext("...");
    	//sce.getServletContext().setAttribute("appCtx", appCtx);
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("Se va a destruir el contexto");
    	//appCtx.close();
    }

    public void attributeAdded(ServletContextAttributeEvent event)  { 
    	System.out.println("Atributo añadido al contexto:"+event.getName()+", "+event.getValue());
    }

    public void attributeReplaced(ServletContextAttributeEvent event)  { 
    }

    public void attributeRemoved(ServletContextAttributeEvent event)  { 
    }
	
}
