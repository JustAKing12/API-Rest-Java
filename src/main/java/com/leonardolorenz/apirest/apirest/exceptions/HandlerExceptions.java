package com.leonardolorenz.apirest.apirest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class HandlerExceptions {
    private final static HashMap<String, Object> respuesta = new HashMap<>();

    //Manejo mediante un handler la excepción si no encuentra a la persona, devuelvo error 404
    @ExceptionHandler({PersonNotFoundException.class})
    ResponseEntity<HashMap<String,Object>> handleException(PersonNotFoundException exception){
        respuesta.put("error", exception.getMensaje());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(respuesta);
    }

    //Manejo mediante un handler la excepción si ya existe la persona, devuelvo error 400
    @ExceptionHandler({PersonAlredyExistException.class})
    ResponseEntity<HashMap<String,Object>> handleException(PersonAlredyExistException exception){
        respuesta.put("error", exception.getMensaje());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(respuesta);
    }
}
