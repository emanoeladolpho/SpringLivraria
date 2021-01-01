package com.emanoel.socialbook.resource;

import com.emanoel.socialbook.domain.Comentario;
import com.emanoel.socialbook.domain.Livro;
import com.emanoel.socialbook.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livros") //  isso quer dizer que qualquer metodo que tiver dentro dessa classe vai ter a URI 'livros' previamente
public class LivrosResource {
    @Autowired
    private LivroService livrosService;

    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro){
        // RequestBody é pra dizer que deve ser pego as informacoes na requisicao e coloca ela dentro do parametro
        // Se ele nao for colocado, nao é possivel obter as informacoes do objeto livro
        livro = livrosService.salvar(livro);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livro.getId()).toUri();

        // o void dentro de ResponseEntity indica que ele nao vai retornar nenhum corpo
        return ResponseEntity.created(uri).build(); // retorna a resposta com o formato 'created' pra indicar que um recurso foi criado com o POST
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id){
        Livro livro = livrosService.buscar(id);
        // ResponseEntity é um objeto que encapsula o nosso objeto de retorno, e tbm permite manipular informações do HTTP
        // Como manipular o código de respostas, por exemplo
        // A interrogação significa que ele pode encapsular qualquer tipo de objeto
        return ResponseEntity.status(HttpStatus.OK).body(livro); // setando a respota e qual vai ser o objeto retornado
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        livrosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id){
        livro.setId(id); // garantir que quem vai ser atualizado é o recurso do ID desejado
        livrosService.atualizar(livro);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/comentarios", method = RequestMethod.POST)
    public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId, @RequestBody Comentario comentario){
        livrosService.salvarComentario(livroId,comentario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}/comentarios", method = RequestMethod.GET)
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long livroId){
        List<Comentario> comentarios = livrosService.listarComentario(livroId);
        return ResponseEntity.status(HttpStatus.OK).body(comentarios);
    }
}