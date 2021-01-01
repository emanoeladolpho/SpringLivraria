package com.emanoel.socialbook.services.exceptions;

public class AutorNaoEncontradoException extends RuntimeException{

    public AutorNaoEncontradoException(String menssagem){
        super(menssagem);
    }

    public AutorNaoEncontradoException(String menssagem, Throwable causa){
        super(menssagem,causa);
    }

}
