package com.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialbooks.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long>{

}
