package com.emanoel.socialbook.repository;

import com.emanoel.socialbook.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentariosRepository extends JpaRepository<Comentario,Long> {
}
