package com.curso.modelo.negocio;

//Sin arrobas ni celemines: java config
public class ServicioMovidas {

	private String dependencia1;
	private String dependencia2;
	
	public ServicioMovidas(String dependencia1) {
		super();
		this.dependencia1 = dependencia1;
		dependencia2 = "TROLOLO";
	}

	public ServicioMovidas(String dependencia1, String dependencia2) {
		super();
		this.dependencia1 = dependencia1;
		this.dependencia2 = dependencia2;
	}

	public void movidaGordísima() {
		System.out.println(dependencia1+", "+dependencia2);
	}
	
	
}
