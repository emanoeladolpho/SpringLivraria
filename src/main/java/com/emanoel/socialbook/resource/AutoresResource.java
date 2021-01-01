package com.emanoel.socialbook.resource;

import com.emanoel.socialbook.domain.Autor;
import com.emanoel.socialbook.services.AutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutoresResource{

    @Autowired
    private AutoresService autoresService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Autor>> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(autoresService.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor){
        // @Valid serve pra validar o objeto de entrada. Ele verifica as anotacoes de validacoes na classe Autor
        // Se nao tiverem de acordo com o determinado, a requisicao para por aqui
        autor = autoresService.salvar(autor);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Autor> buscar(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(autoresService.buscar(id));
    }

}
