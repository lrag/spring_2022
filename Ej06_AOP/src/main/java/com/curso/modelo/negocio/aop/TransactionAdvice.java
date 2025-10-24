package com.curso.modelo.negocio.aop;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curso.util.Logger;

@Component
public class TransactionAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object retorno = null;
		
		try {
			//ANTES:
			retorno = invocation.proceed();
			//DESPUES:
			//TransactionManager.COMMIT
		} catch(Exception e) {
			//TransactionManager.ROLLBACK
		}
		
		return retorno;
	}
	
}
