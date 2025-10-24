package modelo;

import java.util.List;

public class Flota extends AbstractBean {
	
	private String nombre;
	private List<Coche> coches;
	public Flota() {
		super();
	}
	public Flota(String nombre, List<Coche> coches) {
		super();
		this.nombre = nombre;
		this.coches = coches;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Coche> getCoches() {
		return coches;
	}
	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}
	
	
	

}
