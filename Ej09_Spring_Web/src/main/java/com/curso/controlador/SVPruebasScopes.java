package com.curso.controlador;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.curso.modelo.ClaseEjemplo;

@WebServlet(urlPatterns = "/SVPruebasScopes")
public class SVPruebasScopes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ApplicationContext appCtx;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// appCtx = (ApplicationContext)
		// this.getServletContext().getAttribute("contenedorDeEsprin");
		// appCtx.getBean("XXX");

		appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
	}

	public SVPruebasScopes() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ClaseEjemplo o1 = (ClaseEjemplo) appCtx.getBean("objetoSingleton");
		ClaseEjemplo o2 = (ClaseEjemplo) appCtx.getBean("objetoPrototype");
		ClaseEjemplo o3 = (ClaseEjemplo) appCtx.getBean("objetoPrototype");
		ClaseEjemplo o4 = (ClaseEjemplo) appCtx.getBean("objetoRequest");
		ClaseEjemplo o5 = (ClaseEjemplo) appCtx.getBean("objetoRequest");
		ClaseEjemplo o6 = (ClaseEjemplo) appCtx.getBean("objetoSession");
	
		request.setAttribute("o1", o1);
		request.setAttribute("o2", o2);
		request.setAttribute("o3", o3);
		request.setAttribute("o4", o4);
		request.setAttribute("o5", o5);
		request.setAttribute("o6", o6);		
		
		request.getRequestDispatcher("pagina.jsp").forward(request, response);		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

