package com.socialbooks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialbooks.domain.Autor;
import com.socialbooks.repository.AutoresRepository;
import com.socialbooks.services.exceptions.AutorExistenteException;
import com.socialbooks.services.exceptions.AutorNãoEncontradoException;

@Service
public class AutoresService {

	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar(){
		return autoresRepository.findAll();
	}
	
	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Optional<Autor> a = autoresRepository.findById(autor.getId());
			
			if(a != null) {
				throw new AutorExistenteException("O autor já existe.");
			}
		}
		return autoresRepository.save(autor);
	}

	public Optional<Autor> buscar(Long id) {
		Optional<Autor> autor = autoresRepository.findById(id);
		
		if(autor == null) {
			throw new AutorNãoEncontradoException("O autor não pôde ser encontrado.");
		}
		return autor;
	}
}
