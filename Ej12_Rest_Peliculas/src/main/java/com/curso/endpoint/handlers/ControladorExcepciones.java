package com.curso.endpoint.handlers;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curso.endpoint.dto.RespuestaError;
import com.curso.endpoint.dto.RespuestaOk;
import com.curso.endpoint.dto.Zasca;
import com.curso.modelo.negocio.excepcion.NegocioException;
import com.curso.modelo.negocio.excepcion.RecursoNoExistenteException;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//@ControllerAdvice
@ControllerAdvice( basePackages = "com.curso.endpoint")
public class ControladorExcepciones {

	public ControladorExcepciones() {
		super();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content)
		})
	public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) {
		Map<String, String> errores = 
				e.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(Collectors.toMap( fe -> (String) fe.getField(), 
								           fe -> (String) fe.getDefaultMessage()));
		
		Zasca error = new Zasca("400", "Datos inválidos", errores);
		RespuestaError r = new RespuestaError("400","ERROR", error);		
		return new ResponseEntity<RespuestaError>(r, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(NegocioException.class)
	@ResponseBody
	public ResponseEntity<?> handleNegocioException(NegocioException e) {
		//e.printStackTrace();
		
		Zasca error = new Zasca("400", "Error de negocio", e.getMessage());
		RespuestaError r = new RespuestaError("400","ERROR", error);
		return new ResponseEntity<RespuestaError>(r, HttpStatus.BAD_REQUEST);		
	}

	@ExceptionHandler(RecursoNoExistenteException.class)
	@ResponseBody
	public ResponseEntity<?> handle404(RecursoNoExistenteException e) {
		
		System.out.println("===============================");
		System.out.println(e.getMessage());
		//e.printStackTrace();
		
		Zasca error = new Zasca("404", "Recurso no existente", e.getMessage());
		RespuestaError r = new RespuestaError("404","NOT FOUND", error);
		return new ResponseEntity<RespuestaError>(r, HttpStatus.NOT_FOUND);		
	}	
	
	@ExceptionHandler(Throwable.class)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
		})	
	@ResponseBody
	public ResponseEntity<?> handleException(Throwable e) {
		System.out.println("=THOWABLE CAPTURADO EN CONTROLADOR EXCEPCIONES==============================");
		System.out.println(e.getMessage());
		
		Zasca error = new Zasca("500", "Error interno", e.getMessage());
		RespuestaError r = new RespuestaError("500","Consulte con el administrador", error);
		return new ResponseEntity<RespuestaError>(r, HttpStatus.NOT_FOUND);	
	}
	
}
