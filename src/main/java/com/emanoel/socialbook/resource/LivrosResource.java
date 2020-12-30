package com.emanoel.socialbook.resource;

import com.emanoel.socialbook.domain.Livro;
import com.emanoel.socialbook.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livros") //  isso quer dizer que qualquer metodo que tiver dentro dessa classe vai ter a URI 'livros' previamente
public class LivrosResource {
    @Autowired
    private LivrosRepository livrosRepository;

    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(livrosRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Livro livro){
        // RequestBody é pra dizer que deve ser pego as informacoes na requisicao e coloca ela dentro do parametro
        // Se ele nao for colocado, nao é possivel obter as informacoes do objeto livro
        livro = livrosRepository.save(livro);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livro.getId()).toUri();

        // o void dentro de ResponseEntity indica que ele nao vai retornar nenhum corpo
        return ResponseEntity.created(uri).build(); // retorna a resposta com o formato 'created' pra indicar que um recurso foi criado com o POST
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id){
        Livro livro = livrosRepository.findById(id).orElse(null);
        // ResponseEntity é um objeto que encapsula o nosso objeto de retorno, e tbm permite manipular informações do HTTP
        // Como manipular o código de respostas, por exemplo
        // A interrogação significa que ele pode encapsular qualquer tipo de objeto
        if(livro == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(livro); // setando a respota e qual vai ser o objeto retornado

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        try {
            Livro livro = new Livro();
            livro.setId(id);
            livrosRepository.delete(livro);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id){
        livro.setId(id); // garantir que quem vai ser atualizado é o recurso do ID desejado
        livrosRepository.save(livro); //  se ja existe no banco, o metodo save atualizada, se nao, ele cria

        return ResponseEntity.noContent().build();
    }

}
