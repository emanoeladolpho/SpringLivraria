package com.emanoel.socialbook.services.exceptions;

public class AutorExistenteException extends RuntimeException {

    public AutorExistenteException(String menssagem){
        super(menssagem);
    }

    public AutorExistenteException(String menssagem, Throwable causa){
        super(menssagem,causa);
    }
}
