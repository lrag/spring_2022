package com.curso.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.curso.modelo.entidad.Pelicula;

//import org.h2.Driver;

public class PeliculaDaoJdbcAPaloSeco implements PeliculaDao {

	public static void main(String[] args) {
		
		//A partir de JDBC 4.0 ya no es necesario el Class.forName
		/*
		 */
		try {
			Class.forName("org.h2.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//Driver d = new Driver();		
		//Los driver, desde hace mucho tiempo, se registran solos en un bloque estático 
		//DriverManager.registerDriver(d);
		
		//Mysql
		//CREATE TABLE `bbdd`.`peliculas` ( `id` INT NOT NULL AUTO_INCREMENT , `titulo` VARCHAR(255) NOT NULL , `director` VARCHAR(255) NOT NULL , `genero` VARCHAR(255) NOT NULL , `year` INT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;
		
		String titulo   = "Alien";
		String director = "RS";
		String genero   = "CiFi',1968); delete from peliculas #";
		Integer year    = 1979; 		
		
		String sql = "insert into peliculas (titulo,director,genero,year) values ('"+titulo+"','"+director+"','"+genero+"',"+year+")";
		
		System.out.println(sql);
	
		
		/*
		Connection cx = null; 
		try {
			cx = DriverManager.getConnection("jdbc:h2:file:c:/h2/bbdd", "sa", "");
			Statement st = cx.createStatement();
			st.executeUpdate("insert into peliculas (titulo,director,genero,year) values ('"+titulo+"','"+director+"','"+genero+"',"+year+")");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		*/

		//Try catch con gestión de recursos
		/*
		 */
		try(Connection cx=DriverManager.getConnection("jdbc:mysql://localhost:3306/bbdd?allowMultiQueries=true", "root", "root")) {
			Statement st = cx.createStatement();
			//SQL INJECTION!!!!!!
			st.executeUpdate("insert into peliculas (titulo,director,genero,year) values ('"+titulo+"','"+director+"','"+genero+"',"+year+")");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}

	@Override
	public void insertar(Pelicula pelicula) {
		try(Connection cx=DriverManager.getConnection("jdbc:mysql://localhost:3306/bbdd", "root", "root")) {
			PreparedStatement pst = cx.prepareStatement("insert into peliculas (titulo, director, genero, year) values (?,?,?,?)");
			pst.setString(1, pelicula.getTitulo());
			pst.setString(2, pelicula.getDirector());
			pst.setString(3, pelicula.getGenero());
			pst.setInt(4, pelicula.getYear());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	}

	@Override
	public void modificar(Pelicula director) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Pelicula director) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pelicula> listar() {
		List<Pelicula> peliculas = new ArrayList<>();
		try(Connection cx=DriverManager.getConnection("jdbc:mysql://localhost:3306/bbdd", "root", "root")) {
			PreparedStatement pst = cx.prepareStatement("select * from peliculas");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Pelicula p = new Pelicula(
						rs.getInt("id"),
						rs.getString("titulo"),
						rs.getString("director"),
						rs.getString("genero"),
						rs.getInt("year")
					);
				peliculas.add(p);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return peliculas;
	}

	@Override
	public Pelicula buscar(Integer idDirector) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Object> listarGenerico(String sql, RowMapper rowMapper, Object...valores) {
		List<Object> objetos = new ArrayList<>();
		try(Connection cx=DriverManager.getConnection("jdbc:mysql://localhost:3306/bbdd", "root", "root")) {
			PreparedStatement pst = cx.prepareStatement(sql);
			//Sustituir las interrogaciones por los valores
			ResultSet rs = pst.executeQuery();
			int indice = 0;
			while(rs.next()) {
				rowMapper.mapRow(rs, ++indice);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return objetos;
	}	
	
}
