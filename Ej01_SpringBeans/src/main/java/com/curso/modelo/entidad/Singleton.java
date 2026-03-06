package com.curso.modelo.entidad;

public class Singleton {
	
	private static Singleton instancia = new Singleton();
	
	private Singleton()
	{
		System.out.println("Creando el Singlet�n.");
	}

	public static Singleton getInstancia()
	{
		return instancia;
	}
}
