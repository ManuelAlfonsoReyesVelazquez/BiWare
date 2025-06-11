package com.example.usuarios.exception;

public class InvalidArgumentsExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidArgumentsExcepcion(String message) {
        super(message); 
    }

}
