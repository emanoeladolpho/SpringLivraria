package com.emanoel.socialbook.repository;

import com.emanoel.socialbook.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livro,Long> {


}
