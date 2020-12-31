package com.emanoel.socialbook.services.exceptions;


public class LivroNaoEncontradoException extends RuntimeException {

    public LivroNaoEncontradoException(String menssagem){

    }

    public LivroNaoEncontradoException(String menssagem, Throwable causa){
        super(menssagem,causa);
    }

}
