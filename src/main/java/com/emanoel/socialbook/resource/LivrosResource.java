package com.emanoel.socialbook.resource;

import com.emanoel.socialbook.domain.Livro;
import com.emanoel.socialbook.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros") //  isso quer dizer que qualquer metodo que tiver dentro dessa classe vai ter a URI 'livros' previamente
public class LivrosResource {
    @Autowired
    private LivrosRepository livrosRepository;

    @RequestMapping (method = RequestMethod.GET)
    public List<Livro> listar(){
        return livrosRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void salvar(@RequestBody Livro livro){
        // RequestBody é pra dizer que deve ser pego as informacoes na requisicao e coloca ela dentro do parametro
        // Se ele nao for colocado, nao é possivel obter as informacoes do objeto livro
        livrosRepository.save(livro);
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id){
        Livro livro = livrosRepository.findById(id).orElse(null);
        // ResponseEntity é um objeto que encapsula o nosso objeto de retorno, e tbm permite manipular informações do HTTP
        // Como manipular o código de respostas, por exemplo
        // A interrogação significa que ele pode encapsular qualquer tipo de objeto
        System.out.println(livro);
        if(livro == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(livro); // setando a respota e qual vai ser o objeto retornado

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletar(@PathVariable("id") Long id){
        Livro livro = new Livro();
        livro.setId(id);
        livrosRepository.delete(livro);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void atualizar(@RequestBody Livro livro, @PathVariable("id") Long id){
        livro.setId(id); // garantir que quem vai ser atualizado é o recurso do ID desejado
        livrosRepository.save(livro); //  se ja existe no banco, o metodo save atualizada, se nao, ele cria
    }

}
