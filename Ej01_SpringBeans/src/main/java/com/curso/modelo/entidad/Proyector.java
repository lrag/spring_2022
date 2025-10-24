package modelo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Proyector implements InitializingBean, DisposableBean 
{

	@Override
	public void afterPropertiesSet() throws Exception {
		
		System.out.println("Calentando la bombilla.");
		
	}
	
	public void proyectar()
	{
		System.out.println("Proyectando...");
	}

	@Override
	public void destroy() throws Exception {

		System.out.println("Apagando la bombilla.");
		System.out.println("Enfriando el aparato.");
		
	}

}
