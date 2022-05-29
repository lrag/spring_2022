package com.curso.persistencia;

import java.util.List;

public interface InterfaceDao<T,k> {

	void insertar(T director);
	void modificar(T director);
	void borrar(T director);
	List<T> listar();
	T buscar(k idDirector);
	
}
