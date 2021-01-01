package com.emanoel.socialbook.services;

import com.emanoel.socialbook.domain.Autor;
import com.emanoel.socialbook.repository.AutoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoresService {

    @Autowired
    private AutoresRepository autoresRepository;

    public List<Autor> listar(){
        return autoresRepository.findAll();
    }

}
