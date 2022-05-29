package com.curso.persistencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.curso.modelo.entidad.Pelicula;
import com.curso.persistencia.rowmapper.PeliculaRowMapper;

@Repository
public class PeliculaDaoJdbcTemplateH2Implementation implements PeliculaDao {
	
	@Autowired private JdbcTemplate jdbcTemplate;
	@Autowired private PeliculaRowMapper rowMapper;

	@Override
	public void insertar(Pelicula pelicula) {
		//JdbcTemplate captura la SQLException y la relanza como unckecked
		jdbcTemplate.update("insert into peliculas (titulo,director,genero,year) values (?,?,?,?)", 
				            pelicula.getTitulo(),
				            pelicula.getDirector(), 
				            pelicula.getGenero(),
				            pelicula.getYear()); 
	}

	@Override
	public void modificar(Pelicula pelicula) {
		jdbcTemplate.update("update peliculas set titulo=?,director=?,genero=?,year=? where id=?", 
	            pelicula.getTitulo(), 
	            pelicula.getDirector(), 
	            pelicula.getGenero(),
	            pelicula.getYear(),
	            pelicula.getId()); 
	}

	@Override
	public void borrar(Pelicula pelicula) {
		jdbcTemplate.update("delete from peliculas where id=", pelicula.getId()); 
	}

	@Override
	public Pelicula buscar(Integer id) {
		return jdbcTemplate.queryForObject("select * from peliculas where id=?", rowMapper, id);
	}

	@Override
	public List<Pelicula> listar() {
		return jdbcTemplate.query("select * from peliculas", rowMapper);
	}

}









