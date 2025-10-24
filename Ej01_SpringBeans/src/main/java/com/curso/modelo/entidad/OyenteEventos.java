package modelo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class OyenteEventos implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent ev) {
		
		if(ev instanceof ContextRefreshedEvent)
		{
			System.out.println("INICIO!!!");
		}
		
		if(ev instanceof Evento)
		{
			Evento e = (Evento) ev;
			System.out.println("EVENTO:"+e.dato);
		}
	}

}
