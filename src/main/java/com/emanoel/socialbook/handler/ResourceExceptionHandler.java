package com.emanoel.socialbook.handler;

import com.emanoel.socialbook.domain.DetalhesErro;
import com.emanoel.socialbook.services.exceptions.LivroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice // permite capturar todas as exceções que aconteçam no código
public class ResourceExceptionHandler {
    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException
            (LivroNaoEncontradoException e, HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404L);
        erro.setTitulo("O livro não foi encontrado!");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404"); // link que leva para uma pagina que contem informacoes sobre o erro
        erro.setTimeStamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
