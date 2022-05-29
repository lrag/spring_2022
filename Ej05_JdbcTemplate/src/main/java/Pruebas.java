import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.curso.cfg.Configuracion;
import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.GestorPeliculas;
import com.curso.persistencia.PeliculaDao;

public class Pruebas {

	public static void main(String[] args) {
		
		ApplicationContext appCtx = new AnnotationConfigApplicationContext(Configuracion.class);
				
		GestorPeliculas gp = (GestorPeliculas) appCtx.getBean("gestorPeliculas");
		
		//PeliculaDao pDao = (PeliculaDao) appCtx.getBean("peliculaDao");
		
		Pelicula pelicula = new Pelicula(null, "TOCOTO","KE","TAL",1234);
		
		gp.insertar(pelicula);
		
		List<Pelicula> peliculas = gp.listar();
		peliculas.forEach( p -> System.out.println(p.getTitulo()) );	
		
	}
	
}
