package com.teste.springdevdojo.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teste.springdevdojo.exception.BadRequestException;
import com.teste.springdevdojo.exception.BadRequestExceptionDetails;

@ControllerAdvice // Essa anotação marca uma classe como um manipulador de exceções global, que
					// será aplicado a todos os controladores no aplicativo.
public class RestExceptionHundler {
	@ExceptionHandler(BadRequestException.class) // é o mecanismo responsável pelo tratamento da ocorrência de
													// condições, que alteram o fluxo normal da execução

    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Check the Documentation")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
