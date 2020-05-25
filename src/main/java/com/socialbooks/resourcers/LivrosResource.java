package com.socialbooks.resourcers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.socialbooks.domain.Comentario;
import com.socialbooks.domain.Livro;
import com.socialbooks.services.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivrosResource {
	
	@Autowired
	private LivrosService livrosService; 
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Validated @RequestBody Livro livro) {
		livro = livrosService.salvar(livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Optional<Livro> livro = livrosService.buscar(id);
		if (livro.isPresent()) {
			return ResponseEntity.ok(livro.get());
		}
		//Adicionando cache na requisição
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.notFound().cacheControl(cacheControl).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		livrosService.deletar(id);			
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosService.atualizar(livro);			
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable Long livroId,@RequestBody Comentario comentario) {
		
		//Identifica o Usuário que está conectado na api e atribui ao comentário
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		comentario.setUsuario(auth.getName());
		
		livrosService.salvarComentario(livroId, comentario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("{id}/comentarios")
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable Long livroId){
		List<Comentario> comentarios = livrosService.listarComentario(livroId);
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}

}
