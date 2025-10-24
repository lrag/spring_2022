package modelo;

public class Singleton {
	
	private static Singleton instancia = new Singleton();
	
	private Singleton()
	{
		System.out.println("Creando el Singletón.");
	}

	public static Singleton getInstancia()
	{
		return instancia;
	}
}
