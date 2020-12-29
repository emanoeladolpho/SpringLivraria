package com.emanoel.socialbook.resource;

import com.emanoel.socialbook.domain.Livro;
import com.emanoel.socialbook.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LivrosResource {

    @Autowired
    private LivrosRepository livrosRepository;

    @RequestMapping (value = "/livros", method = RequestMethod.GET)
    public List<Livro> listar(){
        return livrosRepository.findAll();
    }

}
