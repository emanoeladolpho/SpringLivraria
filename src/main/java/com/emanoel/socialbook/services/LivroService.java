package com.emanoel.socialbook.services;

import com.emanoel.socialbook.domain.Comentario;
import com.emanoel.socialbook.domain.Livro;
import com.emanoel.socialbook.repository.ComentariosRepository;
import com.emanoel.socialbook.repository.LivrosRepository;
import com.emanoel.socialbook.services.exceptions.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivrosRepository livrosRepository;
    @Autowired
    private ComentariosRepository comentariosRepository;

    public List<Livro> listar(){
        return livrosRepository.findAll();
    }

    public Livro buscar(Long id){
        Livro livro =  livrosRepository.findById(id).orElse(null);

        if(livro == null){
            new LivroNaoEncontradoException("O livro não foi encontrado!");
        }
        return livro;
    }

    public Livro salvar(Livro livro){
        livro.setId(null);
        return livrosRepository.save(livro);
    }

    public void deletar(Long id){
        Livro livro = new Livro();
        livro.setId(id);
        try{
            livrosRepository.delete(livro);
        }catch (EmptyResultDataAccessException e){
            throw new LivroNaoEncontradoException("O livro não foi encontrado!");
        }
    }

    public void atualizar(Livro livro){
        verificarExistencia(livro);
        livrosRepository.save(livro);
    }

    private void verificarExistencia(Livro livro){
        buscar(livro.getId());
    }

    public Comentario salvarComentario(Long livroId, Comentario comentario){
        Livro livro = buscar(livroId);
        comentario.setLivro(livro);
        comentario.setData(new Date());
        return comentariosRepository.save(comentario);
    }
}