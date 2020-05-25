package com.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.socialbooks.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long> {

	Livro findOne(Long id);

}
