package com.curso.modelo.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.curso.cfg.Configuracion;
import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.excepcion.PeliculaException;
import com.curso.modelo.persistencia.PeliculaDao;

@Service("gestorPeliculas")
public class GestorPeliculas /*implements ApplicationContextAware, InitializingBean*/ {

    private final Configuracion configuracion;

	/*Hasta Spring 4.2
	private ApplicationContext applicationContext;
	private GestorPeliculas proxy;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		proxy = applicationContext.getBean(GestorPeliculas.class);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	*/			
		
	@Autowired private PeliculaDao peliculaDao;
	
	//Desde Spring 4.3:
	@Autowired private GestorPeliculas proxy;

    GestorPeliculas(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

	@Transactional(
			propagation = Propagation.REQUIRES_NEW,
			rollbackFor = { PeliculaException.class }
		)
	public void insertar(Pelicula pelicula) throws Exception {
		
		System.out.print("Comprobando titulo...");
		if (pelicula.getTitulo() == null) {
			System.out.println("MAL");
			// Podemos indicar explícitamente que queremos rollback al final:
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// Si queremos detener el proceso lo suyo es lanzar una excepcion
			throw new PeliculaException("Titulo nulo");
		}

		System.out.println("OK");
		peliculaDao.insertar(pelicula);

	}

	//@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { PeliculaException.class })
	public void insertar(List<Pelicula> peliculas) throws Exception {
		for (Pelicula p : peliculas) {
			//this.insertar(p);
			try {
				proxy.insertar(p);
			} catch (PeliculaException e) {
				System.out.println("Película no insertada: "+p);
			}
		}
		//OTRO INSERT
	}

	@Transactional
	public void borrar(Pelicula pelicula) {
		peliculaDao.borrar(pelicula);
	}

	public List<Pelicula> listarTodas() {
		return peliculaDao.listar();
	}

	public void borrarPeliculas() {
		peliculaDao.borrarPeliculas();		
	}

}

/*
@Transactional
class ServicioProductos {
	
	public void insertar() {}
	public void modificar() {}
	public void addCaracteristica() {}
	public void borrar() {}
	public void baja() {}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void buscar() {}
	@Transactional(propagation=Propagation.SUPPORTS)
	public void listar() {}
	
}
*/

/*
class TxAdvice implements MethodInterceptor {

	public Object invoke(MethodInvocation mi) throws Throwable {

		Object retorno = null;
		
		try {
			retorno = mi.proceed();
			if(TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()) {
				//ROLLBACK
			} else {
				//COMMIT
			}			
		} catch (Throwable t) {
			//ROLLBACK
			//throw
		}
		
		return retorno;
		
	}	
	
}
*/
