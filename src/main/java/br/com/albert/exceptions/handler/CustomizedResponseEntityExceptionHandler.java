package br.com.albert.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.albert.exceptions.ExceptionResponse;
import br.com.albert.exceptions.RequiredObjectIsNullException;
import br.com.albert.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e,
			WebRequest request){
		ExceptionResponse exception = new ExceptionResponse(new Date(), 
				e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleNotFoundException(Exception e,
			WebRequest request){
		ExceptionResponse exception = new ExceptionResponse(new Date(),
				e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exception, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RequiredObjectIsNullException.class)
	public ResponseEntity<ExceptionResponse> handleBadRequestException(Exception e,
			WebRequest request){
		ExceptionResponse exception = new ExceptionResponse(new Date(),
				e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exception, HttpStatus.BAD_REQUEST);
	}
}
