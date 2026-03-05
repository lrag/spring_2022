package com.curso.cfg;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@ComponentScan(basePackages="com.curso.controlador")
@EnableWebMvc
public class ConfiguracionMVC implements ApplicationContextAware {
	
	private ApplicationContext appCtx;
	
	@Override
	public void setApplicationContext(ApplicationContext appCtx) throws BeansException {
		this.appCtx = appCtx;
	}
	
	//Estas dos beans son necesarias para Thymeleaf
	@Bean
	public SpringResourceTemplateResolver templateResolver(){
	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setApplicationContext(this.appCtx);
	    templateResolver.setPrefix("/WEB-INF/vistas/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    //En desarrollo mejor tenerlo a false para que podemos hacer modificaciones en las plantillas con la aplicación levantada
	    //En producción si que viene bien tenerlo a true
	    templateResolver.setCacheable(true);
	    return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine(){
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver());
	    templateEngine.setEnableSpringELCompiler(true);
	    return templateEngine;
	}	
	
	//Este es el view resolver de Spring MVC
	@Bean
	public ThymeleafViewResolver viewResolver(){
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    viewResolver.setOrder(1);
	    return viewResolver;
	}
	
}
