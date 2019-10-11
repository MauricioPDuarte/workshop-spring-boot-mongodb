package com.mauriciopd.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mauriciopd.workshopmongo.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError error = StandardError.builder()
				.withTimestamp(System.currentTimeMillis())
				.withStatus(HttpStatus.NOT_FOUND.value())
				.withError("Não encontrado")
				.withMessage(e.getMessage())
				.withPath(request.getRequestURI())
				.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
				
	}
}
