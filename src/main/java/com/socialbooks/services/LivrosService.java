package com.socialbooks.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.socialbooks.domain.Comentario;
import com.socialbooks.domain.Livro;
import com.socialbooks.repository.ComentariosRepository;
import com.socialbooks.repository.LivrosRepository;
import com.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {
	
	@Autowired
	private LivrosRepository livrosRepository; 
	
	@Autowired
	private ComentariosRepository comentariosRepository;
	
	public List<Livro> listar(){
		return livrosRepository.findAll();
	}
	
	public Optional<Livro> buscar(Long id) {
		Optional<Livro> livro = livrosRepository.findById(id);
		
		if(livro == null) {
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado.");
		}
		return livro;
	}
	
	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livrosRepository.save(livro);		
	}
	
	public void deletar(Long id) {
		try {
			livrosRepository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado.");
		}
	}
	
	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		livrosRepository.save(livro);
	}
	
	private void verificarExistencia(Livro livro) {
		buscar(livro.getId());
	}
	
	public Comentario salvarComentario(Long livroId, Comentario comentario) {
		Livro livro = buscarLivro(livroId);
		
		comentario.setLivro(livro);
		comentario.setData(new Date());
		
		return comentariosRepository.save(comentario);
	}
	
	private Livro buscarLivro(Long id) {
		Livro livro = livrosRepository.findOne(id);
		if(livro == null) {
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado.");
		}
		return livro;
	}
	
	public List<Comentario> listarComentario(Long livroId){
		Livro livro = buscarLivro(livroId);
		return livro.getComentarios();
	}
}
