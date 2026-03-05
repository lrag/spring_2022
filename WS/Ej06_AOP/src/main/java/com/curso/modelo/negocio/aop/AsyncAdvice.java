package com.curso.modelo.negocio.aop;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curso.util.Logger;

@Component
public class AsyncAdvice implements MethodInterceptor {

	@Autowired
	//@Qualifier("loggerCronometro")
	private Logger loggerCronometro;	
	
	public void setLoggerCronometro(Logger loggerCronometro) {
		this.loggerCronometro = loggerCronometro;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		Method method = invocation.getMethod();         //metodo al que se está llamando
		Object[] Arguments = invocation.getArguments(); //parametros de la llamada
		Object target = invocation.getThis();           //target
		

		Thread th = new Thread( () -> {
			try {
				invocation.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		});
		th.start();
		
		return null;
		
		
	}
	
	
}
