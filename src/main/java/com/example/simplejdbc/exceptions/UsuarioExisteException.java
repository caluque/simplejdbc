package com.example.simplejdbc.exceptions;

public class UsuarioExisteException extends RuntimeException{

    public UsuarioExisteException(String nombre) {
        super("El usuario con nombre " + nombre + " ya existe");
    }

}
