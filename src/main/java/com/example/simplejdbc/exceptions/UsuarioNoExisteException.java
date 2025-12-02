package com.example.simplejdbc.exceptions;

public class UsuarioNoExisteException extends RuntimeException{

    public UsuarioNoExisteException(int id) {
        super("El usuario con id " + id + " no existe");
    }

}
