package com.curso.modelo.entidad;

public class Casa extends AbstractBean {

	private Seguro seguro;
	
	public Casa() {
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

}
