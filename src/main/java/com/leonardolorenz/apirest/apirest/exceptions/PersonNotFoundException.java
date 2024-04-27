package com.leonardolorenz.apirest.apirest.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonNotFoundException extends RuntimeException{
    private String mensaje;
}
