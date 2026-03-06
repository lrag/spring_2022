package com.curso.modelo.entidad;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

//No hagais esto en casa.
public class ProductoDao {

	//Los daos NO deben tener una conexi�n.
	private Connection cx;
	private DataSource ds;
	
	public ProductoDao() {
		super();
		System.out.println(":::Creando ProductoDao.");
	}

	//Con el set nos basta.
	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public void init()
	{
		System.out.println(":::Obteniendo una conexi�n en ProductoDao.");
		try {
			cx = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void hacerCosas()
	{
		System.out.println("Haciendo cosas con la conexion:"+cx);
	}
	
	public void destroy()
	{
		System.out.println(":::Cerrando la conexi�n en ProductoDao.");
		try {
			cx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
