package modelo;

import org.springframework.context.ApplicationEvent;

public class Evento extends ApplicationEvent {

	public String dato;
	
	public Evento(Object source,String dato) {
		super(source);
		this.dato = dato;
	}

	
	
}
