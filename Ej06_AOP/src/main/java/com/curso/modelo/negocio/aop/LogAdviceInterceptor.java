package com.curso.modelo.negocio.aop;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curso.util.Logger;

//@Component
public class LogAdviceInterceptor implements MethodInterceptor {

	@Autowired
	private Logger logger;
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {		
		Method method = invocation.getMethod();         //metodo al que se está llamando
		Object[] Arguments = invocation.getArguments(); //parametros de la llamada
		Object target = invocation.getThis();           //target
				
		logger.escribir(LocalDateTime.now()+": Llamada al método "+method.getName()+" de "+target.getClass());
		Object retorno = invocation.proceed();
		logger.escribir(LocalDateTime.now()+": Fin de la llamada al método "+method.getName()+" de "+target.getClass());
		return retorno;
	}
	
}

