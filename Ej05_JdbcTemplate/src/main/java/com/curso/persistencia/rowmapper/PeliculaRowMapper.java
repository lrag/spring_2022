package com.curso.persistencia.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.curso.modelo.entidad.Pelicula;

@Component
public class PeliculaRowMapper implements RowMapper<Pelicula>{

	@Override
	public Pelicula mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pelicula p = new Pelicula(rs.getInt("id"),
				rs.getString("titulo"),
				rs.getString("director"),
				rs.getString("genero"),
				rs.getInt("year"));
		return p;
	}	
	
}
