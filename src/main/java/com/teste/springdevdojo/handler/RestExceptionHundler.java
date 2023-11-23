package com.teste.springdevdojo.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teste.springdevdojo.exception.BadRequestException;
import com.teste.springdevdojo.exception.BadRequestExceptionDetails;
import com.teste.springdevdojo.exception.ValidationExceptionDetails;

@ControllerAdvice // Essa anotação marca uma classe como um manipulador de exceções global, que
					// será aplicado a todos os controladores no aplicativo.
public class RestExceptionHundler {
	@ExceptionHandler(BadRequestException.class) // é o mecanismo responsável pelo tratamento da ocorrência de
													// condições, que alteram o fluxo normal da execução

	public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre) {
		return new ResponseEntity<>(
				BadRequestExceptionDetails.builder()
						.timestamp(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST.value())
						.title("Bad Request Exception, Check the Documentation")
						.details(bre.getMessage())
						.developerMessage(bre.getClass().getName())
						.build(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	  public ResponseEntity<ValidationExceptionDetails> validException(MethodArgumentNotValidException exception) {
	        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

	        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
	        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

	        return new ResponseEntity<>(
	                ValidationExceptionDetails.builder()
	                   .timestamp(LocalDateTime.now())
	                   .status(HttpStatus.BAD_REQUEST.value())
	                   .title("Bad Request Exception, Invalid Fields")
	                   .details("Check the field(s) error")
	                   .developerMessage(exception.getClass().getName())
	                   .fields(fields)
	                   .fieldsMessage(fieldsMessage)
	                   .build(), HttpStatus.BAD_REQUEST);
	  }                
	
}
