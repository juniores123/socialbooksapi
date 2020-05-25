package com.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialbooks.domain.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long>  {

}
